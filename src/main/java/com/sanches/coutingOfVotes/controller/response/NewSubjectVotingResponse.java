package com.sanches.coutingOfVotes.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSubjectVotingResponse {

    private Long idStartVoting;
    private Long idSubjecet;
    private LocalDateTime dateRegister;
    private LocalDateTime dateEnd;
}