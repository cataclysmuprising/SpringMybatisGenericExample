/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.util;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = target.getClass().getName();
		key += "#";
		key += method.getName();
		for (Object param : params) {
			if (param != null) {
				key += "-" + param.toString();
			}
		}
		System.err.println("Final Key is ==> " + key);
		return key;
	}
}