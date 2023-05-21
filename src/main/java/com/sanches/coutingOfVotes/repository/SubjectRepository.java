package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.SubjectEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ComponentScan
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long>{

    Optional <SubjectEntity> findBySubject(final String subject);
}
