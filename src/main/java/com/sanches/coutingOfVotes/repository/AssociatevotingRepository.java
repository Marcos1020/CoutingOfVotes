package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.AssociateVotingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatevotingRepository extends JpaRepository<AssociateVotingEntity,Long> {
}