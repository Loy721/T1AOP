package com.loy.t1aop.service;

import com.loy.t1aop.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Long id);

    Optional<User> findByMail(String mail);

    User update(User user);

    void delete(User user);
}
