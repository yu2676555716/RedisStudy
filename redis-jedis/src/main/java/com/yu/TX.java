package com.yu;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Redis事务
 */
public class TX {
    public static void main(String[] args) {

//        伪造数据
        JSONObject object = new JSONObject();
        object.put("userName","yu");
        object.put("passWord","123456");
        String userInfo = object.toJSONString();

//        获得jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

//        清空数据库(测试使用，开发生产勿用)
        //jedis.flushDB();

//        开启事务
        Transaction multi = jedis.multi();

        try {
//        操作事务
            multi.set("user1",userInfo);
            multi.set("user2",userInfo);

//            模拟代码出错，事务不执行
            //int i = 1/0;

//        执行事务
            multi.exec();

        } catch (Exception e) {

//            事务放弃
            multi.discard();

            e.printStackTrace();
        }finally {

//          关闭连接
            jedis.close();

            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
        }
    }
}
