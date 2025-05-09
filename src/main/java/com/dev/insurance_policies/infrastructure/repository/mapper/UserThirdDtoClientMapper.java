package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.UserThird;
import com.dev.insurance_users.generated.client.model.ThirdPartyUserClientDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserThirdDtoClientMapper {

    UserThird fromDtoToDomain(ThirdPartyUserClientDto userDto);
    ThirdPartyUserClientDto fromDomainToDto(UserThird userDomain);

}
