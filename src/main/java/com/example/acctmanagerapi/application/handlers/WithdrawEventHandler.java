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

        if (origin == null) {
            throw new IllegalArgumentException("Account not found: " + originId);
        }

        origin.withdraw(amount);

        balanceService.updateBalance(originId, origin.getBalance());

        return new Transfer(origin, null);
    }
}
