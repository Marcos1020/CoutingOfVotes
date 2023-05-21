package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.request.VotingAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.AssociateVotingResponse;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.service.VotingAssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/start")
public class VotingAssociateController {

    private VotingAssociateService votingAssociateService;

    @Autowired
    public VotingAssociateController(VotingAssociateService votingAssociateService){
        this.votingAssociateService = votingAssociateService;
    }

    @PostMapping("{idSection}/voting-associate/{idAssociate}")
    public ResponseEntity<?> newVoting(
            @PathVariable("idAssociate")final Long idAssociate,
            @PathVariable("idSection")final Long idSection,
            @Valid @RequestBody VotingAssociateRequest request) throws BadRequestException {
        AssociateVotingResponse response = this.votingAssociateService.associateVoting(idAssociate, idSection, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}