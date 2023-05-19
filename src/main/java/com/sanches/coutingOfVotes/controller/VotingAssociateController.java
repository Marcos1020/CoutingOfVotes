package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.request.VotingAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.VotingAssociateResponse;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.NotFoundException;
import com.sanches.coutingOfVotes.service.VotingAssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api")
public class VotingAssociateController {

    private VotingAssociateService votingAssociateService;

    @Autowired
    public VotingAssociateController(VotingAssociateService votingAssociateService){
        this.votingAssociateService = votingAssociateService;
    }

    @PostMapping("{idPauta}/voting-associate/{idAssociate}")
    public ResponseEntity<?> newVoting(
            @PathVariable("idPauta")final Long idPauta,
            @PathVariable("idAssociate")final Long idAssociate,
            @Valid @RequestBody VotingAssociateRequest request) throws BadRequestException, NotFoundException {
        VotingAssociateResponse response = this.votingAssociateService.associateVoting(idAssociate, idPauta, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
