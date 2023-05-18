package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.request.AssociateRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.AssociateResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateAssociateResponse;
import com.sanches.coutingOfVotes.entity.AssociateEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.ObjectAlreadyExists;
import com.sanches.coutingOfVotes.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v1/api/associate")
@RestController
public class AssociateController {

    private AssociateService associateService;

    @Autowired
    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping("new/register")
    public ResponseEntity<?> newRegisterAssociate(
            @Valid @RequestBody AssociateRequest associateRequest) throws ObjectAlreadyExists {
        AssociateResponse response = this.associateService.newRegister(associateRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("list/all")
    public List<AssociateEntity> getAllAssociates() {
        return associateService.listAllAssociates();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateAssociate(
            @PathVariable("id") final Long idAssociate,
            @Valid @RequestBody UpdateAssociateRequest request) throws BadRequestException {
        UpdateAssociateResponse response = this.associateService.updateAssociate(idAssociate, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}