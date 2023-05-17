package com.sanches.coutingOfVotes.controller;

import com.sanches.coutingOfVotes.controller.request.ScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.ScheduleResponse;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v1/api/schedule")
@RestController
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping("new/register")
    public ResponseEntity<?> registerNewSchedule(
            @Valid @RequestBody ScheduleRequest request)throws BadRequestException {
        ScheduleResponse response = this.scheduleService.newSchedule(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("list/all")
    public List<ScheduleEntity> getAllSchedules(){
        return scheduleService.listAllSchedules();
    }
}