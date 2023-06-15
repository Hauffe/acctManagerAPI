package com.example.acctmanagerapi.application.services;

import com.example.acctmanagerapi.application.events.EventHandlerFactory;
import com.example.acctmanagerapi.application.handlers.EventHandler;
import com.example.acctmanagerapi.core.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
class EventServiceImplTest {
    private EventServiceImpl eventService;

    @Mock
    private BalanceService balanceService;

    @Mock
    private EventHandlerFactory eventHandlerFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventService = new EventServiceImpl(balanceService, eventHandlerFactory);
    }

    @Test
    void processEvent_WithValidEventType_CallsEventHandlerHandleEvent() {
        // Arrange
        Event event = new Event("deposit", null, "100", 10);
        EventHandler eventHandler = mock(EventHandler.class);
        when(eventHandlerFactory.getEventHandler(event.getType())).thenReturn(eventHandler);

        // Act
        eventService.processEvent(event);

        // Assert
        verify(eventHandler, times(1)).handleEvent(event, balanceService);
    }
}