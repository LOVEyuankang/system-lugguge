package com.net.metadata.websocket;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket处理器, Netty一款高性能的NIO网络编程框架
 */
@ServerEndpoint
@Component
public class WebSocketService  {
    private Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    //concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet<Session>();

    private static List<Session> sesList = new ArrayList<Session>();         //存放所有连接到服务器的管道
    private static List<String> userList  = new ArrayList<String>();        //存放所有的用户
    private static Map<String,Session> map = new HashMap<String, Session>();        //存 放所有用户及管道信息的集合 ：key:用户名,value:管道

    /**
     * 当有新的WebSocket连接进入时，对该方法进行回调
     * 注入参数的类型:Session、HttpHeaders、ParameterMap
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) throws IOException {
        logger.info("WebSocket连接通道开通");
        webSocketSet.add(session);
        sesList.add(session);
    }

    /**
     * 当有WebSocket连接关闭时，对该方法进行回调
     * 注入参数的类型:Session
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("WebSocket连接通道关闭");
        //将管道从管道的集合移除
        webSocketSet.remove(session);
        sesList.remove(session);
    }

    /**
     * 当有WebSocket抛出异常时，对该方法进行回调
     * 注入参数的类型:Session、Throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        if(session.isOpen()){
            session.close();
        }
        logger.error("WebSocket连接通道错误，原因: "+throwable.getMessage());
    }

    /**
     * 当接收到字符串消息时，对该方法进行回调
     * 注入参数的类型:Session、String
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        try{
            if(session.isOpen()){
                logger.info("实时接收消息: "+message);
                session.sendText(message);
            } else {
                logger.error("WebSocket连接未打开，系统消息推送失败: {}",message);
            }
        } catch (Exception e){
            logger.error("系统消息推送失败："+message+", 失败原因: {}" , e.getMessage());
        }
    }

    public void setMessage(String message){
        sesList.forEach(s -> onMessage(s, message));
    }

    /**
     * 当接收到二进制消息时，对该方法进行回调
     * 注入参数的类型:Session、byte[]
     */
    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        logger.info("实时接收二进制消息: "+String.valueOf(bytes));
        session.sendBinary(bytes);
    }

    /**
     * 当接收到Netty的事件时，对该方法进行回调
     * 注入参数的类型:Session、Object
     */
    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    logger.info("读空闲(服务器端)");
                    break;
                case WRITER_IDLE:
                    logger.info("写空闲(客户端)");
                    break;
                case ALL_IDLE:
                    logger.info("读写空闲");
                    break;
                default:
                    break;
            }
        }
    }
}
