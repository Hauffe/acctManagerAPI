package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Event;

public interface EventService {

    void processEvent(Event event);
}
