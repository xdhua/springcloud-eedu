/**
 * 
 */
package com.hxd.security.core.cache;



/**
 *  缓存的空实现
 * @author hxd
 *
 * date 2019年11月8日
 * 
 */
public class WebUserNullCache implements WebUserCache {
	

	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public void put(String key, Object value) {
		
	}

	@Override
	public void remove(String key) {
		
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Object getNativeCache() {
		return null;
	}

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}
