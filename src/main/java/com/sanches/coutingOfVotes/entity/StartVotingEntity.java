package com.sanches.coutingOfVotes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.coutingOfVotes.statusenum.StartSessionVoting;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import com.sanches.coutingOfVotes.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "START_VOTING_TB")
public class StartVotingEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_START_VOTING")
    private Long idStartVoting;

    @ManyToOne
    @JoinColumn(name = "ID_SCHEDULE")
    private ScheduleEntity Schedule;

    @Column(name = "TITLE_SCHEDULE")
    private String tituloSchedule;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StartSessionVoting status;

    @ManyToMany
    @JoinTable(name = "ASSOCIATE_TB",
            joinColumns = @JoinColumn(name = "ID_START_VOTING"),
            inverseJoinColumns = @JoinColumn(name = "ASSOCIATE_IDS"))
    private List<AssociateEntity> associates;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_START_VOTING")
    private Date dateRegisterOpenVotings;
}