package com.net.metadata.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;


/**
 * Redis配置类
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout;
//    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisCluster getJedisCluster() {
        String[] cNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        // 分割出集群节点
        for (String node : cNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

//		GenericObjectPoolConfig config=new GenericObjectPoolConfig();
//        config.setMaxTotal(maxActive);
//        config.setMaxIdle(maxIdle);
//        config.setMinIdle(minIdle);
//		config.setMaxWaitMillis(maxWaitMillis);
//        config.setTestOnBorrow(true);//在获取Jedis连接时，自动检验连接是否可用
//        config.setTestOnReturn(true);//在将连接放回池中前，自动检验连接是否有效
//        config.setTestWhileIdle(true);//自动测试池中的空闲连接是否都是可用连接
//        config.setBlockWhenExhausted(false);//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时,默认true
//        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
//        config.setMinEvictableIdleTimeMillis(60000);
//        config.setTimeBetweenEvictionRunsMillis(30000);//表示idle object evitor两次扫描之间要sleep的毫秒数
//        config.setNumTestsPerEvictionRun(10);//表示idle object evitor每次扫描的最多的对象数

        // 创建集群对象
//        JedisCluster jedisCluster=new JedisCluster(nodes,commandTimeout,config);
        JedisCluster jedisCluster = new JedisCluster(nodes,commandTimeout,timeout,maxIdle,password,jedisPoolConfig);
        return jedisCluster;
    }

    /**
     * 设置数据存入 redis 的序列化方式 </br>
     * redisTemplate 序列化默认使用的jdkSerializeable, 存储二进制字节码, 导致key会出现乱码，所以自定义 序列化类
     *
     * @param redisConnectionFactory
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


}
