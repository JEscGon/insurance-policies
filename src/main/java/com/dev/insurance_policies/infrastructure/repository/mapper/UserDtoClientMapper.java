package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.User;
import com.dev.insurance_users.generated.client.model.UserClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoClientMapper {

    User fromDtoToDomain(UserClientDto userDto);
    UserClientDto fromDomainToDto(User userDomain);

}
