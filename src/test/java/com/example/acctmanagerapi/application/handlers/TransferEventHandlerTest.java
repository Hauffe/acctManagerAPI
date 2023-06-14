package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.application.services.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferEventHandlerTest {
    private TransferEventHandler transferEventHandler;
    private BalanceService balanceService;

    @BeforeEach
    public void setup() {
        balanceService = mock(BalanceService.class);
        transferEventHandler = new TransferEventHandler();
    }

    @Test
    public void testHandleEvent_Transfer_ExistingAccounts() {
        // Arrange
        String origin = "100";
        String destination = "300";
        int initialOriginBalance = 20;
        int initialDestinationBalance = 5;
        int amount = 15;
        Event event = new Event("transfer", origin, destination, amount);

        Balance originBalance = new Balance(origin, initialOriginBalance);
        Balance destinationBalance = new Balance(destination, initialDestinationBalance);

        when(balanceService.getBalance(origin)).thenReturn(originBalance);
        when(balanceService.getBalance(destination)).thenReturn(destinationBalance);

        // Act
        transferEventHandler.handleEvent(event, balanceService);

        // Assert
        int expectedOriginBalance = initialOriginBalance - amount;
        int expectedDestinationBalance = initialDestinationBalance + amount;

        verify(balanceService, times(1)).updateBalance(origin, expectedOriginBalance);
        verify(balanceService, times(1)).updateBalance(destination, expectedDestinationBalance);
    }

    @Test
    public void testHandleEvent_Transfer_NonExistingOriginAccount() {
        // Arrange
        String origin = "200";
        String destination = "300";
        int amount = 15;
        Event event = new Event("transfer", origin, destination, amount);

        when(balanceService.getBalance(origin)).thenReturn(null);

        // Act
        assertThrows(IllegalArgumentException.class, () -> transferEventHandler.handleEvent(event, balanceService));

        // Assert
        verify(balanceService, never()).updateBalance(anyString(), anyInt());
    }
}