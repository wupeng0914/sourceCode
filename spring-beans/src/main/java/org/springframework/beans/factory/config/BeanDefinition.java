/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * bean定义信息的接口，定义了获取bean定义配置信息的各种方法
 *
 * AttributeAccessor：
 * 		这个接口相当于key->value数据结构的一种操作，BeanDefinition继承这个接口，
 * 		内部实际上是使用了LinkedHashMap来实现这个接口中的所有方法，通常我们通过这
 * 		些方法来保存BeanDefinition定义过程中产生的一些附加信息。
 *
 * BeanMetadataElement：
 * 		BeanDefinition继承这个接口，通过实现getSource()方法来返回BeanDefinition定义的来源，
 * 		比如我们通过xml定义BeanDefinition的，此时getSource()就表示定义bean的xml资源；若我们通
 * 		过api的方式定义BeanDefinition，我们可以将source设置为定义BeanDefinition时所在的类，
 * 		出错时，可以根据这个来源方便排错。
 */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
	 * 单例bean 的生命周期singleton
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 * 多例bean 的生命周期 prototype
	 */
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/**
	 * Role hint indicating that a {@code BeanDefinition} is a major part
	 * of the application. Typically corresponds to a user-defined bean.
	 */
	int ROLE_APPLICATION = 0;

	/**
	 * Role hint indicating that a {@code BeanDefinition} is a supporting
	 * part of some larger configuration, typically an outer
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 * {@code SUPPORT} beans are considered important enough to be aware
	 * of when looking more closely at a particular
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition},
	 * but not when looking at the overall configuration of an application.
	 */
	int ROLE_SUPPORT = 1;

	/**
	 * Role hint indicating that a {@code BeanDefinition} is providing an
	 * entirely background role and has no relevance to the end-user. This hint is
	 * used when registering beans that are completely part of the internal workings
	 * of a {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 */
	int ROLE_INFRASTRUCTURE = 2;

	/**
	 * 根据指定的parentBeanName为bean设置其父级bean
	 */
	void setParentName(@Nullable String parentName);

	/**
	 * 如果这个bean在定义时指定了父级bean，则返回此父级bean的名称
	 */
	@Nullable
	String getParentName();

	/**
	 * 指定此bean定义的bean类名(对应xml中bean元素的class属性)
	 */
	void setBeanClassName(@Nullable String beanClassName);

	/**
	 * 返回此bean定义的当前bean类名
	 * 注意，如果子定义重写/继承其父类的类名，则这不一定是运行时使用的实际类名。
	 * 此外，这可能只是调用工厂方法的类，或者在调用方法的工厂bean引用的情况下，它甚至可能是空的。
	 * 因此，不要认为这是运行时的最终bean类型，而只将其用于单个bean定义级别的解析目的。
	 */
	@Nullable
	String getBeanClassName();

	/**
	 * 设置此bean的生命周期，如：singleton、prototype（对应xml中bean元素的scope属性）
	 */
	void setScope(@Nullable String scope);

	/**
	 * 返回此bean的生命周期，如：singleton、prototype
	 */
	@Nullable
	String getScope();

	/**
	 * 设置是否应延迟初始化此bean（对应xml中bean元素的lazy属性）
	 */
	void setLazyInit(boolean lazyInit);

	/**
	 * 返回是否应延迟初始化此bean，只对单例bean有效
	 */
	boolean isLazyInit();

	/**
	 * 设置此bean在初始化的时候所依赖的bean的beanName。
	 * bean工厂将保证通过dependsOn指定的依赖bean会在当前bean初始化之前完成初始化
	 */
	void setDependsOn(@Nullable String... dependsOn);

	/**
	 * 返回当前bean所依赖的bean的beanName
	 */
	@Nullable
	String[] getDependsOn();

	/**
	 * 设置此bean是否作为其他bean自动注入时的候选者
	 */
	void setAutowireCandidate(boolean autowireCandidate);

	/**
	 * 返回此bean是否作为其他bean自动注入时的候选者
	 */
	boolean isAutowireCandidate();

	/**
	 * 设置此bean是否为自动注入的主要候选者
	 * primary：是否为主要候选者
	 */
	void setPrimary(boolean primary);

	/**
	 * 返回此bean是否被指定为自动注入的主要候选者
	 */
	boolean isPrimary();

	/**
	 * 指定要使用的工厂bean（如果有）。这是要对其调用指定工厂方法的bean的名称。
	 * factoryBeanName：工厂bean名称
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);

	/**
	 * 返回工厂bean名称（如果有）（对应xml中bean元素的factory-bean属性）
	 */
	@Nullable
	String getFactoryBeanName();

	/**
	 * 指定工厂方法（如果有）。此方法将使用构造函数参数调用，如果未指定任何参数，
	 * 则不使用任何参数调用。该方法将在指定的工厂bean（如果有的话）上调用，或者作为本地bean类上的静态方法调用。
	 * factoryMethodName：工厂方法名称
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);

	/**
	 * 返回工厂方法名称（对应xml中bean的factory-method属性）
	 */
	@Nullable
	String getFactoryMethodName();

	/**
	 * 返回此bean的构造函数参数值
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * 是否有构造器参数值设置信息（对应xml中bean元素的<constructor-arg />子元素）
	 * @since 5.0.2
	 */
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 * 获取bean定义是配置的属性值设置信息
	 */
	MutablePropertyValues getPropertyValues();

	/**
	 * 这个bean定义中是否有属性设置信息（对应xml中bean元素的<property />子元素）
	 * @since 5.0.2
	 */
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	/**
	 * 设置bean初始化方法名称
	 * @since 5.1
	 */
	void setInitMethodName(@Nullable String initMethodName);

	/**
	 * 返回bean初始化方法名称
	 * @since 5.1
	 */
	@Nullable
	String getInitMethodName();

	/**
	 * 设置bean销毁方法的名称
	 * @since 5.1
	 */
	void setDestroyMethodName(@Nullable String destroyMethodName);

	/**
	 * bean销毁的方法名称
	 * @since 5.1
	 */
	@Nullable
	String getDestroyMethodName();

	/**
	 * 设置bean的role信息
	 */
	void setRole(int role);

	/**
	 * bean定义的role信息
	 */
	int getRole();

	/**
	 * 设置bean描述信息
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);

	/**
	 * 返回bean描述信息
	 */
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * bean类型解析器
	 * @since 5.2
	 * @see ConfigurableBeanFactory#getMergedBeanDefinition
	 */
	ResolvableType getResolvableType();

	/**
	 * 是否是单例的bean
	 */
	boolean isSingleton();

	/**
	 * 是否是多列的bean
	 * @since 3.0
	 * @see #SCOPE_PROTOTYPE
	 */
	boolean isPrototype();

	/**
	 * 对应xml中bean元素的abstract属性，用来指定是否是抽象的
	 */
	boolean isAbstract();

	/**
	 * 返回此bean定义来自的资源的描述（以便在出现错误时显示上下文）
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * Return the originating BeanDefinition, or {@code null} if none.
	 * Allows for retrieving the decorated bean definition, if any.
	 * <p>Note that this method returns the immediate originator. Iterate through the
	 * originator chain to find the original BeanDefinition as defined by the user.
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
