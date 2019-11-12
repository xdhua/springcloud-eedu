/**
 * 
 */
package com.hxd.security.core.cache;

import java.util.concurrent.ConcurrentHashMap;



/**
 * 自己创建一个 缓存
 * @author hxd
 *
 * date 2019年11月8日
 * 
 */
public class WebUserConCurrentMapCache implements WebUserCache {
	
	private final ConcurrentHashMap<String, Object> store;
	
	public WebUserConCurrentMapCache(ConcurrentHashMap<String, Object> store) {
		this.store = store;
	}

	@Override
	public Object get(String key) {
		return this.store.get(key);
	}

	@Override
	public void put(String key, Object value) {
		this.store.put(key, value);
		
	}

	@Override
	public void remove(String key) {
		this.store.remove(key);
		
	}

	@Override
	public void clear() {
		this.store.clear();
		
	}

	@Override
	public Object getNativeCache() {
		return this.store;
	}
	
	

}
