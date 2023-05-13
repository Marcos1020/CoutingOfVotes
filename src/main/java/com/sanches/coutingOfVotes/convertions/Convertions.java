package com.sanches.coutingOfVotes.convertions;

import com.sanches.coutingOfVotes.controller.request.ScheduleRequest;
import com.sanches.coutingOfVotes.controller.response.ScheduleResponse;
import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.status.ScheduleStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Convertions {

    public static void converterRequestToEntity(ScheduleRequest request, ScheduleEntity entity) {
        entity.setScheduleName(request.getScheduleName());
        entity.setDescription(request.getDescription());
        entity.setStatus(ScheduleStatus.STILLNOTVOTED);
        entity.setDateRegister(ConverterUtil.nowTime());
    }

    public static void convertEntityTOResponse(ScheduleEntity entitySave, ScheduleResponse response) {
        response.setIdSchedule(entitySave.getIdSchedule());
        response.setDescription(entitySave.getDescription());
        response.setScheduleName(entitySave.getScheduleName());
        response.setDateRegister(entitySave.getDateRegister());
        response.setStatus(entitySave.getStatus());
    }
}
