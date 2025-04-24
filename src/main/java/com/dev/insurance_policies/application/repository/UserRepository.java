package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.User;

import java.util.List;

public interface UserRepository {

    User findById(Long userId);
    void deleteUserById(Long id);
    List<User> findAllUsers();
    User getUserByDni(String dni);
    User getUserByEmail(String email);
    void save(User user);
    void updateUser(Long id, User user);
}
