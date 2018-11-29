package com.net.metadata.redis;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

import java.util.concurrent.locks.ReadWriteLock;
/**
 * Description: 二级缓存实现
 * Cache为缓存接口,给缓存供应商的SPI(Service Provider Interface)
 * Cache接口的实现类必须有一个具有String类型参数的构造方法，该参数作为实现类对象的id，对其进行唯一标识
 */
public class MybatisRedisCache implements Cache {

    private String id;

    public MybatisRedisCache(String id) {
        this.id = id;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        JedisUtils.clear();
    }

    /**
     * 获取缓存对象的唯一标识
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 从缓存对象中获取key对应的value
     */
    @Override
    public Object getObject(Object key) {
        return JedisUtils.get(key);
    }

    /**
     * 获取读写锁 可选的方法，从3.2.6起这个方法不再被框架核心调用 任何需要的锁，都必须由缓存供应商提供
     */
    @Override
    public ReadWriteLock getReadWriteLock() {

        return null;
    }

    /**
     * 获取缓存对象中存储的键/值对的数量 可选的方法，没有被框架核心调用
     */
    @Override
    public int getSize() {
        return JedisUtils.getSize();
    }

    /**
     * 保存key/value到缓存对象中 key可以是任何对象
     */
    @Override
    public void putObject(Object key, Object value) {
        JedisUtils.set(key, value);
    }

    /**
     * 可选的方法，没有被核心框架调用，移除key对应的value
     */
    @Override
    public Object removeObject(Object key) {

        return null;
    }

    /**
     * 重新equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (getId() == null)
            throw new CacheException("Cache instances require an ID.");
        if (this == o)
            return true;
        if (!(o instanceof Cache))
            return false;

        Cache otherCache = (Cache) o;
        return getId().equals(otherCache.getId());
    }

    /**
     * 重新hashCode方法
     */
    @Override
    public int hashCode() {
        if (getId() == null)
            throw new CacheException("Cache instances require an ID.");
        return getId().hashCode();
    }

}
