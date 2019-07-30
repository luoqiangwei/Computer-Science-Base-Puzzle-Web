package cn.ovea_y.puzzle.util.commons;

import java.util.Map;
import java.util.UUID;

/**
 * @author OVEA
 */
public class CommonUtils {
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 把map转换成对象
	 * @param map
	 * @param clazz
	 * @return
	 * 
	 * 把Map转换成指定类型
	 */
//	public static <T> T toBean(Map map, Class<T> clazz) {
//		try {
//			/*
//			 * 1. 通过参数clazz创建实例
//			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
//			 */
//			T bean = clazz.getDeclaredConstructor().newInstance();
//			ConvertUtils.register(new DateConverter(), java.util.Date.class);
//			BeanUtils.populate(bean, map);
//			return bean;
//		} catch(Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
}
