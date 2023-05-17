package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.ScheduleEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ComponentScan
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>{

    Optional <ScheduleEntity> findByScheduleName(final String scheduleName);
}
