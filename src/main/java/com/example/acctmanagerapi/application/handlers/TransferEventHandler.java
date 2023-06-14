package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;

public class TransferEventHandler implements EventHandler{
    @Override
    public Transfer handleEvent(Event event, BalanceService balanceService) {
        String origin = event.getOrigin();
        String destination = event.getDestination();
        int amount = event.getAmount();

        Balance originBalance = balanceService.getBalance(origin);
        Balance destinationBalance = balanceService.getBalance(destination);

        if (originBalance == null || destinationBalance == null) {
            throw new IllegalArgumentException("Invalid origin or destination account");
        }

        int originCurrentBalance = originBalance.getBalance();
        int destinationCurrentBalance = destinationBalance.getBalance();

        int originNewBalance = originCurrentBalance - amount;
        int destinationNewBalance = destinationCurrentBalance + amount;

        balanceService.updateBalance(origin, originNewBalance);
        balanceService.updateBalance(destination, destinationNewBalance);

        return new Transfer(originBalance, destinationBalance);
    }
}
