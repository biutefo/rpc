package com.xin.rpc.registry;

/**
 * @Description : 服务发现接口
 * @Author : xin
 * @Created : 2021-04-23 11:59 上午
 */
public interface ServiceDiscovery {
    /**
     * 根据服务名称查找服务地址
     *
     * @param serviceName 服务名称
     * @return 服务地址
     */
    String discover(String serviceName);
}