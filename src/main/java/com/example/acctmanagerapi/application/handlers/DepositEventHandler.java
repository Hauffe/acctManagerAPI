package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;

public class DepositEventHandler implements EventHandler{
    @Override
    public Transfer handleEvent(Event event, BalanceService balanceService) {
        String destinationId = event.getDestination();
        int amount = event.getAmount();

        Balance destination = balanceService.getBalance(destinationId);
        if (destination == null) {
            destination = new Balance(destinationId, amount);  // Instantiate with initial amount
            balanceService.updateBalance(destinationId, amount);
        } else {
            int newBalance = destination.getBalance() + amount;
            balanceService.updateBalance(destinationId, newBalance);
        }
        return new Transfer(null, destination);
    }
}
