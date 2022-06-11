import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class AppCountryTest {

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

    @Test
    @DisplayName("Get country by id with GET method")
    public void shouldReturnCountryById() {
        int countryId =
                given()
                        .body("{\n" + "\"countryName\": \"NW\" \n" + "}")
                        .contentType("application/json")
                        .when()
                        .post("/api/countries")
                        .body()
                        .path("id");
        when()
                .get("/api/countries/" + countryId)
                .then()
                .statusCode(200)
                .body("countryName", equalTo("NW"));
        when().delete("/api/countries/" + countryId);
    }

    @Test
    @DisplayName("Update country by id with PUT method")
    public void shouldUpdateCountryById() {
        given()
                .body("{\n" + "\"id\": 2,\n" + "\"countryName\": \"SW\" \n" + "}")
                .contentType("application/json")
                .when()
                .put("/api/countries/2")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("SW"))
                .body("id", equalTo(2))
                .body("locations", equalTo(null));
    }

    @Test
    @DisplayName("Create country with POST method")
    public void shouldCreateCountry() {
        int countryId =
                given()
                        .body("{\n" + "\"countryName\": \"KK\" \n" + "}")
                        .contentType("application/json")
                        .when()
                        .post("/api/countries")
                        .then()
                        .statusCode(201)
                        .body("countryName", equalTo("KK"))

                        .extract()
                        .response().path("id");

        when().delete("/api/countries/" + countryId);

    }

    @Test
    @DisplayName("Delete country by id with DELETE method")
    public void shouldDeleteCountryById() {
        int countryId =
                given()
                        .body("{\n" + "\"countryName\": \"IR\" \n" + "}")
                        .contentType("application/json")
                        .when()
                        .post("/api/countries")
                        .body()
                        .path("id");
        when()
                .delete("/api/countries/" + countryId)
                .then()
                .statusCode(204);
        when()
                .get("/api/countries/" + countryId)
                .then()
                .statusCode(404);

    }
}