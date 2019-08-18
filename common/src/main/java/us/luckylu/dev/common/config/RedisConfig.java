package us.luckylu.dev.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;


/**
 * Redis序列化
 */
@Configuration
public class RedisConfig {

    @Resource
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisTemplate jdkRedisTemplate() {
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(jdkSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(jdkSerializer);

        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate longRedisTemplate() {
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericToStringSerializer<Long> longSerializer = new GenericToStringSerializer<>(Long.class);

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(longSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(longSerializer);

        return template;
    }
}
