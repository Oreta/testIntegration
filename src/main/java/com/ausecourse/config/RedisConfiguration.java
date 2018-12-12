package com.ausecourse.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration @PropertySource("application.properties") public class RedisConfiguration {
//
//    public static String SEPARATOR = ":";
//
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory
//            = new JedisConnectionFactory();
//        jedisConFactory.setHostName("redis-17866.c1.eu-west-1-3.ec2.cloud.redislabs.com");
//        jedisConFactory.setPassword("123456a");
//        jedisConFactory.setPort(17866);
//        return jedisConFactory;
//    }
//
//    @Bean public RedisTemplate<String, Object> getRedisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
//
//}
public class RedisConfiguration {
 	public static String SEPARATOR = ":";
 	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	    return new JedisConnectionFactory();
	}
 	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
