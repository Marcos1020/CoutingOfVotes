package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.response.NewSubjectVotingResponse;
import com.sanches.coutingOfVotes.entity.NewSubjectVotingEntity;
import com.sanches.coutingOfVotes.entity.SubjectEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.repository.NewSubjectVotingRepository;
import com.sanches.coutingOfVotes.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class StartSessionScheduleService {

    private NewSubjectVotingRepository repository;

    private SubjectRepository scheduleRepository;

    @Autowired
    public StartSessionScheduleService(NewSubjectVotingRepository repository, SubjectRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public NewSubjectVotingResponse startVotIngSession(final Long idSchedule, final int valueMinutes) throws BadRequestException {

        log.info("inicando uma nova sessão de votação");
        Optional<SubjectEntity> entity = this.scheduleRepository.findById(idSchedule);

        if (ObjectUtils.isEmpty(entity)) {
            log.info("Identificador não pode ser nulo");
            throw new BadRequestException("Identificador não pode ser nulo");

        } else if (entity.isPresent()) {
            log.info("essa pauta ja foi aberta para a votacao");
            throw new BadRequestException("essa pauta ja foi aberta para a votacao");
        }

        NewSubjectVotingEntity validationEntity = NewSubjectVotingEntity.builder().build();
        savingTheDataInTheDatabase(valueMinutes, entity, validationEntity);
        NewSubjectVotingEntity entitySave = this.repository.save(validationEntity);

        NewSubjectVotingResponse response = NewSubjectVotingResponse.builder().build();
        ConvertResponseNewVotingToEntitySaveVote(entitySave, response);

        return response;
    }

    private static void savingTheDataInTheDatabase(int valueMinutes, Optional<SubjectEntity> entity, NewSubjectVotingEntity validationEntity) {
        validationEntity.setSubject(entity.get());
        validationEntity.setDateRegister(LocalDateTime.now());
        validationEntity.setDateEnd(validationEntity.getDateRegister().plusMinutes(valueMinutes));
    }

    private static void ConvertResponseNewVotingToEntitySaveVote(NewSubjectVotingEntity entitySave, NewSubjectVotingResponse response) {
        response.setIdStartVoting(entitySave.getIdStartVoting());
        response.setIdSubjecet(entitySave.getSubject().getIdSubject());
        response.setDateRegister(entitySave.getDateRegister());
        response.setDateEnd(entitySave.getDateEnd());
    }
}