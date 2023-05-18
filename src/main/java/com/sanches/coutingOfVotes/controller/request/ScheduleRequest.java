package com.sanches.coutingOfVotes.controller.request;

import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequest {

    private String scheduleName;

    private String description;

    private ScheduleStatus status;
}