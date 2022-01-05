package com.energizeglobal.bankservice.transformer;

import com.energizeglobal.bankservice.domain.AtmMachineEntity;
import com.energizeglobal.bankservice.dto.AtmMachineDto;
import com.energizeglobal.infrastructure.AbstractTransformer;
import org.springframework.stereotype.Component;

@Component(AtmMachineTransformer.BEAN_NAME)
public class AtmMachineTransformer extends AbstractTransformer<AtmMachineEntity,AtmMachineDto> {
    public static final String BEAN_NAME = "atmMachineTransformer";

    @Override
    public void transformValueObjectToEntity(AtmMachineDto input, AtmMachineEntity output) {
        output.setUsername(input.getUserName());
        output.setPassword(input.getPassWord());
        setSystematicField(input, output);
    }

    @Override
    public void transformEntityToValueObject(AtmMachineEntity input, AtmMachineDto output) {
        output.setUserName(input.getUsername());
        output.setPassWord(input.getPassword());
        setSystematicField(input, output);

    }
}
