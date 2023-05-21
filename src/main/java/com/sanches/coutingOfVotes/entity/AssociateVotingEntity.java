package com.sanches.coutingOfVotes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import com.sanches.coutingOfVotes.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "START_VOTING_TB")
public class AssociateVotingEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_START_VOTING")
    private Long idStartVoting;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT")
    private NewSubjectVotingEntity subject;

    @ManyToOne
    @JoinColumn(name = "ID_ASSOCIATE")
    private AssociateEntity associates;

    @Column(name = "VOTE" )
    private String vote;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_START_VOTING")
    private Date dateRegisterOpenVotings;
}