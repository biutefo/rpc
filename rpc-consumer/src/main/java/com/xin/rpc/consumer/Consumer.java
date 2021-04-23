package com.xin.rpc.consumer;

import com.xin.rpc.api.HelloService;
import com.xin.rpc.api.Person;
import com.xin.rpc.client.RpcProxy;
import com.xin.rpc.registry.ServiceDiscovery;
import com.xin.rpc.registry.zk.ZKServiceDiscoveryImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description : service consumer
 * @Author : xin
 * @Created : 2021-04-23 3:30 下午
 */
@Configuration
@ComponentScan
@SuppressWarnings("all")
public class Consumer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Consumer.class);
        RpcProxy rpcProxy = ac.getBean(RpcProxy.class);

        //1
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result1 = helloService.hello("world");
        System.out.println(result1);
        String result2 = helloService.hello(new Person("xin", "li"));
        System.out.println(result2);

        //2
        HelloService helloService2_0 = rpcProxy.create(HelloService.class, "2.0");
        String result3 = helloService2_0.hello("世界");
        System.out.println(result3);

        //3
        int rpcTime = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(rpcTime);
        ExecutorService executorService = Executors.newFixedThreadPool(6);//不要大约server的worker线程，小一点异常少一些

        long start = System.currentTimeMillis();

        for (int i = 0; i < rpcTime; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    String result = helloService.hello("xin" + finalI);
                    System.out.println(result);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        await(countDownLatch);
        long end = System.currentTimeMillis();
        System.out.println("cost【" + (end - start) + "】millis");
    }

    private static void await(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            await(countDownLatch);
        }
    }

    @Bean
    public ServiceDiscovery serviceRegistry() {
        return new ZKServiceDiscoveryImpl("127.0.0.1:2181");
    }

    @Bean
    public RpcProxy rpcProxy(ServiceDiscovery serviceDiscovery) {
        return new RpcProxy(serviceDiscovery);
    }
}
