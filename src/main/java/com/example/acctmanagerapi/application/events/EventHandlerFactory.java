package com.example.acctmanagerapi.application.events;

import com.example.acctmanagerapi.application.handlers.EventHandler;
import org.springframework.stereotype.Component;


public interface EventHandlerFactory {
    EventHandler getEventHandler(String eventType);
}
