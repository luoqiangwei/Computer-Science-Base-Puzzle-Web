package cn.oveay.cspuzzle.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 这个项目中没必要使用这个类
//@WebListener
public class TomcatListener implements ServletContextListener {
//    private UserService userService = new UserServiceImpl();
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        userService.trunOffOnlineToAllUser();
    }
}
