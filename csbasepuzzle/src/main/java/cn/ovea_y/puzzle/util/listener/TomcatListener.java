package cn.ovea_y.puzzle.util.listener;

import cn.oveay.classdesign.service.UserService;
import cn.oveay.classdesign.service.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TomcatListener implements ServletContextListener {
    private UserService userService = new UserServiceImpl();
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        userService.trunOffOnlineToAllUser();
    }
}
