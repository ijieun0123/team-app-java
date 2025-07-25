package com.example.team_app_java.global.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)  // 파라미터에 붙일 수 있게
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
}
