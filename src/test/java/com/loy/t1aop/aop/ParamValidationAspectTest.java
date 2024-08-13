package com.loy.t1aop.aop;

import com.loy.t1aop.exception.ValidateException;
import com.loy.t1aop.repository.UserRepository;
import com.loy.t1aop.service.UserService;
import com.loy.t1aop.service.impl.UserServiceImpl;
import com.loy.t1aop.validation.ParamValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.annotation.Annotation;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParamValidationAspectTest {

    @Mock
    private ParamValidator paramValidator;

    @Mock
    private UserRepository userRepository;


    private UserService userService;

    @BeforeEach
    void beforeEach() {
        final UserService target = new UserServiceImpl(userRepository);

        final AspectJProxyFactory proxyFactory = new AspectJProxyFactory(target);
        proxyFactory.addAspect(new ParamValidationAspect(paramValidator));

        userService = proxyFactory.getProxy();
    }

    @Test
    public void findByMail_whenCalledWithParam_callsParamValidatorValidate() {
        String param = "123";
        when(userRepository.findByMail(param)).thenReturn(Optional.empty());

        userService.findByMail(param);

        verify(paramValidator).validate(ArgumentMatchers.any(), ArgumentMatchers.any(Annotation[].class));
    }

    @Test
    public void findByMail_whenCalledWithNullParam_throwsValidateException() {
        Assertions.assertThrows(ValidateException.class, () -> userService.findByMail(null));
    }
}
