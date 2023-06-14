package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Balance;

public interface BalanceService {
    Balance getBalance(String accountId);

    Balance updateBalance(String accountId, int newBalance);

    void deleteBalance(String accountId);

    void reset();
}
