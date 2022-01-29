/*
 * Copyright 2020 KrnSaurabh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.resilience4j.core;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

/**
 * 注册表存储对象
 * @param <E>
 */
public interface RegistryStore<E> {


    /**
     * 向存储容器中设置数据
     * @param key
     * @param mappingFunction
     * @return
     */
    E computeIfAbsent(String key,
        Function<? super String, ? extends E> mappingFunction);

    /**
     * 向存储容器中设置数据
     * @param key
     * @param value
     * @return
     */
    E putIfAbsent(String key, E value);

    /**
     * 根据key搜索E
     * @param key
     * @return
     */
    Optional<E> find(String key);

    /**
     * 根据名称移除E
     * @param name
     * @return
     */
    Optional<E> remove(String name);

    /**
     * 根据名称替换E
     * @param name
     * @param newEntry
     * @return
     */
    Optional<E> replace(String name, E newEntry);

    /**
     * 获取所有E
     * @return
     */
    Collection<E> values();

}
