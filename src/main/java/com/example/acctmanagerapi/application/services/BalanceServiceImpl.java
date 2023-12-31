package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Balance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BalanceServiceImpl implements BalanceService{
    private final Map<String, Balance> balances;

    public BalanceServiceImpl() {
        this.balances = new HashMap<>();
    }

    @Override
    public Balance getBalance(String accountId) {
        return balances.get(accountId);
    }

    @Override
    public Balance updateBalance(String accountId, int newBalance) {
        Balance balance = balances.get(accountId);
        if (balance != null) {
            balance.setBalance(newBalance);
        } else {
            balance = new Balance(accountId, newBalance);
            balances.put(accountId, balance);
        }
        return balance;
    }

    @Override
    public void deleteBalance(String accountId) {
        balances.remove(accountId);
    }

    @Override
    public void reset() {
        balances.clear();
    }
}
