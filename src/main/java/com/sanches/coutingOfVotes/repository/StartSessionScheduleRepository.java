package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.StartVotingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartSessionScheduleRepository extends JpaRepository<StartVotingEntity ,Long> {

}