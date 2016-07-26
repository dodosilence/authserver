package cc.moondust.authserver.bootstrap;

import cc.moondust.authserver.netty.HttpAuthServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 程序的入口
 * Created by Tristan on 16/7/17.
 */
public class BootMain {
    private static ApplicationContext applicationContext;

    public static void main(String[] strs){
        applicationContext=new ClassPathXmlApplicationContext("spring/spring-context.xml");
        HttpAuthServer httpAuthServer = applicationContext.getBean(HttpAuthServer.class);
        httpAuthServer.run();
    }
}
