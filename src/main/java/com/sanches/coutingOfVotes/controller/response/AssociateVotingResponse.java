package com.sanches.coutingOfVotes.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociateVotingResponse {

    private Long idAssociate;
    private Long idSubject;
    private String vote;
    private Date dateRegisterOpenVotings;
}