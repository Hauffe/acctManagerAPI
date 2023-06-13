package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.core.models.Event;

public interface EventService {
    void deposit(Event event);

    void withdraw(Event event);

    void transfer(Event event);
}
