package com.sanches.coutingOfVotes.controller.request;

import com.sanches.coutingOfVotes.entity.AssociateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingAssociateRequest {

    private List<AssociateEntity> idAssociate;

    private String  cpf;

    private boolean Vote;
}