package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.infrastructure.dto.Event;
import com.example.acctmanagerapi.domain.models.Transfer;

public interface EventService {

    Transfer processEvent(Event event);
}
