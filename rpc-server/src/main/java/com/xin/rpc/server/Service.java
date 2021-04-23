package com.xin.rpc.server;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description : rpc service
 * @Author : xin
 * @Created : 2021-04-23 2:09 下午
 */
@Target({ElementType.TYPE}) // 标注在类上
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
@Component // 自动注册bean
public @interface Service {
    /**
     * 服务接口类
     */
    Class<?> value();

    /**
     * 服务版本号
     */
    String version() default "";
}
