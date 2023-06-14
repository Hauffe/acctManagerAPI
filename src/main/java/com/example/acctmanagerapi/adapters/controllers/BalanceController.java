package com.example.acctmanagerapi.adapters.controllers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<Integer> getBalance(@RequestParam("account_id") String accountId) {
        Balance balance = balanceService.getBalance(accountId);
        if (balance != null) {
            return ResponseEntity.ok(balance.getBalance());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }
}
