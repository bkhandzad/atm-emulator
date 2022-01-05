package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.AtmMachineEntity;
import com.energizeglobal.infrastructure.exceptin.ServiceException;
import com.energizeglobal.bankservice.repository.AtmMachineRepository;
import com.energizeglobal.bankservice.transformer.AtmMachineTransformer;
import com.energizeglobal.datamodel.AtmMachineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(AtmMachineService.BEAN_NAME)
public class AtmMachineService {
    public static final String BEAN_NAME = "atmMachineService";

    @Autowired
    private AtmMachineRepository atmMachineRepository;

    @Autowired
    private AtmMachineTransformer atmMachineTransformer;

    public AtmMachineEntity findByID(Long id){
        return atmMachineRepository.findById(id).orElseThrow(() ->new ServiceException("Invalid Atm ID "+id+" Not found"));
    }

    public List<AtmMachineDto> findAll() {
        Iterable<AtmMachineEntity> it = atmMachineRepository.findAll();
        List<AtmMachineEntity> atmMachineEntities = new ArrayList<>();
        it.forEach(atmMachineEntities::add);
        return atmMachineEntities.stream().map(entity -> atmMachineTransformer.createValueObjectFromEntity(entity)).collect(Collectors.toList());
    }

    public AtmMachineDto login(String userName, String passWord){
        AtmMachineEntity atmMachineEntity = atmMachineRepository.findAtmMachineEntitiesByUsernameAndPassword(userName,passWord).orElseThrow(() ->new ServiceException("User " +userName+" Password "+passWord+" Not found"));
        AtmMachineDto atmMachine = new AtmMachineDto(atmMachineEntity.getId(),atmMachineEntity.getUsername(),atmMachineEntity.getPassword());
        return atmMachine;
    }

    public AtmMachineEntity save(AtmMachineDto atmMachineDto) {
        atmMachineDto.setInsertDate(LocalDateTime.now());
        atmMachineDto.setModifyDate(null);
        AtmMachineEntity atmMachineEntity = atmMachineTransformer.createEntityFromValueObject(atmMachineDto);
        return atmMachineRepository.save(atmMachineEntity);
    }
}
