package org.springframework.test.context;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.model.Car;
import org.springframework.test.context.model.Person;
import org.springframework.test.context.model.Student;

import java.util.Arrays;

/**
 * @Description XmlContextTest
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/24 4:53 下午
 **/
public class XmlContextTest {

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/bean-context.xml");
//		Student student = (Student) context.getBean("student");
//		System.out.println(student.say());

//		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
//		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//		System.out.println("结果为："+beanDefinition);
/***********************************************************************************************************************/
//		BeanDefinition carBeanDefinition =
//				BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName())
//						.addPropertyValue("name","奥迪")
//				.getBeanDefinition();
//
//		BeanDefinition studentBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Student.class.getName())
//				.addPropertyValue("name", "小明")
//				.addPropertyReference("car", "car")
//				.getBeanDefinition();
//
//		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//		beanFactory.registerBeanDefinition("car", carBeanDefinition);
//		beanFactory.registerBeanDefinition("student", studentBeanDefinition);
//
//		System.out.println(beanFactory.getBean("car"));
//		System.out.println(beanFactory.getBean("student"));
/***********************************************************************************************************************/

//		BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder
//				.genericBeanDefinition(Car.class)
//				.addPropertyValue("name", "帕萨特")
//				.getBeanDefinition();
//
//		BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder
//				.genericBeanDefinition()
//				.setParentName("car1")
//				.getBeanDefinition();
//
//		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//		beanFactory.registerBeanDefinition("car1", carBeanDefinition1);
//		beanFactory.registerBeanDefinition("car2", carBeanDefinition2);
//
//		System.out.println(beanFactory.getBean("car1"));
//		System.out.println(beanFactory.getBean("car2"));

/***********************************************************************************************************************/

//		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//
//		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//		beanDefinition.setBeanClass(String.class);
//		beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0,"须弥子");
//
//		factory.registerBeanDefinition("name", beanDefinition);
//
//		System.out.println(factory.getBeanDefinition("name"));
//		System.out.println(factory.containsBeanDefinition("name"));
//		System.out.println(Arrays.asList(factory.getBeanDefinitionNames()));
//		System.out.println(factory.getBeanDefinitionCount());
//		System.out.println(factory.isBeanNameInUse("name"));
//
//		factory.registerAlias("name", "alias-name-1");
//		factory.registerAlias("name", "alias-name-2");
//
//		System.out.println(factory.isAlias("alias-name-1"));
//		System.out.println(Arrays.asList(factory.getAliases("name")));
//		System.out.println(factory.getBean("name"));

/************************************************** BeanDefinition 合并 ************************************************/
//		testBeanDefinition();
/**************************************************  BeanPostProcessor  ***********************************************/
		testPostProcessor();

	}

	//BeanDefinition 合并测试
	public static void testBeanDefinition(){
		//创建bean容器
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		//创建一个bean xml解析器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);
		//解析bean xml，将解析过程中产生的BeanDefinition注册到DefaultListableBeanFactory容器中
		beanDefinitionReader.loadBeanDefinitions("context/bean-parent.xml");
		//遍历容器中注册的所有bean信息
		for (String beanName : factory.getBeanDefinitionNames()){
			//通过bean名称获取原始的注册的BeanDefinition信息
			BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
			//获取合并之后的BeanDefinition信息
			BeanDefinition mergeBeanDefinition = factory.getMergedBeanDefinition(beanName);

			System.out.println(beanName);
			System.out.println("解析xml过程中注册的beanDefinition："+beanDefinition);
			System.out.println("beanDefinition中的属性信息："+beanDefinition.getPropertyValues());
			System.out.println("合并之后得到的mergeBeanDefinition："+mergeBeanDefinition);
			System.out.println("mergeBeanDefinition中的属性信息："+mergeBeanDefinition.getPropertyValues());
			System.out.println("------------------------------------------------------------------------");
		}
	}

	public static void testPostProcessor(){
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

		factory.addBeanPostProcessor(new MySmartInstantiationAwareBeanPostProcessor());

		factory.registerBeanDefinition("name", BeanDefinitionBuilder
				.genericBeanDefinition(String.class)
				.addConstructorArgValue("须弥子")
				.getBeanDefinition());

		factory.registerBeanDefinition("age", BeanDefinitionBuilder
				.genericBeanDefinition(Integer.class)
				.addConstructorArgValue(20)
				.getBeanDefinition());

		factory.registerBeanDefinition("person", BeanDefinitionBuilder
				.genericBeanDefinition(Person.class)
				.getBeanDefinition());

		Person person = factory.getBean("person", Person.class);

		System.out.println(person);

	}



}
