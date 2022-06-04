import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageServiceSaverInterface;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageServiceSaver;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.*;

import static com.tcs.edu.enums.Severity.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {

    private MessageServiceSaverInterface serviceSaver;

    @BeforeEach
    public void setUp() {
        serviceSaver = new MessageServiceSaver();
    }

    @Test
    @DisplayName("Saving message")
    public void messageShouldBeSaved() throws NoSuchFieldException, IllegalAccessException {
        Message message = new Message(MINOR, "Test message");

        serviceSaver.save(message);

        Field messageRepositoryField = serviceSaver.getClass().getDeclaredField("messageRepository");
        messageRepositoryField.setAccessible(true);

        HashMapMessageRepository messageRepository =
                (HashMapMessageRepository) messageRepositoryField.get(serviceSaver);
        Field hashMapMessageRepositoryField = messageRepository.getClass().getDeclaredField("messages");
        hashMapMessageRepositoryField.setAccessible(true);
        HashMap messageRepositoryValue = (HashMap) hashMapMessageRepositoryField.get(messageRepository);

        Message savedMessage = (Message) messageRepositoryValue.values().toArray() [0];

        Assertions.assertAll(
                () -> assertThat(messageRepositoryValue).hasSize(1),
                () -> assertThat(savedMessage.getBody()).isEqualTo("Test message")
        );
    }

    @Test
    @DisplayName("Don`t save message with null body")
    public void messageWithNullBodyShouldNotBeSaved() {
        Message message = new Message(MINOR, null);

        Exception exception = Assertions.assertThrows(LogException.class, () -> serviceSaver.save(null));

        assertThat(exception.getMessage()).isEqualTo("Can`t print message");
    }

    @Test
    @DisplayName("Don`t save null message")
    public void nullMessageShouldNotBeSaved() {
        Exception exception = Assertions.assertThrows(LogException.class, () -> serviceSaver.save(null));

        assertThat(exception.getMessage()).isEqualTo("Can`t print message");
    }

    @Nested
    @DisplayName("Find message")
    class MessageShouldBeFound {

        @BeforeEach
        public void setUpMessages() {
            Message message = new Message(MINOR, "Test message");
            Message message1 = new Message(MAJOR, "Test message 1");

            serviceSaver.save(message);
            serviceSaver.save(message1);
        }

        @Test
        @DisplayName("Find message by primary key")
        public void messageShouldBeFoundByPrimaryKey() {
            Message newMessage = new Message(REGULAR, "Test message");
            final UUID generatedKey = serviceSaver.save(newMessage);

            String messageBody = serviceSaver.findByPrimaryKey(generatedKey).getBody();

            assertThat(messageBody).isEqualTo("Test message");
        }

        @Test
        @DisplayName("Find message by severity")
        public void messageShouldBeFoundBySeverity() {
            final Collection<Message> allMessages = serviceSaver.findBySeverity(MAJOR);

            ArrayList<String> allMessagesBody = new ArrayList<>();
            for (Message currentMessage : allMessages) {
                allMessagesBody.add(currentMessage.getBody());
            }

            assertThat(allMessagesBody).hasSize(1).contains("Test message 1");
        }

        @Test
        @DisplayName("Find all messages")
        public void allMessagesShouldBeFound() {
            final Collection<Message> allMessages = serviceSaver.findAll();

            ArrayList<String> allMessagesBody = new ArrayList<>();
            for (Message currentMessage : allMessages) {
                allMessagesBody.add(currentMessage.getBody());
            }

            assertThat(allMessagesBody).hasSize(2).contains("Test message", "Test message 1");
        }
    }




}
