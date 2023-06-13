package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;

public class DepositEventHandler implements EventHandler{
    @Override
    public void handleEvent(Event event, BalanceService balanceService) {
        Balance balance = balanceService.getBalance(event.getDestination());
        int newBalance = balance.getBalance() + event.getAmount();
        balanceService.updateBalance(event.getDestination(), newBalance);
    }
}
