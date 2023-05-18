package com.sanches.coutingOfVotes.convertions;

import com.sanches.coutingOfVotes.controller.request.AssociateRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateAssociateRequest;
import com.sanches.coutingOfVotes.controller.response.AssociateResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateAssociateResponse;
import com.sanches.coutingOfVotes.entity.AssociateEntity;
import com.sanches.coutingOfVotes.statusenum.StatusAssociate;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssociateConvertions {

    public void convertRequestToEntity(AssociateRequest associateRequest, AssociateEntity associate) {
        associate.setCpf(associateRequest.getCpf());
        associate.setName(associateRequest.getName());
        associate.setDateRegister(ConverterUtil.nowTime());
        associate.setStatusAssociate(StatusAssociate.ACTIVE);
    }

    public AssociateResponse convertEntityToResponse(AssociateEntity entitySave, AssociateResponse associateResponse) {
        associateResponse.setIdAssociate(entitySave.getIdAssociate());
        associateResponse.setCpf(entitySave.getCpf());
        associateResponse.setName(entitySave.getName());
        associateResponse.setStatusAssociate(entitySave.getStatusAssociate());
        associateResponse.setDateRegister(entitySave.getDateRegister());
        return associateResponse;
    }

    public void updateConvertRequestToEntiy(UpdateAssociateRequest request, AssociateEntity associateEntity) {
        associateEntity.setName(request.getName());
        associateEntity.setCpf(request.getCpf());
        associateEntity.setDateUpdate(ConverterUtil.nowTime());
    }

    public void updateConvertEntityToResponse(AssociateEntity entitySave, UpdateAssociateResponse response) {
        response.setIdAssociate(entitySave.getIdAssociate());
        response.setStatusAssociate(entitySave.getStatusAssociate());
        response.setName(entitySave.getName());
        response.setCpf(entitySave.getCpf());
        response.setDateUpdate(entitySave.getDateUpdate());
    }

}