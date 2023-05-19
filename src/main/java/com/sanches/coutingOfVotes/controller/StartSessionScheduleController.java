package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.service.StartSessionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/start-session")
public class StartSessionScheduleController {

    private StartSessionScheduleService service;

    @Autowired
    public StartSessionScheduleController(StartSessionScheduleService service) {
        this.service = service;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> StartVotation(
            @PathVariable("id") final Long idSchedule) throws BadRequestException {
        this.service.startVotIngSession(idSchedule);
        return ResponseEntity.status(HttpStatus.OK).body("iniciando uma nova sessao de votacao");
    }
}