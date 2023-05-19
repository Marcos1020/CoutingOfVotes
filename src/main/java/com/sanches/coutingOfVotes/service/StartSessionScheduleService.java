package com.sanches.coutingOfVotes.service;

import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import com.sanches.coutingOfVotes.entity.StartVotingEntity;
import com.sanches.coutingOfVotes.exception.BadRequestException;
import com.sanches.coutingOfVotes.repository.ScheduleRepository;
import com.sanches.coutingOfVotes.repository.StartSessionScheduleRepository;
import com.sanches.coutingOfVotes.statusenum.ScheduleStatus;
import com.sanches.coutingOfVotes.statusenum.StartSessionVoting;
import com.sanches.coutingOfVotes.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StartSessionScheduleService {

    private StartSessionScheduleRepository repository;

    private ScheduleRepository scheduleRepository;

    @Autowired
    public StartSessionScheduleService(StartSessionScheduleRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    public void startVotIngSession(final Long idSchedule) throws BadRequestException {

        log.info("inicando uma nova sessão de votação");
        Optional<ScheduleEntity> entity = this.scheduleRepository.findById(idSchedule);

        if (ObjectUtils.isEmpty(entity)) {
            log.info("Identificador não pode ser nulo");
            throw new BadRequestException("Identificador não pode ser nulo");

        } else if (entity.isPresent()) {
            log.info("essa pauta ja foi aberta para a votacao");
            throw new BadRequestException("essa pauta ja foi aberta para a votacao");
        }

        StartVotingEntity validationEntity = StartVotingEntity.builder().build();
        validationEntity.setSchedule(entity.get());
        validationEntity.setTituloSchedule(entity.get().getScheduleName());
        validationEntity.setDateRegisterOpenVotings(ConverterUtil.nowTime());
        validationEntity.setStatus(StartSessionVoting.OPEN_VOTING);
        this.repository.save(validationEntity);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {

            if (checkIfEdtaWithinTimeVotingLimit(validationEntity) == true ) {
                entity.get().setStatus(ScheduleStatus.VOTED);
                validationEntity.setStatus(StartSessionVoting.VOTING_CLOSED);

            }else {

            }
        }, 10, TimeUnit.MINUTES);

        executorService.shutdown();
    }

    private static boolean checkIfEdtaWithinTimeVotingLimit(StartVotingEntity validationEntity) {
        Date now = new Date();
        Date registrationTime = validationEntity.getDateRegisterOpenVotings();

        String diffInMillis = String.valueOf(now.getTime() - registrationTime.getTime());
        String minutesDiff = String.valueOf(TimeUnit.MINUTES.convert(Long.parseLong(diffInMillis), TimeUnit.MINUTES)) ;
        return minutesDiff.equals(10);
    }
}