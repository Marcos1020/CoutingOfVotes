package com.sanches.coutingOfVotes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NEW_SUBJECT_VOTING_TB")
public class NewSubjectVotingEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_NEW_SUBJECT_VOTING")
    private Long idStartVoting;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT")
    private SubjectEntity subject;

    @Column(name = "DT_REGISTER")
    private LocalDateTime dateRegister;

    @Column(name = "DT_END")
    private LocalDateTime dateEnd;
}