package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.ScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.ScheduleResponse;
import com.sanches.coutingOfVotes.convertions.Convertions;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ScheduleService {

    private Convertions convertions;

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(Convertions convertions, ScheduleRepository scheduleRepository){
        this.convertions = convertions;
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponse newSchedule(ScheduleRequest request)throws BadRequestException {

        log.info("Inserindo uma nova pauta para ser votada", toString());
        Optional <ScheduleEntity> scheduleEntityentity = this.scheduleRepository.findByScheduleName(request.getScheduleName());

        if (scheduleEntityentity.isPresent()){
            log.info("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
            throw new BadRequestException("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
        }

        ScheduleEntity entity = ScheduleEntity.builder().build();
        convertions.converterRequestToEntity(request, entity);
        ScheduleEntity entitySave = this.scheduleRepository.save(entity);
        log.info(entitySave.toString());

        ScheduleResponse response = ScheduleResponse.builder().build();
        convertions.convertEntityTOResponse(entitySave, response);
        return response;
    }

    public List<ScheduleEntity> listAllSchedules(){
        return scheduleRepository.findAll();
    }
}