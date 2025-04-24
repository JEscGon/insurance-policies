package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.User;
import com.dev.insurance_policies.application.repository.UserRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.UserDtoClientMapper;
import com.dev.insurance_users.generated.client.api.UsersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryRestClientImpl implements UserRepository {

    private final UsersApi usersApi;
    private final UserDtoClientMapper userDtoClientMapper;

    @Override
    public User findById(Long userId) {
        return userDtoClientMapper.fromDtoToDomain(usersApi.findById(Long.valueOf(userId)));
    }
    @Override
    public void deleteUserById(Long id){
        usersApi.deleteUserById(id);
    }
    @Override
    public List<User> findAllUsers(){
        return usersApi.findAllUsers().stream()
            .map(userDtoClientMapper::fromDtoToDomain)
            .toList();
    }
    @Override
    public User getUserByDni(String dni) {
         return userDtoClientMapper.fromDtoToDomain(usersApi.getUserByDni(dni));
    }
    @Override
    public User getUserByEmail(String email) {
        return userDtoClientMapper.fromDtoToDomain(usersApi.getUserByEmail(email));
    }
    @Override
    public void save(User user) {
        usersApi.save(userDtoClientMapper.fromDomainToDto(user));
    }
    @Override
    public void updateUser(Long id, User user) {
        usersApi.updateUser(id, userDtoClientMapper.fromDomainToDto(user));
    }

}
