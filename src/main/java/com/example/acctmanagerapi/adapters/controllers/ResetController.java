package com.example.acctmanagerapi.adapters.controllers;

import com.example.acctmanagerapi.application.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reset")
public class ResetController {
    private final BalanceService balanceService;

    @Autowired
    public ResetController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public ResponseEntity<String> resetState() {
        balanceService.reset();
        return ResponseEntity.ok("OK");
    }
}
