package com.yuxiao.websocket.websocket;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Yuxiao
 * @Date: 2018/9/1 10:56
 * @Description:
 */

@Controller
@ServerEndpoint("/websocket")
public class MyWebsocket {

    // 在线用户数
    private static AtomicInteger onlineCount = new AtomicInteger();

    private static CopyOnWriteArraySet<MyWebsocket> myWebsockets = new CopyOnWriteArraySet();

    private Session session;


    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        myWebsockets.add(this);
        onlineCount.incrementAndGet();
        System.out.println("connect: current online count:" + onlineCount.get());
    }

    @OnClose
    public void onClose(){
        myWebsockets.remove(this);
        onlineCount.decrementAndGet();
        System.out.println("leave: current online count:" + onlineCount.get());
    }


    @OnMessage
    public void onMessage(String msg, Session session){
        System.out.println("recive msg from client : " + msg);
        for(MyWebsocket node : myWebsockets){
            try {
                node.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("unexcepted error");
        error.printStackTrace();
    }


    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

}
