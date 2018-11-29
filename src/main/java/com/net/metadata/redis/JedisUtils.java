package com.net.metadata.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

/**
 * Description: redis做mybatis二级缓存工具类
 */
@Component
public class JedisUtils {

    @Autowired
    public JedisCluster jedisCluster;

    private static String PREFIX="FX2_CACHE";

    private static JedisUtils jedisUtils;

    @PostConstruct
    public void init() {
        jedisUtils=this;
        jedisUtils.jedisCluster=this.jedisCluster;
    }

    public static void set(Object key, Object value) {
        try {
            jedisUtils.jedisCluster.hset(SerializingUtils.serialize(PREFIX),SerializingUtils.serialize(key), SerializingUtils.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object get(Object key) {
        try {
            byte[] keyBytes = SerializingUtils.serialize(key);
            byte[] prefixBytes = SerializingUtils.serialize(PREFIX);
            if (jedisUtils.jedisCluster.hexists(prefixBytes, keyBytes)) {
                return SerializingUtils.deserialize(jedisUtils.jedisCluster.hget(prefixBytes,keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void del(Object key) {
        try {
            byte[] prefixBytes = SerializingUtils.serialize(PREFIX);
            jedisUtils.jedisCluster.hdel(prefixBytes,SerializingUtils.serialize(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        byte[] prefixBytes = SerializingUtils.serialize(PREFIX);
        jedisUtils.jedisCluster.del(prefixBytes);
    }

    public static int getSize() {
        return jedisUtils.jedisCluster.dbSize().intValue();
    }
}
