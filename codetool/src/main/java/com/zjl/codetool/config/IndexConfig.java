package com.zjl.codetool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import java.io.IOException;

/**
 * @author zhoujl
 * @date 2020/9/27
 */
@Configuration
public class IndexConfig{

   @Value("${server.port}")
   private String serverport;

   @EventListener({ApplicationReadyEvent.class})
   public void applicationReadyEvent() {
        String url = "http://localhost:"+serverport;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}