import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class RaceResultsService {
    private Collection<ClientSubscription> subscriptions = new HashSet<>();
    private Logger logger;

    public RaceResultsService(Logger logger) {
        this.logger = logger;
    }

    public void send(Message message) {
        logger.log(message.getDate(), message.getMessage());
        subscriptions.stream()
                .filter(s -> s.getSubscriptionType() == message.getSubscriptionType())
                .forEach(s -> s.getClient().receive(message));
    }

    public void removeSubscriber(Client client, SubscriptionType subscriptionType) {
        Optional<ClientSubscription> clientSubscription = subscriptions
                .stream()
                .filter(s -> s.getClient().equals(client) && s.getSubscriptionType() == subscriptionType)
                .findFirst();
        if(!clientSubscription.isPresent())
            throw new IllegalArgumentException();
        subscriptions.remove(clientSubscription.get());
    }

    public void addSubscription(Client client, SubscriptionType subscriptionType) {
        subscriptions.add(new ClientSubscription(client, subscriptionType));
    }
}
