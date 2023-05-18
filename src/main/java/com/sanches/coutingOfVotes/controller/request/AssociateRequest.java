package com.sanches.coutingOfVotes.controller.request;

import com.sanches.coutingOfVotes.statusenum.StatusAssociate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociateRequest {

    private String name;

    private String cpf;

    private StatusAssociate statusAssociate;
}