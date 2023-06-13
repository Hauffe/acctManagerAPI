package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Balance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceImplTest {

    private BalanceServiceImpl balanceService;

    @BeforeEach
    void setUp() {
        balanceService = new BalanceServiceImpl();
    }

    @Test
    void getBalance_ExistingAccountId_ReturnsBalance() {
        String accountId = "100";
        Balance balance = new Balance(accountId, 10);
        balanceService.updateBalance(accountId, 10);

        Balance result = balanceService.getBalance(accountId);

        assertEquals(balance, result);
    }

    @Test
    void getBalance_NonExistingAccountId_ReturnsNull() {
        String accountId = "200";

        Balance result = balanceService.getBalance(accountId);

        assertNull(result);
    }

    @Test
    void updateBalance_ExistingAccountId_UpdatesBalance() {
        String accountId = "100";
        Balance balance = new Balance(accountId, 10);
        balanceService.updateBalance(accountId, 10);

        balanceService.updateBalance(accountId, 20);
        Balance updatedBalance = balanceService.getBalance(accountId);

        assertEquals(20, updatedBalance.getBalance());
    }

    @Test
    void updateBalance_NonExistingAccountId_CreatesNewBalance() {
        String accountId = "200";
        Balance balance = new Balance(accountId, 30);

        balanceService.updateBalance(accountId, 30);
        Balance updatedBalance = balanceService.getBalance(accountId);

        assertEquals(balance, updatedBalance);
    }

    @Test
    void deleteBalance_ExistingAccountId_RemovesBalance() {
        String accountId = "100";
        balanceService.updateBalance(accountId, 10);

        balanceService.deleteBalance(accountId);
        Balance result = balanceService.getBalance(accountId);

        assertNull(result);
    }

    @Test
    void deleteBalance_NonExistingAccountId_DoesNotThrowException() {
        String accountId = "200";

        assertDoesNotThrow(() -> balanceService.deleteBalance(accountId));
    }
}