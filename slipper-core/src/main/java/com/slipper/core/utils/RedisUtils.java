package com.slipper.core.utils;

import com.slipper.common.exception.RunException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 存入普通对象
     * @param key redis key
     * @param value 值
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 存入普通对象
     * @param key redis key
     * @param value 值
     * @param expire 过期时长
     */
    public void set(String key, Object value, long expire) {
        if(StringUtils.isBlank(key)) {
            throw new RunException("redis key 不可以为空!");
        }
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 获取普通对象
     * @param key redis key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 往Hash中存入数据
     * @param key redis key
     * @param hashKey hash Key
     * @param value 值
     */
    public void putHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 往Hash中存入数据
     * @param key redis key
     * @param value 值
     */
    public void putHashBatch(String key, Map<?,?> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 获取Hash中的数据
     * @param key redis key
     * @param hashKey hash key
     */
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取Hash中的数据
     * @param key redis key
     * @param hashKeys hash key
     */
    public List multiGetHashBatch(String key, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 往Set中存入数据
     *
     * @param key redis key
     * @param values 值
     * @return 存入的个数
     */
    public long addSet(String key, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key redis key
     * @param values 值
     * @return 移除的个数
     */
    public long removeSet(String key, Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入数据
     *
     * @param key redis key
     * @param value 值
     * @return 存入的个数
     */
    public long pushList(String key, Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key redis key
     * @param values 值
     * @return 存入的个数
     */
    public long pushListBatch(String key, Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key redis key
     * @param values 值
     * @return 存入的个数
     */
    public long pushListBatch(String key, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key redis key
     * @param start 开始位置
     * @param end 结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public List<Object> getListRange(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 删除key
     * @param key key
     * @return
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     * @param keys keys
     * @return
     */
    public long deleteBatch(Collection<String> keys) {
        Long count = redisTemplate.delete(keys);
        return count == null ? 0 : count;
    }

}
