import java.time.LocalDate;

public interface Message {
    SubscriptionType getSubscriptionType();
    LocalDate getDate();
    String getMessage();
}
