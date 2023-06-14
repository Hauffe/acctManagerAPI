package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.application.services.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WithdrawEventHandlerTest {
    private WithdrawEventHandler withdrawEventHandler;
    private BalanceService balanceService;

    @BeforeEach
    public void setup() {
        balanceService = mock(BalanceService.class);
        withdrawEventHandler = new WithdrawEventHandler();
    }

    @Test
    public void testHandleEvent_Withdraw_NonExistingAccount() {
        // Arrange
        String origin = "200";
        int amount = 10;
        Event event = new Event("withdraw", origin, null, amount);

        when(balanceService.getBalance(origin)).thenReturn(null);

        // Act
        assertThrows(IllegalArgumentException.class, () -> withdrawEventHandler.handleEvent(event, balanceService));

        // Assert
        verify(balanceService, never()).updateBalance(anyString(), anyInt());
    }

    @Test
    public void testHandleEvent_Withdraw_ExistingAccount() {
        // Arrange
        String origin = "100";
        int initialBalance = 20;
        int amount = 5;
        Event event = new Event("withdraw", origin, null, amount);

        Balance balance = new Balance(origin, initialBalance);
        when(balanceService.getBalance(origin)).thenReturn(balance);

        // Act
        withdrawEventHandler.handleEvent(event, balanceService);

        // Assert
        int expectedNewBalance = initialBalance - amount;
        verify(balanceService, times(1)).updateBalance(origin, expectedNewBalance);
    }
}