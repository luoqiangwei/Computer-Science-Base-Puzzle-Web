package cn.ovea_y.puzzle.util.commons;


import cn.ovea_y.puzzle.util.commons.exception.NanoflakeException;

import java.io.IOException;
import java.util.Properties;

/**
 * @author QiangweiLuo
 * 用于产生分布式ID的一个类
 */
public class Nanoflake {
    // 设定一个初始纳秒时间戳
    private static long initialTime = 1878547820027449L;
    // 设定的机器ID
    private static long workerID = 0;
    // 最大可用的机器ID
    private static long maxWorkerID = 2048L - 1L;
    // 机器ID偏移bit
    private static long workerMoveBit = 52L;

    static {
        // 加载配置文件
        Properties props = new Properties();
        try {
            props.load(Nanoflake.class.getClassLoader().getResourceAsStream("/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("配置文件读取失败");
        }
        if(props.getProperty("machine_id") != null){
            workerID = Integer.parseInt(props.getProperty("machine_id"));
        }
    }

    /**
     * 获取初始化时间
     * @return
     */
    public long getInitialTime() {
        return initialTime;
    }

    /**
     *
     * @param initialTime
     */
    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    /**
     * 获取当前机器ID
     * @return
     */
    public long getWorkerID() {
        return workerID;
    }

    /**
     * 设置当前机器ID
     * @param workerID
     * @throws NanoflakeException
     */
    public void setWorkerID(long workerID) throws NanoflakeException {
        if(workerID < 0 || workerID > maxWorkerID) throw new NanoflakeException("workerID too big or too low");
        this.workerID = workerID;
    }

    /**
     * 获取当前纳秒时间
     * @return
     */
    private static synchronized long getNanoTime(){
        return System.nanoTime();
    }

    /**
     * 获取基于纳秒的数字分布式ID
     * @return
     */
    public static long getNanoflakeNum(){
        long temp = 0;
        temp |= getNanoTime() - initialTime;
        workerID = workerID << workerMoveBit;
        temp |= workerID;
        return temp;
    }

    /**
     * 获取基于纳秒的字符分布式ID
     * @return
     */
    public static String getNanoflake(){
        return  Long.toHexString(getNanoflakeNum());
    }
}
