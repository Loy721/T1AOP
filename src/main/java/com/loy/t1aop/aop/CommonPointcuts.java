package com.loy.t1aop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcuts {

    @Pointcut("within(com.loy.t1aop.service..*)")
    public void serviceLayer() {}
}
