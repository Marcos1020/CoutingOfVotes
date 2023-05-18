package com.sanches.coutingOfVotes.convertions;

import com.sanches.coutingOfVotes.controller.request.ScheduleRequest;
import com.sanches.coutingOfVotes.controller.request.UpdateScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.ScheduleResponse;
import com.sanches.coutingOfVotes.controller.response.UpdateScheduleResponse;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleConvertions {

    public static void converterRequestToEntity(ScheduleRequest request, ScheduleEntity entity) {
        entity.setScheduleName(request.getScheduleName());
        entity.setDescription(request.getDescription());
        entity.setStatus(ScheduleStatus.STILL_NOT_VOTED);
        entity.setDateRegister(ConverterUtil.nowTime());
    }

    public static void convertEntityTOResponse(ScheduleEntity entitySave, ScheduleResponse response) {
        response.setIdSchedule(entitySave.getIdSchedule());
        response.setDescription(entitySave.getDescription());
        response.setScheduleName(entitySave.getScheduleName());
        response.setDateRegister(entitySave.getDateRegister());
        response.setStatus(entitySave.getStatus());
    }

    public static void UpdateConvertEntityToRequest(UpdateScheduleRequest request, ScheduleEntity entity) {
        entity.setScheduleName(request.getScheduleName());
        entity.setDescription(request.getDescription());
        entity.setDateUpdate(ConverterUtil.nowTime());
        entity.setStatus(ScheduleStatus.STILL_NOT_VOTED);
    }

    public static void updateConvertEntitySaveToResponse(ScheduleEntity entitySave, UpdateScheduleResponse response) {
        response.setScheduleName(entitySave.getScheduleName());
        response.setIdSchedule(entitySave.getIdSchedule());
        response.setDescription(entitySave.getDescription());
        response.setStatusAssociate(entitySave.getStatus());
        response.setDateUpadate(ConverterUtil.nowTime());
    }
}
