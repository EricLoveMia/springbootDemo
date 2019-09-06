package cn.eric.springbootdemo.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: NotRepeatSubmit
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 11:46
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRepeatSubmit {

    /** 过期时间 单位毫秒 **/
    long value() default 5000;
}
