package com.xin.rpc.provider;

import com.xin.rpc.registry.ServiceRegistry;
import com.xin.rpc.registry.zk.ZKServiceRegistryImpl;
import com.xin.rpc.server.RpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description : 服务提供者
 * @Author : xin
 * @Created : 2021-04-23 2:37 下午
 */
@Configuration
@ComponentScan
public class Provider {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Provider.class);
    }

    @Bean
    public ServiceRegistry serviceRegistry() {
        return new ZKServiceRegistryImpl("127.0.0.1:2181");
    }

    @Bean
    public RpcServer rpcServer(ServiceRegistry serviceRegistry) {
        return new RpcServer("127.0.0.1:8000", serviceRegistry);
    }

}
