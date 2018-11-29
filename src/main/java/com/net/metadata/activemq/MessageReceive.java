package com.net.metadata.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ 消息接收类
 */
@Component
public class MessageReceive {
    private Logger logger = LoggerFactory.getLogger(MessageReceive.class);

    @JmsListener(destination="queue-1")
    public void recieve1(String message) {
        logger.info("queue-1接收消息，详情：{}",message);
    }

    @JmsListener(destination="queue-2")
    public void recieve2(String message) {
        logger.info("queue-2接收消息，详情：{}",message);
    }

    @JmsListener(destination="topic-1", containerFactory="topicListenerContainer")
    public void recieve3(String message) {
        logger.info("topic-1接收消息，详情：{}",message);
    }

    @JmsListener(destination="topic-2", containerFactory="topicListenerContainer")
    public void recieve4(String message) {
        logger.info("topic-2接收消息，详情：{}",message);
    }

}
