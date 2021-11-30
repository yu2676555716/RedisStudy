package com.yu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.pojo.User;
import com.yu.pojo.User1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

//    初探redisTemplate
    @Test
    void contextLoads() {

//        和jedis类似，但是多了opsFor...的操作，此处的ForValue可以看作是操作String类型
//        基本的操作可以直接通过redisTemplate.的方式操作，如.delete()删除，事务操作等
        redisTemplate.opsForValue().set("key1","Hello Redis");

//        对数据库的操作通过获取连接来操作，如清空数据库等
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushAll();
//        connection.flushDb();
//        connection.close();

//        以上的操作非常麻烦，所以一般使用自己定义的redisTemplate

        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

//    使用redis操作未序列化实体类示例
    @Test
    void unSerializable() throws JsonProcessingException {

        User user = new User("yu", 21);
//        真实开发一般都用JSON传递对象
        String json = new ObjectMapper().writeValueAsString(user);

//        json格式存redis
        redisTemplate.opsForValue().set("user",json);
//        显示正常
        System.out.println(redisTemplate.opsForValue().get("user"));

//        user对象直接存redis
        redisTemplate.opsForValue().set("user1",user);
//        报SerializationFailedException和IllegalArgumentException异常
        System.out.println(redisTemplate.opsForValue().get("user1"));
//        直接使用项目因为自定义了RedisConfig配置，所以不报异常

    }

//    使用redis操作序列化实体类示例
    @Test
    void useSerializable(){

        User1 user = new User1("yu", 21);
//        user对象直接存redis
        redisTemplate.opsForValue().set("user2",user);

        System.out.println(redisTemplate.opsForValue().get("user2"));

    }

}
