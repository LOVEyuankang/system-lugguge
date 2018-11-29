package com.net.metadata.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMQ 消息发送类
 */
@Component
public class MessageSend {
    private Logger logger = LoggerFactory.getLogger(MessageSend.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier("queue1")
    private Queue queue1;
    @Autowired
    @Qualifier("queue2")
    private Queue queue2;
    @Autowired
    @Qualifier("topic1")
    private Topic topic1;
    @Autowired
    @Qualifier("topic2")
    private Topic topic2;

    public void sendQueue1(String message){
        logger.info("queue-1消息发送，通讯内容：{}", message);
        jmsMessagingTemplate.convertAndSend(queue1, message);
    }

    public void sendQueue2(String message){
        logger.info("queue-2消息发送，通讯内容：{}", message);
        jmsMessagingTemplate.convertAndSend(queue2, message);
    }

    //每隔2s时间向队列发送消息
//    @Scheduled(fixedDelay=1000)
    private void send1() {
        String msgString = System.currentTimeMillis()+" : queue-1";
        jmsMessagingTemplate.convertAndSend(queue1,msgString);
        logger.info("queue-1消息发送，通讯内容：{}",msgString);
    }

    //每隔2s时间向队列发送消息
//    @Scheduled(fixedDelay=1000)
    private void send2() {
        String msgString = System.currentTimeMillis()+" : queue-2";
        jmsMessagingTemplate.convertAndSend(queue2,msgString);
        logger.info("queue-2消息发送，通讯内容：{}",msgString);
    }

    //每隔2s时间向队列发送消息
//    @Scheduled(fixedDelay=1000)
    private void receive1() {
        String msgString = System.currentTimeMillis()+" : topic-1";
        jmsMessagingTemplate.convertAndSend(topic1,msgString);
        logger.info("topic-1消息发送，通讯内容：{}",msgString);
    }

    //每隔2s时间向队列发送消息
//    @Scheduled(fixedDelay=1000)
    private void receive2() {
        String msgString = System.currentTimeMillis()+" : topic-2";
        jmsMessagingTemplate.convertAndSend(topic2,msgString);
        logger.info("topic-2消息发送，通讯内容：{}",msgString);
    }
}
