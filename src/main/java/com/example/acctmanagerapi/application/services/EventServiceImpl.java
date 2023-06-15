package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.application.events.EventHandlerFactory;
import com.example.acctmanagerapi.application.handlers.EventHandler;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService{
    private final BalanceService balanceService;
    private final EventHandlerFactory eventHandlerFactory;

    public EventServiceImpl(BalanceService balanceService, EventHandlerFactory eventHandlerFactory) {
        this.balanceService = balanceService;
        this.eventHandlerFactory = eventHandlerFactory;
    }

    @Override
    public Transfer processEvent(Event event) {
        Transfer transfer;
        try{
            EventHandler eventHandler = eventHandlerFactory.getEventHandler(event.getType());
            transfer = eventHandler.handleEvent(event, balanceService);
        }catch (IllegalArgumentException e){
            transfer = null;
        }
        return transfer;
    }
}
