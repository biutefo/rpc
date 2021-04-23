package com.xin.rpc.provider;

import com.xin.rpc.api.HelloService;
import com.xin.rpc.api.Person;
import com.xin.rpc.server.Service;

/**
 * @Author : xin
 * @Created : 2021-04-23 3:01 下午
 */
@Service(value = HelloService.class, version = "2.0")
public class CNHelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}
