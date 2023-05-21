package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.response.NewSubjectVotingResponse;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.service.StartSessionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable("id") final Long idSchedule,
            @RequestParam(name = "valueMinutes", required = false, defaultValue = "1") final int valueMinutes) throws BadRequestException {
        NewSubjectVotingResponse response = this.service.startVotIngSession(idSchedule, valueMinutes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}