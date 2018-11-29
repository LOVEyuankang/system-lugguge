package com.net.metadata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.net.metadata.mapper")
@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理
@EnableScheduling    	// 开启调度任务支持
@EnableCaching        	// 开启缓存
@EnableAsync        	// 开启异步任务支持
@EnableJms            	// 开启ActiveMQ
public class SystemLuggugeApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SystemLuggugeApplication.class, args);
	}
}
