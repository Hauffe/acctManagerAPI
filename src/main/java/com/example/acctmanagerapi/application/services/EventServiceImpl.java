package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    private final BalanceService balanceService;
    public EventServiceImpl(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public void deposit(Event event) {
        Balance balance = balanceService.getBalance(event.getDestination());
        int newBalance = balance.getBalance() + event.getAmount();
        balanceService.updateBalance(event.getDestination(), newBalance);
    }

    @Override
    public void withdraw(Event event) {
        Balance balance = balanceService.getBalance(event.getDestination());
        int newBalance = balance.getBalance() - event.getAmount();
        balanceService.updateBalance(event.getDestination(), newBalance);
    }

    @Override
    public void transfer(Event event) {
        Balance originBalance = balanceService.getBalance(event.getOrigin());
        Balance destinationBalance = balanceService.getBalance(event.getDestination());
        int originNewBalance = originBalance.getBalance() - event.getAmount();
        int destinationNewBalance = destinationBalance.getBalance() + event.getAmount();
        balanceService.updateBalance(event.getOrigin(), originNewBalance);
        balanceService.updateBalance(event.getDestination(), destinationNewBalance);
    }
}
