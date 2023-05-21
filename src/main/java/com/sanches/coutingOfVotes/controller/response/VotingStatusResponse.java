package com.sanches.coutingOfVotes.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingStatusResponse {

    private Long IdVoting;
    private String StatusVote;
}