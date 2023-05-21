package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.SubjectRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.SubjectResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateScheduleResponse;
import com.sanches.coutingOfVotes.convertions.SubjectConvertions;
import com.sanches.coutingOfVotes.entity.SubjectEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.ObjectAlreadyExists;
import com.sanches.coutingOfVotes.repository.SubjectRepository;
import com.sanches.coutingOfVotes.statusenum.SubjectStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubjectService {

    private SubjectConvertions convertions;

    private SubjectRepository scheduleRepository;

    @Autowired
    public SubjectService(SubjectConvertions convertions, SubjectRepository scheduleRepository) {
        this.convertions = convertions;
        this.scheduleRepository = scheduleRepository;
    }

    public SubjectResponse newSchedule(SubjectRequest request) throws ObjectAlreadyExists {

        log.info("Inserindo uma nova pauta para ser votada", request.toString());
        Optional<SubjectEntity> scheduleEntityentity = this.scheduleRepository.findBySubject(request.getSubject());

        if (scheduleEntityentity.isPresent()) {
            log.info("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
            throw new ObjectAlreadyExists("Pauta com o mesmo nome ja foi inserida, favor verifcar o nome e tente novamente");
        }

        SubjectEntity entity = SubjectEntity.builder().build();
        convertions.converterRequestToEntity(request, entity);
        if (entity.getStatus().equals(null)){
            log.info("Status não pode ser nulo");
            throw new BadRequestException("Status não pode ser nulo");
        }
        SubjectEntity entitySave = this.scheduleRepository.save(entity);
        log.info(entitySave.toString());

        SubjectResponse response = SubjectResponse.builder().build();
        convertions.convertEntityTOResponse(entitySave, response);
        return response;
    }

    public List<SubjectEntity> listAllSchedules() {
        return scheduleRepository.findAll();
    }

    public UpdateScheduleResponse updateSchedule(final Long idSchedule, UpdateScheduleRequest request) throws BadRequestException {

        SubjectEntity scheduleEntity = this.scheduleRepository.findById(idSchedule).orElse(null);
        if (ObjectUtils.isEmpty(scheduleEntity)) {
            log.info("Idt nao encontrado na base de dados, favor verificar o numero inserido e tentar novamente");
            throw new BadRequestException("Idt nao encontrado na base de dados, favor verificar o numero inserido e tentar novamente");
        }
        convertions.UpdateConvertEntityToRequest(request, scheduleEntity);
        SubjectEntity entitySave = this.scheduleRepository.save(scheduleEntity);

        UpdateScheduleResponse response = UpdateScheduleResponse.builder().build();
        convertions.updateConvertEntitySaveToResponse(entitySave, response);

        return response;
    }
    public void inactivatingSchedule(final Long idSchedule)throws BadRequestException{

        log.info("Pausando uma pauta");
        Optional <SubjectEntity> entitySchedule = this.scheduleRepository.findById(idSchedule);

        if(!entitySchedule.isPresent()){
            log.info("Idt não encontrado na base de dados");
            throw new BadRequestException("Idt não encontrado na base de dados");

        } else if (entitySchedule.get().getStatus().equals(SubjectStatus.VOTED)){
            log.info("Não foi possivel pausar a pauta, pois ela já consta como votada");
            throw new BadRequestException("Não foi possivel pausar a pauta, pois ela já consta como votada");
        }
        entitySchedule.get().setStatus(SubjectStatus.STAND_BY);
        entitySchedule.get().setDateUpdate(ConverterUtil.nowTime());
        this.scheduleRepository.save(entitySchedule.get());
    }
}