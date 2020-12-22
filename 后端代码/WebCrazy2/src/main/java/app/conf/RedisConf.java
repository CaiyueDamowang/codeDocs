package app.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author Fizz Pu
 * @Date 2020/12/22 上午10:55
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConf {

    @Value("${redis.domain}")
    private String domain;

    @Value("${redis.port}")
    private String  port;

    @Value("${redis.maxIdle}")
    private String maxIdle;

    @Value("${redis.maxTotal}")
    private String maxTotal;

    @Value("${redis.maxWaitMillis}")
    private String maxWaitMillis;


    public RedisTemplate<String, Object> initRedisTemplate() {
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxIdle(Integer.parseInt(maxIdle)); // 最大空闲数
        pool.setMaxIdle(Integer.parseInt(maxTotal)); // 最大连接数
        pool.setMaxWaitMillis(Integer.parseInt(maxWaitMillis));

        JedisConnectionFactory factory = new JedisConnectionFactory(pool);
        factory.setHostName(domain);
        factory.setPort(Integer.parseInt(port));

        RedisTemplate redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;
    }
}
