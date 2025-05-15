package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.UserThird;
import com.dev.insurance_policies.application.repository.UserThirdRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.UserThirdDtoClientMapper;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.model.ThirdPartyUserWrapperClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserThirdRestClientImpl implements UserThirdRepository {

    private final ThirdUsersApi thirdUsersApi;
    private final UserThirdDtoClientMapper userThirdDtoClientMapper;

    @Override
    public void deleteThirdUserById(Long id) {
        thirdUsersApi.deleteThirdUserById(id);
    }
    @Override
    public List<UserThird> findAllThirdUsers() {
        return thirdUsersApi.findAllThirdUsers().getUsers().stream()
            .map(userThirdDtoClientMapper::fromDtoToDomain)
            .toList();
    }
    @Override
    public UserThird findThirdUserById(Long id) {
        return userThirdDtoClientMapper.fromDtoToDomain(thirdUsersApi.findThirdUserById(id));
    }

    @Override
    public List<Integer> saveThirdUser(List<UserThird> usersThird) {
        var userWrapper = new ThirdPartyUserWrapperClientDto();
        usersThird.stream()
                .map(userThirdDtoClientMapper::fromDomainToDto)
                .forEach(userWrapper::addUsersItem);
        return thirdUsersApi.saveThirdUser(userWrapper);
    }

    @Override
    public void updateThirdUser(Long id, UserThird userThird) {
        thirdUsersApi.updateThirdUser(id, userThirdDtoClientMapper.fromDomainToDto(userThird));
    }
}
