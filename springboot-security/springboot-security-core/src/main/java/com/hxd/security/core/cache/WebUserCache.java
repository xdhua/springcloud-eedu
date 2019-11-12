package com.hxd.security.core.cache;

/**
 *   创建接口标准
 * @author hxd
 *
 * date 2019年11月8日
 *
 */
public interface WebUserCache {
	
	/**
	 *  根据当前key 获取用户存放值
	 * @param key
	 * @return
	 */
	Object get(String key);
	
	/**
	 *  将需要的信息存放在内存中
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);
	
	/**
	 *  从当前内存移除相应数据
	 * @param key
	 */
	void remove(String key);
	
	/**
	 * 清空内存
	 */
	void clear();
	
	/**
	 *  获取当前 缓存
	 * @return
	 */
	Object getNativeCache();
	
	/**
	 *  判断当前缓存中是否存在当前值
	 * @param key
	 * @return
	 */
	boolean containsKey(String key);

}
