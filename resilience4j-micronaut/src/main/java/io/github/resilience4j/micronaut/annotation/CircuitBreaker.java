/*
 * Copyright 2017 Robert Winkler
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
package io.github.resilience4j.micronaut.annotation;

import io.github.resilience4j.micronaut.circuitbreaker.CircuitBreakerInterceptor;
import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Executable;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.*;

/**
 * This annotation can be applied to a class or a specific method. Applying it on a class is
 * equivalent to applying it on all its public methods. The annotation enables backend monitoring
 * for all methods where it is applied. Backend monitoring is performed via a circuit breaker. See
 * {@link io.github.resilience4j.circuitbreaker.CircuitBreaker} for details.
 *
 * 熔断注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Around
@Type(CircuitBreakerInterceptor.class)
@Documented
@Executable
public @interface CircuitBreaker {

    /**
     * Name of the circuit breaker.
     *
     * @return the name of the circuit breaker
     */
    String name();

    /**
     * fallbackMethod method name.
     * 回退方法
     * @return fallbackMethod method name.
     */
    String fallbackMethod() default "";
}
