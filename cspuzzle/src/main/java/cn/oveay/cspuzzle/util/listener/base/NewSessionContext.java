package cn.oveay.cspuzzle.util.listener.base;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class NewSessionContext {
//    private static UserService userService = new UserServiceImpl();
    private static HashMap<String, HttpSession> sessionMap = new HashMap();
    private static HashMap<String, Long> createTimeMap = new HashMap();
    private static int timeout = 6;
    private static Boolean isUseHeartBeat = false;

    static {
        // 加载配置文件
        Properties props = new Properties();
        try {
            props.load(NewSessionContext.class.getClassLoader().getResourceAsStream("/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("配置文件读取失败");
        }
        if(props.getProperty("heartBeat") != null){
            isUseHeartBeat = Boolean.parseBoolean(props.getProperty("heartBeat"));
        }
        if(props.getProperty("timeout") != null){
            timeout = Integer.parseInt(props.getProperty("timeout"));
        }
    }

    public static int getTimeout() {
        return timeout;
    }

    public static HashMap getSessionMap() {
        return sessionMap;
    }

    public static synchronized void AddSession(HttpSession session){
        if (session != null) {
            //虽然设置为1秒，但是时间上还会延迟1分钟销毁session。
            //然而真的1秒多一点就失效了😂
            if(isUseHeartBeat) {
                session.setMaxInactiveInterval(timeout);
            }
            sessionMap.put(session.getId(), session);
//            createTimeMap.put(session.getId(), new Date().getTime());
        }
    }

    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
//            if(session.getAttribute("user") != null){
//                User user = (User) session.getAttribute("user");
//                user.setOnline(User.OFFLINE);
//                userService.updateUser(user);
//            }
            sessionMap.remove(session.getId());
//            createTimeMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return sessionMap.get(session_id);
    }

    public static synchronized Long getCreateTime(String session_id) {
        if (session_id == null)
            return null;
        return createTimeMap.get(session_id);
    }

    public static synchronized void resetCreateTime(String session_id) {
        createTimeMap.remove(session_id);
        createTimeMap.put(session_id, new Date().getTime());
    }

}
