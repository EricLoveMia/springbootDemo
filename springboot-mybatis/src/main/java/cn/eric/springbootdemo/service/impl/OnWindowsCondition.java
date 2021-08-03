package cn.eric.springbootdemo.service.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OnWindowsCondition
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 15:41
 **/
public class OnWindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }

}
