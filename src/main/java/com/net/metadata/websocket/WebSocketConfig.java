package com.net.metadata.websocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * WebSocket服务器
 */
//@EnableWebSocket    // 开启WebSocket
@Configuration
public class WebSocketConfig{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);

    @Value("${netty.websocket.url}")
    public String url;
    @Value("${netty.websocket.path}")
    public String path;
    @Value("${netty.websocket.port}")
    public Integer port;

    /**
     * 检测服务类实现
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
