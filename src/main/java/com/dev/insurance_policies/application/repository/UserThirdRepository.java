package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.UserThird;

import java.util.List;

public interface UserThirdRepository {
    void deleteThirdUserById(Long userId);
    List<UserThird> findAllThirdUsers();
    UserThird findThirdUserById(Long id);
    List<Integer> saveThirdUser(List<UserThird> usersThird);
    void updateThirdUser(Long id, UserThird userThird);
}
