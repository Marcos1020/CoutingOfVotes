package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.response.VotingStatusResponse;
import com.sanches.coutingOfVotes.entity.AssociateVotingEntity;
import com.sanches.coutingOfVotes.entity.NewSubjectVotingEntity;
import com.sanches.coutingOfVotes.entity.SubjectEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.repository.AssociatevotingRepository;
import com.sanches.coutingOfVotes.repository.NewSubjectVotingRepository;
import com.sanches.coutingOfVotes.repository.SubjectRepository;
import com.sanches.coutingOfVotes.statusenum.StatusVote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CountingVotesService {

    private SubjectRepository scheduleRepository;

    private AssociatevotingRepository associateRepository;

    private NewSubjectVotingRepository repository;

    @Autowired
    public CountingVotesService(SubjectRepository scheduleRepository, AssociatevotingRepository associateRepository, NewSubjectVotingRepository repository) {
        this.scheduleRepository = scheduleRepository;
        this.associateRepository = associateRepository;
        this.repository = repository;
    }

    public VotingStatusResponse countingVotesAssociates(final Long idSection)throws BadRequestException {

        Optional<NewSubjectVotingEntity> voting  = this.repository.findById(idSection);
        SubjectEntity schedule = SubjectEntity.builder().build();
        List<AssociateVotingEntity> votingEntity = this.associateRepository.findAll();

        if (!voting.isPresent()){
            log.info("Idt de sessão não foi encontrado");
            throw new BadRequestException("Idt de sessão não foi encontrado");
        }
        int countingVotesYes = 0;
        int countingVotesNo = 0;

        for (AssociateVotingEntity associate : votingEntity)
            if ("SIM".equals(associate.getVote())) {
                countingVotesYes ++;
            } else if ("NAO".equals(associate.getVote())) {
                countingVotesNo ++;
            }

        if (countingVotesYes > countingVotesNo){
            schedule.setStatusVote(StatusVote.APROVED);
            this.scheduleRepository.save(schedule);
        } else {
            schedule.setStatusVote(StatusVote.NOT_APROVED);
            this.scheduleRepository.save(schedule);
        }
        VotingStatusResponse response = VotingStatusResponse.builder().build();
        response.setIdVoting(voting.get().getIdStartVoting());
        response.setStatusVote(schedule.getStatusVote().toString());
        return response;
    }
}