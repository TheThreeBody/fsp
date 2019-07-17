package com.zhengtong.fsp.manager.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

//import java.util.Collection;

/**
 * @ClassName CustomerShiroCacheManager
 * @Description 用户自定义cachemanager，方便扩展
 *
 * @author Dongwen
 * @data 2016年12月13日 下午3:42:19
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

//    private Collection<? extends org.springframework.cache.Cache> caches;

    private ShiroCacheManager shiroCacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return getShiroCacheManager().getCache(name);
    }

    @Override
    public void destroy() throws Exception {
        shiroCacheManager.destroy();
    }

    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }

//    public void setCaches(Collection<? extends org.springframework.cache.Cache> caches) {
//        this.caches = caches;
//    }
//
//    protected Collection<? extends org.springframework.cache.Cache> loadCaches() {
//        return this.caches;
//    }
}
