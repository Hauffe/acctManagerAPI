package com.example.acctmanagerapi.adapters.controllers;

import com.example.acctmanagerapi.application.services.EventService;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        Transfer transfer = eventService.processEvent(event);
        if (transfer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(transfer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }
}
