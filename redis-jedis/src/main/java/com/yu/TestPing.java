package com.yu;
import redis.clients.jedis.Jedis;

/**
 * 测试连接Redis服务端
 */
public class TestPing {
    public static void main(String[] args) {

//        通过Ip地址端口号获得一个jedis对象，此处的Jedis对象可以看作是一个redis客户端
        Jedis jedis = new Jedis("127.0.0.1", 6379);

//        Redis的命令在jedis中成为方法，jedis通过 "." 的形式来调用Redis命令
        System.out.println(jedis.ping());

//        关闭连接
        jedis.close();
    }
}
