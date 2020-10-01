import java.util.Objects;

public class ClientSubscription {

    private Client client;
    private SubscriptionType subscriptionType;

    public ClientSubscription(Client client, SubscriptionType subscriptionType) {
        this.client = client;
        this.subscriptionType = subscriptionType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSubscription that = (ClientSubscription) o;
        return Objects.equals(client, that.client) &&
                subscriptionType == that.subscriptionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, subscriptionType);
    }
}
