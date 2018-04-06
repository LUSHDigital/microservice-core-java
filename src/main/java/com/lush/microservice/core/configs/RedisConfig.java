package com.lush.microservice.core.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

  /**
   * Redis host name.
   */
  @Value("${spring.redis.host}")
  private String redisHost;

  /**
   * Redis port.
   */
  @Value("${spring.redis.port}")
  private int redisPort;

  /**
   * Create a JedisConnectionFactory to set the host, port, and pool for the redis.
   * @return JedisConnectionFactory
   */
  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  /**
   * Create a RedisTemplate to serialize keys and values.
   *
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return redisTemplate;
  }
}
