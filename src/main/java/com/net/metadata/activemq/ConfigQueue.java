package com.net.metadata.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMQ 配置类
 */
//@Configuration
//@PropertySource("classpath:application.properties")
@Component
public class ConfigQueue {

    @Value("${queue1}")
    private String queue1;
    @Value("${queue2}")
    private String queue2;
    @Value("${topic1}")
    private String topic1;
    @Value("${topic2}")
    private String topic2;

    /**
     * 首先将队列注入到SpringBoot容器中去
     */
    @Bean
    public Queue queue1() {
        return new ActiveMQQueue(queue1);
    }
    @Bean
    public Queue queue2() {
        return new ActiveMQQueue(queue2);
    }

    @Bean
    public Topic topic1() {
        return new ActiveMQTopic(topic1);
    }
    @Bean
    public Topic topic2() {
        return new ActiveMQTopic(topic2);
    }

    /**
     * JmsListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        return topicListenerContainer;
    }
}
