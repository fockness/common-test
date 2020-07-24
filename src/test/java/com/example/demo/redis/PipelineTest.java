package com.example.demo.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTest extends AbstractRedisProvider{

    @Test
    public void testPipeline(){
        Map<String, String> m1 = Maps.newHashMap();
        m1.put("key", "aa");
        m1.put("value", "11");
        Map<String, String> m2 = Maps.newHashMap();
        m2.put("key", "bb");
        m2.put("value", "22");
        List<Map<String, String>> saveList = Lists.newArrayList(m1, m2);
        //batchInsert(saveList, TimeUnit.HOURS, 1);
        System.out.println(batchGet(Lists.newArrayList("aa", "bb")));
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * pipeline插入多条数据
     * @param saveList
     * @param unit
     * @param timeout
     *
     * SessionCallback比RedisCallback封装的更友好
     */
    public void batchInsert(List<Map<String, String>> saveList, TimeUnit unit, int timeout) {
        /* 插入多条数据 */
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (Map<String, String> needSave : saveList) {
                    redisTemplate.opsForValue().set(needSave.get("key"), needSave.get("value"), timeout,unit);
                }
                return null;
            }
        });
    }

    /**
     * pipeline取出多条数据
     * @param keyList
     * @return
     */
    public List<String> batchGet(List<String> keyList) {
        /* 批量获取多条数据 */
        List<Object> objects = redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
                for (String key : keyList) {
                    stringRedisConnection.get(key);
                }
                return null;
            }
        });

        List<String> collect = objects.stream().map(val -> String.valueOf(val)).collect(Collectors.toList());
        return collect;
    }
}
