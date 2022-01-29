/*
 *
 *  Copyright 2019 Mahmoud Romeh, Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package io.github.resilience4j.core;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEvent;
import io.vavr.collection.Map;

import java.util.Optional;

/**
 * root resilience4j registry to be used by resilience types registries for common functionality
 *
 * 注册器
 * @param <E>  注册接口
 * @param <C> 配置对象
 */
public interface Registry<E, C> {

    /**
     * Adds a configuration to the registry
     *
     * 添加配置
     * @param configName    the configuration name 配置名称
     * @param configuration the added configuration 配置对象
     */
    void addConfiguration(String configName, C configuration);

    /**
     * Find a named entry in the Registry
     * 根据名称搜索E
     *
     * @param name the  name
     */
    Optional<E> find(String name);

    /**
     * Remove an entry from the Registry
     *
     * 根据名称移除E
     * @param name the  name
     */
    Optional<E> remove(String name);

    /**
     * Replace an existing entry in the Registry by a new one.
     *
     * 根据名称替换E
     * @param name     the existing name
     * @param newEntry a new entry
     */
    Optional<E> replace(String name, E newEntry);

    /**
     * Get a configuration by name
     * 根名称获取配置
     * @param configName the configuration name
     * @return the found configuration if any
     */
    Optional<C> getConfiguration(String configName);

    /**
     * Get the default configuration
     *
     * 获取默认配置
     * @return the default configuration
     */
    C getDefaultConfig();

    /**
     * 获取全局标签
     * @return global configured registry tags
     */
    Map<String, String> getTags();

    /**
     * Returns an EventPublisher which can be used to register event consumers.
     *
     * 获取事件推送器
     *
     * @return an EventPublisher
     */
    EventPublisher<E> getEventPublisher();

    /**
     * An EventPublisher can be used to register event consumers.
     */
    interface EventPublisher<E> extends io.github.resilience4j.core.EventPublisher<RegistryEvent> {

        /**
         * 在添加时执行的行为
         * @param eventConsumer
         * @return
         */
        EventPublisher<E> onEntryAdded(EventConsumer<EntryAddedEvent<E>> eventConsumer);

        /**
         * 在移除时执行的行为
         * @param eventConsumer
         * @return
         */
        EventPublisher<E> onEntryRemoved(EventConsumer<EntryRemovedEvent<E>> eventConsumer);

        /**
         * 在替换时执行的行为
         * @param eventConsumer
         * @return
         */
        EventPublisher<E> onEntryReplaced(EventConsumer<EntryReplacedEvent<E>> eventConsumer);
    }
}
