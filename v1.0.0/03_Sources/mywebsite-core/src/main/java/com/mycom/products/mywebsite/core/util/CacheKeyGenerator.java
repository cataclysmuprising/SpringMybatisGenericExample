/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;

public class CacheKeyGenerator implements KeyGenerator {
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = target.getClass().getSimpleName();
		key += "@";
		key += method.getName();
		if (params != null && params.length > 0) {
			key += "-";
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				if (param != null) {
					key += param.toString();
					if (i + 1 != params.length) {
						key += ",";
					}
				}
			}
		}
		// logger.debug("Cache Key ==> " + key);
		return key;
	}
}