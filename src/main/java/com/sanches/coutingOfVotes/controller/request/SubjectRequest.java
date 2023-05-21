package com.sanches.coutingOfVotes.controller.request;

import com.sanches.coutingOfVotes.statusenum.SubjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectRequest {

    private String subject;

    private String description;

    private SubjectStatus status;
}