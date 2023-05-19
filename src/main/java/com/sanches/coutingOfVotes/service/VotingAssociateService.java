package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.VotingAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.VotingAssociateResponse;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.entity.StartVotingEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.NotFoundException;
import com.sanches.coutingOfVotes.repository.ScheduleRepository;
import com.sanches.coutingOfVotes.repository.StartSessionScheduleRepository;
import com.sanches.coutingOfVotes.statusenum.StatusAssociate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VotingAssociateService {

    private StartSessionScheduleRepository repository;

    private ScheduleRepository scheduleRepository;

    @Autowired
    public VotingAssociateService(StartSessionScheduleRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public VotingAssociateResponse associateVoting(final Long idPauta, final Long idAssociate, VotingAssociateRequest request) throws BadRequestException, NotFoundException {

        Optional<StartVotingEntity> startVoting = this.repository.findById(idAssociate);
        Optional<ScheduleEntity> scheduleEntity = this.scheduleRepository.findById(idPauta);

        if (!scheduleEntity.isPresent()){
            log.info("Idt da pauta não foi encontrado");
            throw new BadRequestException("Idt da pauta não foi encontrado");

        } else if (!startVoting.isPresent()) {
            log.info("Idt de associado não foi encontrado na base de dados");
            throw new BadRequestException("Idt de associado não foi encontrado na base de dados");

        } else if (request.getCpf().equals(StatusAssociate.INACTIVE)) {
            log.info("Associado está inativo, e não está apto a votação");
            throw new NotFoundException("Associado está inativo, e não está apto a votação");
        }

        startVoting.get().setAssociates(request.getIdAssociate());

        return null;
    }
}