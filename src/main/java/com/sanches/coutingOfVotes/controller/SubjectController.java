package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.request.SubjectRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.SubjectResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateScheduleResponse;
import com.sanches.coutingOfVotes.entity.SubjectEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.ObjectAlreadyExists;
import com.sanches.coutingOfVotes.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v1/api/schedule")
@RestController
public class SubjectController {

    private SubjectService scheduleService;

    @Autowired
    public SubjectController(SubjectService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping("new/register")
    public ResponseEntity<?> registerNewSchedule(
            @Valid @RequestBody SubjectRequest request)throws ObjectAlreadyExists {
        SubjectResponse response = this.scheduleService.newSchedule(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("list/all")
    public List<SubjectEntity> getAllSchedules(){
        return scheduleService.listAllSchedules();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id")final Long idSchedule,
            @Valid @RequestBody UpdateScheduleRequest request)throws BadRequestException{
        UpdateScheduleResponse response = this.scheduleService.updateSchedule(idSchedule, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("inactive/{id}")
    public ResponseEntity<?>inactive(
            @PathVariable ("id")final Long idSchedule) throws BadRequestException{
        this.scheduleService.inactivatingSchedule(idSchedule);
        return ResponseEntity.status(HttpStatus.OK).body("Pauta em Stand-by");
    }
}