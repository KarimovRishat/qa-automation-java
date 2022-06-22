import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AppCountryTest {
    private static Connection connection;
    private static final String countryName = "IR";
    private int createdCountryId;
    private int uniqueCountryId;


    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeAll
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @BeforeEach
    public void createCountryBeforeTests() throws SQLException {
        int biggestId = -1;
        Statement sqlSelect = connection.createStatement();
        ResultSet resultSet = sqlSelect.executeQuery("SELECT * FROM country order by id DESC limit 1");
        if (resultSet.next()) {
            biggestId = resultSet.getInt(1);
        }
        createdCountryId = biggestId + 1;

        PreparedStatement sqlInsert = connection.prepareStatement(
                "INSERT INTO country(id, country_name) VALUES(?,?)"
        );
        sqlInsert.setInt(1, createdCountryId);
        sqlInsert.setString(2, countryName);
        sqlInsert.executeUpdate();
    }

    @AfterEach
    public void deleteCountryAfterTests() throws SQLException {
        PreparedStatement sqlDeleteCreatedCountry = connection.prepareStatement(
                "DELETE FROM country WHERE id =?"
        );
        sqlDeleteCreatedCountry.setInt(1, createdCountryId);
        sqlDeleteCreatedCountry.executeUpdate();

        PreparedStatement sqlDeleteUniqueCountry = connection.prepareStatement(
                "DELETE FROM country WHERE id =?"
        );
        sqlDeleteUniqueCountry.setInt(1, uniqueCountryId);
        sqlDeleteUniqueCountry.executeUpdate();
    }

    @AfterAll
    public static void disconnect() throws SQLException {
        connection.close();

    }

    @Test
    @DisplayName("Get country by id")
    public void getCountryById() {
        when()
                .get("/api/countries/" + createdCountryId)
                .then()
                .statusCode(200)
                .body("id", not(empty()),
                        "countryName", is(countryName));
    }

    @Test
    @DisplayName("Create unique country with POST method")
    public void createUniqueCountry() throws SQLException {
        uniqueCountryId = given()
                .contentType(ContentType.JSON)
                .body("{\n" + " \"countryName\": \"NW\"\n" + "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()))
                .extract()
                .path("id");

        Collection<Integer> countryIds = new ArrayList<>();

        Statement sql = connection.createStatement();
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country WHERE country_name = 'NW'");
        while (resultSet.next()) {
            countryIds.add(resultSet.getInt(1));
        }
        assertThat(countryIds.size(), is(1));
        assertThat(countryIds, hasItem(uniqueCountryId));
    }

    @Test
    @DisplayName("Create not unique country with POST method")
    public void createNotUniqueCountry() throws SQLException {
        PreparedStatement sqlUpdate = connection.prepareStatement(
                "UPDATE public.country SET country_name = 'TT' WHERE id = ?"
        );
        sqlUpdate.setInt(1, createdCountryId);
        sqlUpdate.executeUpdate();

        given()
                .contentType(ContentType.JSON)
                .body("{\n" + " \"countryName\": \"TT\"\n" + "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Update country with PATCH method")
    public void patchingCountry() throws SQLException {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        " \"id\": \"" + createdCountryId + "\",\n" +
                        " \"countryName\": \"RR\"\n" + "}")
                .when()
                .patch("/api/countries/" + createdCountryId)
                .then()
                .statusCode(200)
                .body("id", is(createdCountryId), "countryName", is("RR"));

        Collection<Integer> countryIds = new ArrayList<>();

        Statement sql = connection.createStatement();
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country WHERE country_name = 'RR'");
        while (resultSet.next()) {
            countryIds.add(resultSet.getInt(1));
        }
        assertThat(countryIds.size(), is(1));
        assertThat(countryIds, hasItem(createdCountryId));
    }

    @Test
    @DisplayName("Delete country")
    public void deleteCountry() throws SQLException {
        when()
                .delete("/api/countries/" + createdCountryId)
                .then()
                .statusCode(204);

        Collection<Integer> countryIds = new ArrayList<>();
        Statement sql = connection.createStatement();
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country WHERE id = '"
                + createdCountryId + "'");
        while (resultSet.next()) {
            countryIds.add(resultSet.getInt(1));
        }
        assertThat(countryIds.size(), is(0));
    }
}
