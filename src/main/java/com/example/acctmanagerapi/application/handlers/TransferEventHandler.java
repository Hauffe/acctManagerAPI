package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.domain.models.Balance;
import com.example.acctmanagerapi.infrastructure.dto.Event;
import com.example.acctmanagerapi.domain.models.Transfer;

public class TransferEventHandler implements EventHandler{
    @Override
    public Transfer handleEvent(Event event, BalanceService balanceService) {
        Balance originBalance = balanceService.getBalance(event.getOrigin());
        Balance destinationBalance = balanceService.getBalance(event.getDestination());

        if (originBalance == null) {
            throw new IllegalArgumentException("Invalid origin or destination account");
        }

        if (destinationBalance == null) {
            destinationBalance = balanceService.updateBalance(event.getDestination(),0);
        }

        Transfer transfer = new Transfer(originBalance, destinationBalance);
        transfer.processTransfer(event.getAmount());

        balanceService.updateBalance(transfer.getOrigin().getId(), transfer.getOrigin().getBalance());
        balanceService.updateBalance(transfer.getDestination().getId(), transfer.getDestination().getBalance());

        return transfer;
    }
}
