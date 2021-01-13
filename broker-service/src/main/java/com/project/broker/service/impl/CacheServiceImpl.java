package com.project.broker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.project.broker.service.CacheService;

/**
 * Cache service implementation
 * 
 * @author saumeen
 *
 */
@Service
public class CacheServiceImpl implements CacheService{

	
	@Autowired
    private CacheManager cacheManager;
	
	@Override
	public void evictCache() {
		cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
	}

}
