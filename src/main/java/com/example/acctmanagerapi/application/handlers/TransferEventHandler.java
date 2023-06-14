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

        if (originBalance == null) {
            throw new IllegalArgumentException("Invalid origin or destination account");
        }

        if (destinationBalance == null) {
            destinationBalance = new Balance(destination, 0);  // Instantiate with initial amount
            balanceService.updateBalance(destination, 0);
        }


        int originCurrentBalance = originBalance.getBalance();
        int destinationCurrentBalance = destinationBalance.getBalance();

        originBalance.setBalance(originCurrentBalance - amount);
        destinationBalance.setBalance(destinationCurrentBalance + amount);

        balanceService.updateBalance(origin, originBalance.getBalance());
        balanceService.updateBalance(destination, destinationBalance.getBalance());

        return new Transfer(originBalance, destinationBalance);
    }
}
