package com.example.acctmanagerapi.application.handlers;


import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.application.services.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
class DepositEventHandlerTest {

    private BalanceService balanceService;
    private DepositEventHandler depositEventHandler;

    @BeforeEach
    public void setup() {
        balanceService = mock(BalanceService.class);
        depositEventHandler = new DepositEventHandler();
    }

    @Test
    public void testHandleEvent_NewAccount() {
        Event event = new Event("deposit", null,"100", 10);

        doReturn(null).when(balanceService).getBalance("100");

        depositEventHandler.handleEvent(event, balanceService);

        verify(balanceService, times(1)).getBalance("100");
        verify(balanceService, times(1)).updateBalance("100", 10);
    }

    @Test
    public void testHandleEvent_ExistingAccount() {
        Event event = new Event("deposit", null, "100", 10);

        Balance existingBalance = new Balance("100", 10);
        doReturn(existingBalance).when(balanceService).getBalance("100");

        depositEventHandler.handleEvent(event, balanceService);

        verify(balanceService, times(1)).getBalance("100");
        verify(balanceService, times(1)).updateBalance("100", 20);
    }
}