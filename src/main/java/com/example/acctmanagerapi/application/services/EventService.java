package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;

public interface EventService {

    Transfer processEvent(Event event);
}
