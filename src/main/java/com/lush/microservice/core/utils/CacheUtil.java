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
   */
  @Autowired
  public CacheUtil(RedisTemplate<String, Object> redisTemplate) {
    this.hashOps = redisTemplate.opsForHash();
  }

  /***************************************************
   * SET CACHE KEY
   ***************************************************/


  /**
   * Set a cache by keys(Use call from service.).
   *
   * @return Cache
   */
  public Cache setCache(CacheKeyType cacheKeyType, String[] keys) {
    return this.setCache(cacheKeyType, keys, keys);
  }

  /**
   * Set a cache by key(Use call from service.).
   */
  public Cache setCache(CacheKeyType cacheKeyType, String key) {
    String[] keys = {key};
    return this.setCache(cacheKeyType, keys, keys);
  }

  /**
   * Set list cache by keys, page. (Use call from service.).
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
   * Set list cache by key, page. (Use call from service.).
   *
   * @param key
   * @param page
   * @return Cache
   */
  public Cache setListCache(String key, Pageable page) {
    return this.setListCache(new String[]{key}, page);
  }

  /**
   * Set list cache by page. (Use call from service.).
   *
   * @param page
   * @return Cache
   */
  public Cache setListCache(Pageable page) {
    return this.setListCache(new String[]{}, page);
  }

  /**
   * Set child list cache by keys. (Use call from service.).
   *
   * @param keys
   * @return Cache
   */
  public Cache setChildListCache(String[] keys) {
    return this.setCache(CacheKeyType.CHILD_LIST, keys);
  }

  /**
   * Set child list cache by key. (Use call from service.).
   *
   * @param key
   * @return Cache
   */
  public Cache setChildListCache(String key) {
    return this.setCache(CacheKeyType.CHILD_LIST, new String[]{key});
  }

  /**
   * Set self cache by keys. (Use call from service.).
   *
   * @param keys
   * @return Cache
   */
  public Cache setSelfCache(String[] keys) {
    return this.setCache(CacheKeyType.SELF, keys);
  }

  /**
   * Set self cache by key. (Use call from service.).
   *
   * @param key
   * @return Cache
   */
  public Cache setSelfCache(String key) {
    return this.setCache(CacheKeyType.SELF, new String[]{key});
  }

  /**
   * Set parent cache by keys. (Use call from service.).
   *
   * @param keys
   * @return Cache
   */
  public Cache setParentCache(String[] keys) {
    return this.setCache(CacheKeyType.PARENT, keys);
  }

  /**
   * Set parent cache by key. (Use call from service.).
   *
   * @param key
   * @return Cache
   */
  public Cache setParentCache(String key) {
    return this.setCache(CacheKeyType.PARENT, new String[]{key});
  }

  /**
   * Set child cache by keys. (Use call from service.).
   *
   * @param keys
   * @return Cache
   */
  public Cache setChild(String[] keys) {
    return this.setCache(CacheKeyType.CHILD, keys);
  }

  /**
   * Set child cache by key. (Use call from service.).
   *
   * @param key
   * @return Cache
   */
  public Cache setChild(String key) {
    return this.setCache(CacheKeyType.CHILD, new String[]{key});
  }

  /***************************************************
   * SET CACHE
   ***************************************************/


  /**
   * Set cache data on the redis server.
   *
   * @param value
   * @param cache
   */
  public void createCacheData(Object value, Cache cache) {
    cache.setValue(value);
    this.setRedisCacheData(cache);
  }

  /**
   * Create a redis cache key.
   */
  private void setRedisCacheData(Cache cache) {
    hashOps.put(cache.getKey(), cache.getHashKey(), cache.getValue());
  }

  /***************************************************
   * REMOVE CACHE
   ***************************************************/

  /**
   * Remove all redis cache data.
   *
   * @param cacheKeyType
   * @param keys
   */
  private void removeAllRedisCacheData(CacheKeyType cacheKeyType, String[] keys) {
    String cacheKey = this.generateKeyByKeys(cacheKeyType, keys);
    Map<String, Object> allRedisCacheData = hashOps.entries(cacheKey);

    for (String key : allRedisCacheData.keySet()) {
      hashOps.delete(cacheKey, key);
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
    this.removeAllRedisCacheData(CacheKeyType.LIST, new String[]{key});
  }

  /**
   * Remove list cache.
   */
  public void removeListCacheData() {
    this.removeAllRedisCacheData(CacheKeyType.LIST, new String[]{});
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
    this.removeAllRedisCacheData(CacheKeyType.CHILD_LIST, new String[]{key});
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
    cache = this.setCache(CacheKeyType.SELF, new String[]{key});
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
    cache = this.setCache(CacheKeyType.PARENT, new String[]{key});
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
    cache = this.setCache(CacheKeyType.CHILD, new String[]{key});
    this.removeRedisCacheData(cache);
  }

  /***************************************************
   * COMMON
   ***************************************************/


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
   * @param keys
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
   * @param cacheKeyType
   * @return String
   */
  private String generateKeyByKeys(CacheKeyType cacheKeyType) {
    return appName + SEPARATOR + cacheKeyType;
  }

  /**
   * Generate a cache key.
   *
   * @param cacheKeyType
   * @param keys
   * @return String
   */
  private String generateKeyByKeys(CacheKeyType cacheKeyType, String[] keys) {
    String cacheKey = appName + SEPARATOR + cacheKeyType;

    // Combine cache key type and keys to make a cache key.
    for (String key : keys) {
      cacheKey += SEPARATOR + key;
    }
    return cacheKey;
  }

}