package org.springframework.test.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description MySmartInstantiationAwareBeanPostProcessor
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/29 4:41 下午
 **/
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Nullable
	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println(beanClass);
		System.out.println("调用 MySmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors 方法");
		Constructor<?>[] constructors = beanClass.getDeclaredConstructors();
		if (constructors != null){
			//获取带有 @MyAutoWried 注解的构造器列表
			List<Constructor<?>> collect = Arrays.stream(constructors)
					.filter(constructor -> constructor.isAnnotationPresent(MyAutoWried.class))
					.collect(Collectors.toList());
			Constructor<?>[] constructors1 = collect.toArray(new Constructor<?>[collect.size()]);
			return constructors1.length != 0 ? constructors1 : null;
		}else {
			return null;
		}
	}

}
