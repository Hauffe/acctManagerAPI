package com.example.acctmanagerapi.application.events;

import com.example.acctmanagerapi.application.handlers.EventHandler;


public interface EventHandlerFactory {
    EventHandler getEventHandler(String eventType);
}
