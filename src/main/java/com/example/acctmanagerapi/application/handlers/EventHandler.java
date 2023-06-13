package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.core.models.Event;

public interface EventHandler {
    void handleEvent(Event event, BalanceService balanceService);
}
