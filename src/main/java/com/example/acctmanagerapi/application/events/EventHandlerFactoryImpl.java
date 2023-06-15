package com.example.acctmanagerapi.application.events;

import com.example.acctmanagerapi.application.handlers.DepositEventHandler;
import com.example.acctmanagerapi.application.handlers.EventHandler;
import com.example.acctmanagerapi.application.handlers.TransferEventHandler;
import com.example.acctmanagerapi.application.handlers.WithdrawEventHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandlerFactoryImpl implements EventHandlerFactory{
    private final Map<String, EventHandler> eventHandlers;

    public EventHandlerFactoryImpl() {
        this.eventHandlers = new HashMap<>();
        initializeEventHandlers();
    }

    private void initializeEventHandlers() {
        // Register event handlers
        eventHandlers.put("deposit", new DepositEventHandler());
        eventHandlers.put("withdraw", new WithdrawEventHandler());
        eventHandlers.put("transfer", new TransferEventHandler());
    }

    @Override
    public EventHandler getEventHandler(String eventType) {
        EventHandler handler = eventHandlers.get(eventType);
        if(handler == null){
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }
        return eventHandlers.get(eventType);
    }

}
