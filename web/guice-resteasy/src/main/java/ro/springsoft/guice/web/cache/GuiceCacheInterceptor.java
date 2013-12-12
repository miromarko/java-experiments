package ro.springsoft.guice.web.cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Author: Miroslav MARKO Date: 10/4/13
 * in module config:
 * 		bindInterceptor(any(), annotatedWith(Cached.class),
 *				new GuiceCacheInterceptor());
 */
public class GuiceCacheInterceptor implements MethodInterceptor {
	private static final long max_cache = 600000; // max cache size
	private static final Cache<String, Object> cache = CacheBuilder
			.newBuilder().maximumSize(max_cache).build();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		// LOG.info("intercept : " + invocation.getMethod().getName());
		String objectKey = "";
		for (Object argument : invocation.getArguments()) {
			if (argument == null) {
				objectKey += "|";
			} else {
				objectKey += "|" + argument.toString();
			}
		}
		if (!"".equals(objectKey)) {
			objectKey = invocation.getMethod().getName() + objectKey;
			Object ret = cache.getIfPresent(objectKey);
			if (ret == null) {
				ret = invocation.proceed();
				if (ret != null) {
					cache.put(objectKey, ret);
				}
			}
			return ret;
		}
		return invocation.proceed();
	}

	public static Cache<String, Object> getCache() {
		return cache;
	}

}
