package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.UserThird;
import com.dev.insurance_users.generated.client.model.UserClientDto;
import com.dev.insurance_users.generated.client.model.UserThirdClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserThirdDtoClientMapper {

    UserThird fromDtoToDomain(UserThirdClientDto userDto);
    UserThirdClientDto fromDomainToDto(UserThird userDomain);

}
