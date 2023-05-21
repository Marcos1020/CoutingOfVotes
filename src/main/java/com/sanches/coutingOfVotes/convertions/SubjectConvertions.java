package com.sanches.coutingOfVotes.convertions;

import com.sanches.coutingOfVotes.controller.request.SubjectRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.SubjectResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateScheduleResponse;
import com.sanches.coutingOfVotes.entity.SubjectEntity;
import com.sanches.coutingOfVotes.statusenum.SubjectStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubjectConvertions {

    public static void converterRequestToEntity(SubjectRequest request, SubjectEntity entity) {
        entity.setSubject(request.getSubject());
        entity.setDescription(request.getDescription());
        entity.setStatus(SubjectStatus.STILL_NOT_VOTED);
        entity.setDateRegister(ConverterUtil.nowTime());
    }

    public static void convertEntityTOResponse(SubjectEntity entitySave, SubjectResponse response) {
        response.setIdSchedule(entitySave.getIdSubject());
        response.setDescription(entitySave.getDescription());
        response.setSubject(entitySave.getSubject());
        response.setDateRegister(entitySave.getDateRegister());
        response.setStatus(entitySave.getStatus());
    }

    public static void UpdateConvertEntityToRequest(UpdateScheduleRequest request, SubjectEntity entity) {
        entity.setSubject(request.getSubject());
        entity.setDescription(request.getDescription());
        entity.setDateUpdate(ConverterUtil.nowTime());
        entity.setStatus(SubjectStatus.STILL_NOT_VOTED);
    }

    public static void updateConvertEntitySaveToResponse(SubjectEntity entitySave, UpdateScheduleResponse response) {
        response.setSubject(entitySave.getSubject());
        response.setIdSubject(entitySave.getIdSubject());
        response.setDescription(entitySave.getDescription());
        response.setStatusAssociate(entitySave.getStatus());
        response.setDateUpadate(ConverterUtil.nowTime());
    }
}