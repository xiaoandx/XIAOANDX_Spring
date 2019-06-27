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

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**  
 * K 指以hash结构操作时 键类型
 * T 为数据实体 应实现序列化接口,并定义serialVersionUID * RedisTemplate 提供了五种数据结构操作类型 hash / list / set / zset / value
 * 方法命名格式为 数据操作类型 + 操作 如 hashPut 指以hash结构(也就是map)想key添加键值对
 * @ClassName:RedisHelper   
 * @author: xiaoandx.周巍
 * @date: 2019-06-27 12:52
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
public interface RedisHelper<HK, T> {
    /**
     * Hash结构 添加元素 * @param key key * @param hashKey hashKey * @param domain 元素
     */
    void hashPut(String key, HK hashKey, T domain);

    /**
     * Hash结构 获取指定key所有键值对 * @param key * @return
     */
    Map<HK, T> hashFindAll(String key);

    /**
     * Hash结构 获取单个元素 * @param key * @param hashKey * @return
     */
    T hashGet(String key, HK hashKey);

    void hashRemove(String key, HK hashKey);

    /**
     * List结构 向尾部(Right)添加元素 * @param key * @param domain * @return
     */
    Long listPush(String key, T domain);

    /**
     * List结构 向头部(Left)添加元素 * @param key * @param domain * @return
     */
    Long listUnshift(String key, T domain);

    /**
     * List结构 获取所有元素 * @param key * @return
     */
    List<T> listFindAll(String key);

    /**
     * List结构 移除并获取数组第一个元素 * @param key * @return
     */
    T listLPop(String key);

    /**
     * 对象的实体类
     * @param key
     * @param domain
     * @return
     */
    void valuePut(String key, T domain);

    /**
     * 获取对象实体类
     * @param key
     * @return
     */
    T getValue(String key);

    void remove(String key);

    /**
     * 设置过期时间 * @param key 键 * @param timeout 时间 * @param timeUnit 时间单位
     */
    boolean expirse(String key, long timeout, TimeUnit timeUnit);
}

