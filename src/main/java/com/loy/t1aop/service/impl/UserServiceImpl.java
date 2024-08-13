package com.loy.t1aop.service.impl;

import com.loy.t1aop.domain.User;
import com.loy.t1aop.repository.UserRepository;
import com.loy.t1aop.service.UserService;
import com.loy.t1aop.validation.annotation.Mail;
import com.loy.t1aop.validation.annotation.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByMail(@Size(min = 12, max = 256) @Mail String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public Optional<User> findByName(@Size(min = 4, max = 64) String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
