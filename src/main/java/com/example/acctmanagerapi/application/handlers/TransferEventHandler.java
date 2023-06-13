package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;

public class TransferEventHandler implements EventHandler{
    @Override
    public void handleEvent(Event event, BalanceService balanceService) {
        Balance originBalance = balanceService.getBalance(event.getOrigin());
        Balance destinationBalance = balanceService.getBalance(event.getDestination());
        int originNewBalance = originBalance.getBalance() - event.getAmount();
        int destinationNewBalance = destinationBalance.getBalance() + event.getAmount();
        balanceService.updateBalance(event.getOrigin(), originNewBalance);
        balanceService.updateBalance(event.getDestination(), destinationNewBalance);
    }
}
