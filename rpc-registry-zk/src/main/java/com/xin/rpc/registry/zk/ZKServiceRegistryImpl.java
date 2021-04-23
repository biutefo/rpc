package com.xin.rpc.registry.zk;

import com.xin.rpc.registry.ServiceRegistry;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : zk服务注册实现
 * @Author : xin
 * @Created : 2021-04-23 1:42 下午
 */
public class ZKServiceRegistryImpl implements ServiceRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKServiceRegistryImpl.class);

    private final String zkAddress;

    private ZkClient zkClient;

    public ZKServiceRegistryImpl(String zkAddress) {
        this.zkAddress = zkAddress;
        init();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        // 创建 registry 节点（持久）
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registryPath)) { // 未注册过该rpc服务端注册服务根节点
            zkClient.createPersistent(registryPath);
            LOGGER.debug("create registry node: {}", registryPath);
        }
        // 创建 service 节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) { // 为创建过改服务提供者根节点
            zkClient.createPersistent(servicePath);
            LOGGER.debug("create service node: {}", servicePath);
        }
        // 创建 address 节点（临时）
        String addressPath = servicePath + "/address-"; // 在改服务下注册当前地址为服务提供者
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        LOGGER.debug("create address node: {}", addressNode);
    }

    public void init() {
        // 创建 ZooKeeper 客户端
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        LOGGER.debug("connect zookeeper");
    }

    public void destroy() {
        zkClient.close();
    }

}
