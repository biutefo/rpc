package com.xin.rpc.registry.zk;

import com.xin.rpc.registry.ServiceDiscovery;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description : zk服务发现实现
 * @Author : xin
 * @Created : 2021-04-23 1:42 下午
 */
public class ZKServiceDiscoveryImpl implements ServiceDiscovery {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKServiceDiscoveryImpl.class);

    private final String zkAddress;
    private ZkClient zkClient;

    public ZKServiceDiscoveryImpl(String zkAddress) {
        this.zkAddress = zkAddress;
        init();
    }

    @Override
    public String discover(String name) {
        // 创建 ZooKeeper 客户端
        LOGGER.debug("connect zookeeper");
        String servicePath = Constant.ZK_REGISTRY_PATH + "/" + name; // zk service 注册节点路径
        if (!zkClient.exists(servicePath)) { // 服务提供者不在线
            throw new RuntimeException(String.format("can not find any service node on path: %s", servicePath));
        }
        List<String> addressList = zkClient.getChildren(servicePath);
        if (CollectionUtils.isEmpty(addressList)) { // 服务提供者数量为0
            throw new RuntimeException(String.format("can not find any address node on path: %s", servicePath));
        }
        // 获取 address 节点
        String address;
        int size = addressList.size();
        if (size == 1) { // 直接返回服务提供者
            // 若只有一个地址，则获取该地址
            address = addressList.get(0);
            LOGGER.debug("get only address node: {}", address);
        } else { // 若存在多个地址，则随机获取一个地址 负载均衡
            address = addressList.get(ThreadLocalRandom.current().nextInt(size));
            LOGGER.debug("get random address node: {}", address);
        }
        // 获取 address 节点的值
        String addressPath = servicePath + "/" + address; // 拼接上服务提供者的地址
        return zkClient.readData(addressPath); //返回服务提供者

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
