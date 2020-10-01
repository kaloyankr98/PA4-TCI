import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class RaceResultsServiceTests {
    private Client client1 = mock(Client.class);
    private Client client2 = mock(Client.class);

    private Logger logger = mock(Logger.class);
    private RaceResultsService raceResultsService = new RaceResultsService(logger);

    @Test
    public void receiveMessagesOnlyToSubscribedTypes(){
        Message f1RacesMessage = mock(Message.class);

        when(f1RacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.F1);

        raceResultsService.addSubscription(client1, SubscriptionType.F1);
        raceResultsService.send(f1RacesMessage);

        verify(client1).receive(f1RacesMessage);
    }

    @Test
    public void receiveMessagesOnSubscribedTypesOnly(){
        Message horseRacesMessage = mock(Message.class);
        Message boatRacesMessage = mock(Message.class);
        Message f1RacesMessage = mock(Message.class);

        when(horseRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.HORSES);
        when(boatRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.BOATS);
        when(f1RacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.F1);

        raceResultsService.addSubscription(client1, SubscriptionType.HORSES);
        raceResultsService.addSubscription(client2, SubscriptionType.F1);

        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(boatRacesMessage);
        raceResultsService.send(f1RacesMessage);

        verify(client1).receive(horseRacesMessage);
        verify(client1).receive(f1RacesMessage); //client2 is subscribed to F1, should fail
        verify(client1, never()).receive(boatRacesMessage);
    }
    @Test
    public void receiveMessagesOnSubscribedTypesOnlyTrue(){
        Message horseRacesMessage = mock(Message.class);
        Message boatRacesMessage = mock(Message.class);
        Message f1RacesMessage = mock(Message.class);

        when(horseRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.HORSES);
        when(boatRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.BOATS);
        when(f1RacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.F1);

        raceResultsService.addSubscription(client1, SubscriptionType.HORSES);
        raceResultsService.addSubscription(client2, SubscriptionType.F1);

        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(boatRacesMessage);
        raceResultsService.send(f1RacesMessage);

        verify(client1).receive(horseRacesMessage);
        verify(client2).receive(f1RacesMessage); //client2 is subscribed to F1, should work
        verify(client1, never()).receive(boatRacesMessage);
    }

    @Test
    public void moreThanOneSubscribedClientMustReceiveMessages(){
        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.HORSES);

        raceResultsService.addSubscription(client1, SubscriptionType.HORSES);
        raceResultsService.addSubscription(client2, SubscriptionType.HORSES);

        raceResultsService.send(horseRacesMessage);

        verify(client1).receive(horseRacesMessage);
        verify(client2).receive(horseRacesMessage);
    }

    @Test
    public void LoggingWhenMessageIsSent(){
        LocalDate currentDate = LocalDate.now();
        String logMessage = "message logged";

        Message f1RacesMessage = mock(Message.class);

        when(f1RacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.F1);
        when(f1RacesMessage.getDate()).thenReturn(currentDate);
        when(f1RacesMessage.getMessage()).thenReturn(logMessage);

        raceResultsService.addSubscription(client1, SubscriptionType.F1);
        raceResultsService.send(f1RacesMessage);

        verify(logger).log(currentDate, logMessage);
        verify(client1).receive(f1RacesMessage);
    }

    @Test
    public void clientsGetMultipleMessagesOnSubscribedCategories(){
        Message boatRacesMessage = mock(Message.class);
        Message f1RacesMessage = mock(Message.class);

        when(f1RacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.F1);//indirect input
        when(boatRacesMessage.getSubscriptionType()).thenReturn(SubscriptionType.BOATS);//indirect input

        raceResultsService.addSubscription(client1, SubscriptionType.F1);//direct input
        raceResultsService.addSubscription(client1, SubscriptionType.BOATS);//direct input

        raceResultsService.send(f1RacesMessage);
        raceResultsService.send(f1RacesMessage);
        raceResultsService.send(boatRacesMessage);

        verify(client1, times(2)).receive(f1RacesMessage);//indirect output
        verify(client1).receive(boatRacesMessage);//indirect output
    }

    @Test(expected = IllegalArgumentException.class)
    public void unsubscribeThenSubscribe(){
        raceResultsService.removeSubscriber(client1, SubscriptionType.HORSES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSubscribedTypeToBeRemovedFrom(){
        raceResultsService.addSubscription(client1, SubscriptionType.HORSES);
        raceResultsService.removeSubscriber(client1, SubscriptionType.F1);
    }

}
