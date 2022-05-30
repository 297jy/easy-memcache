# Easy-Memcache
最近在学习Go语言，打算用Go开发一个简单的分布式缓存系统
## 特点
* 能够实现高可用、负载均衡
* 能够动态进行集群的扩容、缩容
* 无主节点，去中心化
* 数据保存在内存中
* 能够避免缓存击穿

## 部署架构
![部署架构图.png](https://s2.loli.net/2022/05/30/SmLZCINfTw1YQUR.png)

## 原理
* 利用zookeeper做配置中心，动态感知其他分布式缓存服务器的上下线
* 利用LVS做统一虚拟ip、负载均衡
* 缓存淘汰策略支持：LRU、LRU-K、FIFO、LFU、2Q、LIRS
* 利用一致性哈希进行数据分布、避免数据倾斜
