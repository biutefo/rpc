package com.xin.rpc.provider;

import com.xin.rpc.api.HelloService;
import com.xin.rpc.api.Person;
import com.xin.rpc.server.Service;

/**
 * @Author : xin
 * @Created : 2021-04-23 2:59 下午
 */
@SuppressWarnings("all")
@Service(HelloService.class)
public class ENHelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
