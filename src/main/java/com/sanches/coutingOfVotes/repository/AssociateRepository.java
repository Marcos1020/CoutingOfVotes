package com.sanches.coutingOfVotes.repository;

import com.sanches.coutingOfVotes.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

   Optional<AssociateEntity> findByCpf(final String cpf);
}