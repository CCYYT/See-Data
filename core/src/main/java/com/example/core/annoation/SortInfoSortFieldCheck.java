package com.example.core.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解用在方法上
@Target(ElementType.METHOD)
//@Retention作用是定义被它所注解的注解保留多久，RUNTIME运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface SortInfoSortFieldCheck {
}
