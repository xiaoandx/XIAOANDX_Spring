/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package club.xiaoandx.conf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 接口的简单实现
 * </p>
 * @ClassName:RedisHelperImpl
 * @author: xiaoandx.周巍
 * @date: 2019-06-27 12:54
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
@Service("RedisHelper")
public class RedisHelperImpl<HK, T> implements RedisHelper<HK, T> {
	// 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
	private RedisTemplate<String, T> redisTemplate;
	// 在构造器中通过redisTemplate的工厂方法实例化操作对象
	private HashOperations<String, HK, T> hashOperations;
	private ListOperations<String, T> listOperations;
	@SuppressWarnings("unused")
	private ZSetOperations<String, T> zSetOperations;
	@SuppressWarnings("unused")
	private SetOperations<String, T> setOperations;
	private ValueOperations<String, T> valueOperations;

	// IDEA虽然报错,但是依然可以注入成功, 实例化操作对象后就可以直接调用方法操作Redis数据库
	@Autowired
	public RedisHelperImpl(RedisTemplate<String, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
		this.listOperations = redisTemplate.opsForList();
		this.zSetOperations = redisTemplate.opsForZSet();
		this.setOperations = redisTemplate.opsForSet();
		this.valueOperations = redisTemplate.opsForValue();
	}

	@Override
	public void hashPut(String key, HK hashKey, T domain) {
		hashOperations.put(key, hashKey, domain);
	}

	@Override
	public Map<HK, T> hashFindAll(String key) {
		return hashOperations.entries(key);
	}

	@Override
	public T hashGet(String key, HK hashKey) {
		return hashOperations.get(key, hashKey);
	}

	@Override
	public void hashRemove(String key, HK hashKey) {
		hashOperations.delete(key, hashKey);
	}

	@Override
	public Long listPush(String key, T domain) {
		return listOperations.rightPush(key, domain);
	}

	@Override
	public Long listUnshift(String key, T domain) {
		return listOperations.leftPush(key, domain);
	}

	@Override
	public List<T> listFindAll(String key) {
		if (!redisTemplate.hasKey(key)) {
			return null;
		}
		return listOperations.range(key, 0, listOperations.size(key));
	}

	@Override
	public T listLPop(String key) {
		return listOperations.leftPop(key);
	}

	@Override
	public void valuePut(String key, T domain) {
		valueOperations.set(key, domain);
	}

	@Override
	public T getValue(String key) {
		return valueOperations.get(key);
	}

	@Override
	public void remove(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public boolean expirse(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout, timeUnit);
	}
}
