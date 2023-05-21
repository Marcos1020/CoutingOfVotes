package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.VotingAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.AssociateVotingResponse;
import com.sanches.coutingOfVotes.entity.AssociateEntity;
import com.sanches.coutingOfVotes.entity.NewSubjectVotingEntity;
import com.sanches.coutingOfVotes.entity.AssociateVotingEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.repository.AssociateRepository;
import com.sanches.coutingOfVotes.repository.NewSubjectVotingRepository;
import com.sanches.coutingOfVotes.repository.AssociatevotingRepository;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class VotingAssociateService {

    private AssociatevotingRepository repository;

    private NewSubjectVotingRepository newSubjectVotingRepository;

    private AssociateRepository associateRepository;

    private CountingVotesService countingVotes;

    @Autowired
    public VotingAssociateService(AssociatevotingRepository repository, NewSubjectVotingRepository newSubjectVotingRepository, AssociateRepository associateRepository, CountingVotesService countingVotes) {
        this.repository = repository;
        this.newSubjectVotingRepository = newSubjectVotingRepository;
        this.associateRepository = associateRepository;
        this.countingVotes = countingVotes;
    }

    public AssociateVotingResponse associateVoting(final Long idAssociate, final Long idSection, VotingAssociateRequest request) throws BadRequestException {

        Optional<NewSubjectVotingEntity> sectionVoting = this.newSubjectVotingRepository.findById(idSection);
        Optional<AssociateEntity> associateEntity = this.associateRepository.findById(idAssociate);
        Optional<AssociateVotingEntity> votingEntity = this.repository.findById(associateEntity.get().getIdAssociate());

        VrifyConditionsTheMethods(sectionVoting, associateEntity, votingEntity);

        if (sectionVoting.get().getDateEnd().isAfter(LocalDateTime.now())) {
            log.info("Votação encerrada");
            throw new BadRequestException("Votação encerrada");
        }

        AssociateVotingEntity startVotingEntity = new AssociateVotingEntity();
        extractMetod(request, sectionVoting, associateEntity, startVotingEntity);
        AssociateVotingEntity entitySave = this.repository.save(startVotingEntity);

        AssociateVotingResponse response = AssociateVotingResponse.builder().build();
        convertEntityVotingToResponseVoting(entitySave, response);
        return response;
    }

    private static void VrifyConditionsTheMethods(Optional<NewSubjectVotingEntity> sectionVoting,
                                                  Optional<AssociateEntity> associateEntity,
                                                  Optional<AssociateVotingEntity> votingEntity) {

        if (!sectionVoting.isPresent()) {
            log.info("Pauta não encontra-se presente na base e dados");
            throw new BadRequestException("Pauta não encontra-se presente na base e dados");

        } else if (!associateEntity.isPresent()) {
            log.info("error");
            throw new BadRequestException("Error");

        } else if (votingEntity.isPresent()) {
            log.info("O associado portador do idt já votou na pauta em questão");
            throw new BadRequestException("O associado portador do idt já votou na pauta em questão");
        }
    }

    private static void extractMetod(VotingAssociateRequest request, Optional<NewSubjectVotingEntity> sectionEntity,
                                     Optional<AssociateEntity> associateEntity,
                                     AssociateVotingEntity startVotingEntity) {
        startVotingEntity.setAssociates(associateEntity.get());
        startVotingEntity.setSubject(sectionEntity.get());
        startVotingEntity.setVote(request.getVote());
        startVotingEntity.setDateRegisterOpenVotings(ConverterUtil.nowTime());
    }

    private static void convertEntityVotingToResponseVoting(AssociateVotingEntity entitySave, AssociateVotingResponse response) {
        response.setIdAssociate(entitySave.getAssociates().getIdAssociate());
        response.setIdSubject(entitySave.getSubject().getIdStartVoting());
        response.setVote(entitySave.getVote());
        response.setDateRegisterOpenVotings(entitySave.getDateRegisterOpenVotings());
    }
}