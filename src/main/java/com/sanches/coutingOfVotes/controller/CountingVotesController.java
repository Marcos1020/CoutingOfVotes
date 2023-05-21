package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.response.VotingStatusResponse;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.service.CountingVotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/counting-votes")
public class CountingVotesController {

    private CountingVotesService service;

    public CountingVotesController(CountingVotesService service){
        this.service = service;
    }

    @PostMapping("{idSection}")
    public ResponseEntity<?> countingVotesOfAssociates(
            @PathVariable("idSection")final Long idSection)throws BadRequestException {
            VotingStatusResponse response  = this.service.countingVotesAssociates(idSection);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}