package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;

public class WithdrawEventHandler implements EventHandler{
    @Override
    public Transfer handleEvent(Event event, BalanceService balanceService) {
        String originId = event.getOrigin();
        Balance origin = balanceService.getBalance(originId);
        int amount = event.getAmount();

        Balance originBalance = balanceService.getBalance(originId);

        if (originBalance == null) {
            throw new IllegalArgumentException("Account not found: " + origin);
        }

        int currentBalance = originBalance.getBalance();
        if (currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal: " + origin);
        }

        int newBalance = currentBalance - amount;
        balanceService.updateBalance(originId, newBalance);

        return new Transfer(origin, null);
    }
}
