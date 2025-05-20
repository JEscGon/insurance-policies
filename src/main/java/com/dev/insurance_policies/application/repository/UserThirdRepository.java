package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.UserThird;

import java.util.List;

public interface UserThirdRepository {
    void deleteThirdUserById(Long userId);
    List<UserThird> findAllThirdUsers();
    UserThird findThirdUserById(Long id);
    void saveThirdUser(UserThird userThird);
    void updateThirdUser(Long id, UserThird userThird);
}
