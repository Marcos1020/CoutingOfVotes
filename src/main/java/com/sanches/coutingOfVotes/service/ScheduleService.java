package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.ScheduleRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.ScheduleResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateScheduleResponse;
import com.sanches.coutingOfVotes.convertions.ScheduleConvertions;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.ObjectAlreadyExists;
import com.sanches.coutingOfVotes.repository.ScheduleRepository;
import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ScheduleService {

    private ScheduleConvertions convertions;

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleConvertions convertions, ScheduleRepository scheduleRepository) {
        this.convertions = convertions;
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponse newSchedule(ScheduleRequest request) throws ObjectAlreadyExists {

        log.info("Inserindo uma nova pauta para ser votada", request.toString());
        Optional<ScheduleEntity> scheduleEntityentity = this.scheduleRepository.findByScheduleName(request.getScheduleName());

        if (scheduleEntityentity.isPresent()) {
            log.info("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
            throw new ObjectAlreadyExists("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
        }

        ScheduleEntity entity = ScheduleEntity.builder().build();
        convertions.converterRequestToEntity(request, entity);
        if (entity.getStatus().equals(null)){
            log.info("Status não pode ser nulo");
            throw new BadRequestException("Status não pode ser nulo");
        }
        ScheduleEntity entitySave = this.scheduleRepository.save(entity);
        log.info(entitySave.toString());

        ScheduleResponse response = ScheduleResponse.builder().build();
        convertions.convertEntityTOResponse(entitySave, response);
        return response;
    }

    public List<ScheduleEntity> listAllSchedules() {
        return scheduleRepository.findAll();
    }

    public UpdateScheduleResponse updateSchedule(final Long idSchedule, UpdateScheduleRequest request) throws BadRequestException {

        ScheduleEntity scheduleEntity = this.scheduleRepository.findById(idSchedule).orElse(null);
        if (ObjectUtils.isEmpty(scheduleEntity)) {
            log.info("Idt nao encontrado na base de dados, favor verificar o numero inserido e tentar novamente");
            throw new BadRequestException("Idt nao encontrado na base de dados, favor verificar o numero inserido e tentar novamente");
        }
        convertions.UpdateConvertEntityToRequest(request, scheduleEntity);
        ScheduleEntity entitySave = this.scheduleRepository.save(scheduleEntity);

        UpdateScheduleResponse response = UpdateScheduleResponse.builder().build();
        convertions.updateConvertEntitySaveToResponse(entitySave, response);

        return response;
    }
}