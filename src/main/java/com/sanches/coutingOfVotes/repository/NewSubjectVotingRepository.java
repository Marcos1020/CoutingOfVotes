package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.NewSubjectVotingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewSubjectVotingRepository extends JpaRepository<NewSubjectVotingEntity, Long> {
}