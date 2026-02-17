package com.example.acctmanagerapi.application.handlers;

import com.example.acctmanagerapi.application.services.BalanceService;
import com.example.acctmanagerapi.infrastructure.dto.Event;
import com.example.acctmanagerapi.domain.models.Transfer;

public interface EventHandler {
    Transfer handleEvent(Event event, BalanceService balanceService);
}
