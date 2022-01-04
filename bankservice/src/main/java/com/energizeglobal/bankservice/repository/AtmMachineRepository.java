package com.energizeglobal.bankservice.repository;

import com.energizeglobal.bankservice.domain.AtmMachineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtmMachineRepository extends CrudRepository<AtmMachineEntity,Long> {

    Optional<AtmMachineEntity> findAtmMachineEntitiesByUsernameAndPassword(String username, String password);

    AtmMachineEntity findByUsername(String username);
}
