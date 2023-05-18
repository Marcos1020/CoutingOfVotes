package com.sanches.coutingOfVotes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import com.sanches.coutingOfVotes.utils.DateAndTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SCHEDULE_TB")
public class ScheduleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_SCHEDULE")
    private Long idSchedule;

    @Column(name = "SCHEDULE_NAME")
    @NotEmpty(message = "Campo nome pauta não pode ser vazio")
    private String scheduleName;

    @Column(name = "DESCRIPTION")
    @NotEmpty(message = "Campo descricao não pode ser vazio")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_REGISTER")
    private Date dateRegister;

    @JsonDeserialize(using = DateAndTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConverterUtil.FORMATO_DATA)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_UPDATE")
    private Date dateUpdate;
}