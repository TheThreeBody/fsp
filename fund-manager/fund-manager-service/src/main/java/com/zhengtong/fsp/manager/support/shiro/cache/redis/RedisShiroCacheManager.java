package com.zhengtong.fsp.manager.support.shiro.cache.redis;

import com.zhengtong.fsp.manager.support.redis.RedisManager;
import com.zhengtong.fsp.manager.support.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

/**
 * 
 * @ClassName RedisShiroCacheManager
 * @Description Redis管理
 *
 * @author Dongwen
 * @data 2016年12月13日 下午1:43:57
 */
public class RedisShiroCacheManager implements ShiroCacheManager {

    private RedisManager cacheManager;
    
    public RedisManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(RedisManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new RedisShiroCache<K, V>(name, getCacheManager());
    }

    @Override
    public void destroy() {
    	//做一些需要释放资源的操作
    }

}
