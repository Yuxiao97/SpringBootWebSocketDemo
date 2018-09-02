package com.yuxiao.websocket.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class SpringBootWebsocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebsocketDemoApplication.class, args);
	}

    /**
     * 必须，否则无法访问websocket接口
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
