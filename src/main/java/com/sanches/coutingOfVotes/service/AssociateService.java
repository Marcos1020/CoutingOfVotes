package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.controller.request.AssociateRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.AssociateResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateAssociateResponse;
import com.sanches.coutingOfVotes.convertions.AssociateConvertions;
import com.sanches.coutingOfVotes.entity.AssociateEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.exception.ObjectAlreadyExists;
import com.sanches.coutingOfVotes.repository.AssociateRepository;
import com.sanches.coutingOfVotes.statusenum.StatusAssociate;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AssociateService {

    private AssociateRepository associateRepository;

    private AssociateConvertions convertions;

    @Autowired
    public AssociateService(AssociateRepository associateRepository, AssociateConvertions convertions){
        this.associateRepository = associateRepository;
        this.convertions = convertions;
    }

    public AssociateResponse newRegister(AssociateRequest associateRequest) throws ObjectAlreadyExists {

        log.info("Cadastrando um novo associado");

        Optional<AssociateEntity> associateEntity = this.associateRepository.findByCpf(associateRequest.getCpf());
        if (associateEntity.isPresent()){
            log.info("Cpf ja cadastrado na base de dados, favor verifique o cpf e tente novamente");
            throw new ObjectAlreadyExists("Cpf ja cadastrado na base de dados, favor verifique o cpf e tente novamente");
        }

        AssociateEntity associate = AssociateEntity.builder().build();
        convertions.convertRequestToEntity(associateRequest, associate);
        AssociateEntity entitySave = this.associateRepository.save(associate);

        AssociateResponse associateResponse = AssociateResponse.builder().build();
        return convertions.convertEntityToResponse(entitySave, associateResponse);
    }

    public List<AssociateEntity> listAllAssociates() {
        return associateRepository.findAll();
    }

    public UpdateAssociateResponse updateAssociate(final Long idAssociate, UpdateAssociateRequest request) throws BadRequestException {

        AssociateEntity entity = this.associateRepository.findById(idAssociate).orElse(null);

        if (ObjectUtils.isEmpty(entity)){
            log.info("Idt não encontrado na base de dados, favor inserir um idt valido e tenatar noavamente");
            throw new BadRequestException("Idt não encontrado na base de dados, favor inserir um idt valido e tenatar noavamente");
        }

        convertions.updateConvertRequestToEntiy(request, entity);
        AssociateEntity entitySave = this.associateRepository.save(entity);

        UpdateAssociateResponse response = UpdateAssociateResponse.builder().build();
        convertions.updateConvertEntityToResponse(entitySave, response);

        return response;
    }

    public void inactivatingAssociated(final Long idAssociate)throws BadRequestException{

        log.info("Inativando um associado");
        Optional <AssociateEntity> entityAssociate = this.associateRepository.findById(idAssociate);

        if(!entityAssociate.isPresent()){
            log.info("Idt não encontrado na base de dados");
            throw new BadRequestException("Idt não encontrado na base de dados");

        } else if (entityAssociate.get().getStatusAssociate().equals(StatusAssociate.INACTIVE)) {
            log.info("Usuario não pode ser inativado, pois já consta como inativo na base de dados");
            throw new BadRequestException("Usuario não pode ser inativado, pois já consta como inativo na base de dados");
        }
        entityAssociate.get().setStatusAssociate(StatusAssociate.INACTIVE);
        entityAssociate.get().setDateUpdate(ConverterUtil.nowTime());
        this.associateRepository.save(entityAssociate.get());
    }
}