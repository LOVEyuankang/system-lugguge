package com.net.metadata.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Redis操作类
 */
@Service(value = "redisService")
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;  //用来操作字符串类型，序列化类：StringRedisSerializer
    @Autowired
    private RedisTemplate redisTemplate;    //用来操作对象类型，序列化类：JdkSerializationRedisSerializer

//    redisTemplate.opsForValue();　　//操作字符串
//    redisTemplate.opsForHash();　　 //操作hash
//    redisTemplate.opsForList();　　 //操作list
//    redisTemplate.opsForSet();　　  //操作set
//    redisTemplate.opsForZSet();　 　//操作有序set

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setList(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public String getList(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public void setMap(String key, Map map){
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    public Map getMap(String key){
        return stringRedisTemplate.opsForHash().entries(key);
    }

    public void setSet(String key, String... value){
        stringRedisTemplate.opsForSet().add(key, value);
    }

    public Set getSet(String key){
        return stringRedisTemplate.opsForSet().members(key);
    }

    public void setZSet(String key, Set set){
        stringRedisTemplate.opsForZSet().add(key, set);
    }

    //(从大到小)顺序排列,返回对象集合
    public Set getZSetDESC(String key, long start, long end){
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    //(从小到大)顺序排列,返回对象集合
    public Set getZSetACS(String key, long start, long end){
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }




}
