package com.loy.t1aop.validation.impl;

import com.loy.t1aop.exception.MailValidateException;
import com.loy.t1aop.validation.AnnotationParamValidator;
import com.loy.t1aop.validation.annotation.Mail;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

@Component
public class MailValidationImpl implements AnnotationParamValidator {

    private static final String DEFAULT_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static Pattern DEFAULT_EMAIL_PATTERN = Pattern.compile(DEFAULT_EMAIL_REGEX);

    private static boolean isCompile = false; //Производилась ли компиляция нового выражения (сделано для оптимизации)

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return Mail.class;
    }

    @Override
    public void validate(Object param, Annotation annotation) {
        Mail annotationMail = (Mail) annotation;
        String regExp = annotationMail.regexp();
        if (param instanceof String mail) {
            if (!regExp.isEmpty() && !isCompile) {
                DEFAULT_EMAIL_PATTERN = Pattern.compile(regExp);
                isCompile = true;
            }
            if (!DEFAULT_EMAIL_PATTERN.matcher(mail).matches())
                throw new MailValidateException("Маил не удовлетворяет регулярному выражению");
        } else
            throw new MailValidateException("Параметр " + param + " не соответствует типу String");
    }
}
