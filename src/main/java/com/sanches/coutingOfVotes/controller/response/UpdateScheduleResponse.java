package com.sanches.coutingOfVotes.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import com.sanches.coutingOfVotes.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateScheduleResponse {

    private Long idSchedule;

    private String scheduleName;

    private String description;

    private ScheduleStatus statusAssociate;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    private Date dateUpadate;
}