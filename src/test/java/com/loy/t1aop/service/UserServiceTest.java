package com.loy.t1aop.service;

import com.loy.t1aop.domain.User;
import com.loy.t1aop.exception.SizeValidateException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByMail_whenEmailTooShort_throwsSizeValidateException() {
        Assertions.assertThrows(SizeValidateException.class, () -> userService.findByMail("123"));
    }

    @Test
    public void findByMail_whenInvalidEmail_throwsSizeValidateException() {
        Assertions.assertThrows(SizeValidateException.class, () -> userService.findByMail("12345"));
    }

    @Test
    @Transactional
    public void  findByMail_whenSaveValidUser_findSuccess() {
        User danil = new User(null, "Danil", "danil@mail.ru", null);
        userService.save(danil);

        Optional<User> byMail = userService.findByMail(danil.getMail());

        Assertions.assertTrue(byMail.isPresent());
    }

    @Test
    public void findByName_whenNameTooShort_throwsSizeValidateException() {
        Assertions.assertThrows(SizeValidateException.class, () -> userService.findByName("dan"));
    }

    @Test
    @Transactional
    public void findByName_whenSaveValidUser_findSuccess() {
        User danil = new User(null, "Danil", "danil@mail.ru", null);
        userService.save(danil);

        Optional<User> byMail = userService.findByName(danil.getName());

        Assertions.assertTrue(byMail.isPresent());
    }
}
