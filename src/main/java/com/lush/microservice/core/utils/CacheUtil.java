package com.lush.microservice.core.utils;

import com.lush.microservice.core.enums.CacheKeyType;
import com.lush.microservice.core.models.Cache;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Class for cache util.
 *
 * @author Is
 */
@Component
public class CacheUtil {

  /**
   * HashOperations.
   */
  private HashOperations<String, String, Object> hashOps;

  /**
   * Separator.
   */
  private final String SEPARATOR = ":";

  /**
   * Cache.
   */
  @Autowired
  private Cache cache;

  /**
   * Application name.
   */
  @Value("${spring.application.name}")
  private String appName;

  /**
   * Construct.
   *
   * @param redisTemplate
   */
  @Autowired
  public CacheUtil(RedisTemplate<String, Object> redisTemplate) {
    this.hashOps = redisTemplate.opsForHash();
  }

  /**
   * Create a redis cache key.
   *
   * @param cache
   */
  public void setRedisCacheData(Cache cache) {
    hashOps.put(cache.getKey(), cache.getHashKey(), cache.getValue());
  }

  /**
   * Get a redis cache data.
   *
   * @param cache
   * @return Object
   */
  public Object getRedisCacheData(Cache cache) {
    return hashOps.get(cache.getKey(), cache.getHashKey());
  }

  /**
   * Remove a redis cache key.
   *
   * @param cache
   */
  public void removeRedisCacheData(Cache cache) {
    hashOps.delete(cache.getKey(), cache.getHashKey());
  }

  /**
   * Generate a hash cache key.
   *
   * @return String
   */
  public String generateHashKeyByKeys(String[] keys) {
    String hashKey = appName;

    // Combine keys to make a hash cache key.
    for (String key : keys) {
      hashKey += SEPARATOR + key;
    }

    return hashKey;
  }

  /**
   * Generate a cache key.
   *
   * @return String
   */
  public String generateKeyByKeys(CacheKeyType cacheKeyType) {
    return appName + SEPARATOR + cacheKeyType;
  }

  /**
   * Generate a cache key.
   *
   * @return String
   */
  public String generateKeyByKeys(CacheKeyType cacheKeyType, String[] keys) {
    String cacheKey = appName + SEPARATOR + cacheKeyType;

    // Combine cache key type and keys to make a cache key.
    for (String key : keys) {
      cacheKey += SEPARATOR + key;
    }

    return cacheKey;
  }

  /**
   * Set a cache.
   *
   * @param cacheKeyType
   * @param keys
   * @param hashKeys
   * @return Cache
   */
  public Cache setCache(CacheKeyType cacheKeyType, String[] keys, String[] hashKeys) {

    // If the cache type is a list or child, create a cache key by combining the cache type and keys.
    if (cacheKeyType == CacheKeyType.CHILD || cacheKeyType == CacheKeyType.LIST) {
      cache.setKey(this.generateKeyByKeys(cacheKeyType, keys));
    } else {
      cache.setKey(this.generateKeyByKeys(cacheKeyType));
    }

    cache.setHashKey(this.generateHashKeyByKeys(hashKeys));

    return cache;
  }

  /**
   * Remove all redis cache data.
   *
   * @param cacheKeyType
   * @param keys
   */
  public void removeAllRedisCacheData(CacheKeyType cacheKeyType, String[] keys) {
    String cacheKey = this.generateKeyByKeys(cacheKeyType, keys);
    Map<String, Object> allRedisCacheData = hashOps.entries(cacheKey);

    for (String key : allRedisCacheData.keySet()) {
      hashOps.delete(cacheKey, key.toString());
    }
  }
}