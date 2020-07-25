package org.springframework.test.context.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @Description AwareBean
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/7/1 10:47 上午
 **/
public class AwareBean implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware {
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("setClassLoader:"+classLoader);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory:"+beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName:"+name);
	}
}
