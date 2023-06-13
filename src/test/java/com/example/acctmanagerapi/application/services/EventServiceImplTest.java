package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class EventServiceImplTest {

    private EventServiceImpl eventService;

    @Mock
    private BalanceService balanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventService = new EventServiceImpl(balanceService);
    }

    @Test
    void deposit_ValidEvent_UpdatesBalance() {
        Event event = new Event("deposit", null, "100", 10);
        Balance balance = new Balance("100", 0);
        when(balanceService.getBalance(event.getDestination())).thenReturn(balance);

        eventService.deposit(event);

        verify(balanceService, times(1)).updateBalance(event.getDestination(), 10);
    }

    @Test
    void withdraw_ValidEvent_UpdatesBalance() {
        Event event = new Event("withdraw", null, "100", 5);
        Balance balance = new Balance("100", 10);
        when(balanceService.getBalance(event.getDestination())).thenReturn(balance);

        eventService.withdraw(event);

        verify(balanceService, times(1)).updateBalance(event.getDestination(), 5);
    }

    @Test
    void transfer_ValidEvent_UpdatesBalances() {
        Event event = new Event("transfer", "100", "200", 10);
        Balance originBalance = new Balance("100", 20);
        Balance destinationBalance = new Balance("200", 5);
        when(balanceService.getBalance(event.getOrigin())).thenReturn(originBalance);
        when(balanceService.getBalance(event.getDestination())).thenReturn(destinationBalance);

        eventService.transfer(event);

        verify(balanceService, times(1)).updateBalance(event.getOrigin(), 10);
        verify(balanceService, times(1)).updateBalance(event.getDestination(), 15);
    }
}