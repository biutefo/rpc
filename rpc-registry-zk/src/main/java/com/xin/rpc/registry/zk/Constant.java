package com.xin.rpc.registry.zk;

/**
 * @Description : 常量
 * @Author : xin
 * @Created : 2021-04-23 12:00 下午
 */
public interface Constant {
    /**
     * zk session超时时间
     */
    int ZK_SESSION_TIMEOUT = 5000;
    /**
     * zk 连接超时时间
     */
    int ZK_CONNECTION_TIMEOUT = 1000;
    /**
     * zk服务注册根路径
     */
    String ZK_REGISTRY_PATH = "/registry";
}
