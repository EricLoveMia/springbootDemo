package com.loveprogrammer.springboot.kafka.service.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OnUnixCondition
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:01
 **/
public class OnUnixCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        return osName.contains("unix") || osName.contains("linux") || osName.contains("Mac OS X");
    }
}
