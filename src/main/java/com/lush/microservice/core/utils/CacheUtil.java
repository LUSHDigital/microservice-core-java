package com.lush.microservice.core.utils;

import com.lush.microservice.core.enums.CacheKeyType;
import com.lush.microservice.core.models.Cache;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
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
   * Set a cache(For internal use.).
   *
   * @param cacheKeyType
   * @param keys
   * @param hashKeys
   * @return Cache
   */
  private Cache setCache(CacheKeyType cacheKeyType, String[] keys, String[] hashKeys) {

    // If the cache type is a list or child or child list, create a cache key by combining the cache type and keys.
    if (cacheKeyType == CacheKeyType.CHILD || cacheKeyType == CacheKeyType.LIST
        || cacheKeyType == CacheKeyType.CHILD_LIST) {
      cache.setKey(this.generateKeyByKeys(cacheKeyType, keys));
    } else {
      // PARENT, SELF
      cache.setKey(this.generateKeyByKeys(cacheKeyType));
    }

    cache.setHashKey(this.generateHashKeyByKeys(hashKeys));

    return cache;
  }

  /**
   * Set list cache by keys, page(Use call from service.).
   *
   * @param keys
   * @param page
   * @return Cache
   */
  public Cache setListCache(String[] keys, Pageable page) {
    String[] hashKeys = {page.getSort().toString(),
        Integer.toString(page.getPageNumber()), Integer.toString(page.getPageSize())};

    return this.setCache(CacheKeyType.LIST, keys, hashKeys);
  }

  /**
   * Set list cache by key, page(Use call from service.).
   *
   * @param key
   * @param page
   * @return
   */
  public Cache setListCache(String key, Pageable page) {
    String[] keys = {key};
    return this.setListCache(keys, page);
  }

  /**
   * Set list cache by page(Use call from service.).
   *
   * @param page
   * @return Cache
   */
  public Cache setListCache(Pageable page) {
    String[] keys = {};
    return this.setListCache(keys, page);
  }

  /**
   * Set a cache by keys(Use call from service.).
   *
   * @param cacheKeyType
   * @param keys
   * @return Cache
   */
  public Cache setCache(CacheKeyType cacheKeyType, String[] keys) {
    return this.setCache(cacheKeyType, keys, keys);
  }

  /**
   * Set a cache by key(Use call from service.).
   *
   * @param cacheKeyType
   * @param key
   * @return
   */
  public Cache setCache(CacheKeyType cacheKeyType, String key) {
    String[] keys = {key};
    return this.setCache(cacheKeyType, keys, keys);
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

  /**
   * Remove a redis cache key.
   *
   * @param cache
   */
  private void removeRedisCacheData(Cache cache) {
    hashOps.delete(cache.getKey(), cache.getHashKey());
  }

  /**
   * Remove list cache by keys.
   *
   * @param keys
   */
  public void removeListCacheData(String[] keys) {
    this.removeAllRedisCacheData(CacheKeyType.LIST, keys);
  }

  /**
   * Remove list cache by key.
   *
   * @param key
   */
  public void removeListCacheData(String key) {
    String[] keys = {key};
    this.removeAllRedisCacheData(CacheKeyType.LIST, keys);
  }

  /**
   * Remove child list cache by keys.
   *
   * @param keys
   */
  public void removeChildListCacheData(String[] keys) {
    this.removeAllRedisCacheData(CacheKeyType.CHILD_LIST, keys);
  }

  /**
   * Remove child list cache by key.
   *
   * @param key
   */
  public void removeChildListCacheData(String key) {
    String[] keys = {key};
    this.removeAllRedisCacheData(CacheKeyType.CHILD_LIST, keys);
  }

  /**
   * Remove self cache by keys.
   *
   * @param keys
   */
  public void removeSelfCacheData(String[] keys) {
    cache = this.setCache(CacheKeyType.SELF, keys);
    this.removeRedisCacheData(cache);
  }

  /**
   * Remove self cache by key.
   *
   * @param key
   */
  public void removeSelfCacheData(String key) {
    String[] keys = {key};
    cache = this.setCache(CacheKeyType.SELF, keys);
    this.removeRedisCacheData(cache);
  }

  /**
   * Remove parent cache by keys.
   *
   * @param keys
   */
  public void removeParentCacheData(String[] keys) {
    cache = this.setCache(CacheKeyType.PARENT, keys);
    this.removeRedisCacheData(cache);
  }

  /**
   * Remove parent cache by key.
   *
   * @param key
   */
  public void removeParentCacheData(String key) {
    String[] keys = {key};
    cache = this.setCache(CacheKeyType.PARENT, keys);
    this.removeRedisCacheData(cache);
  }

  /**
   * Remove child cache by keys.
   *
   * @param keys
   */
  public void removeChildCacheData(String[] keys) {
    cache = this.setCache(CacheKeyType.CHILD, keys);
    this.removeRedisCacheData(cache);
  }

  /**
   * Remove child cache by key.
   *
   * @param key
   */
  public void removeChildCacheData(String key) {
    String[] keys = {key};
    cache = this.setCache(CacheKeyType.CHILD, keys);
    this.removeRedisCacheData(cache);
  }
}