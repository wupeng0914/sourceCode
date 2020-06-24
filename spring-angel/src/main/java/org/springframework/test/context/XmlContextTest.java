package org.springframework.test.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.model.Student;

/**
 * @Description XmlContextTest
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/24 4:53 下午
 **/
public class XmlContextTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/bean-context.xml");

		Student student = (Student) context.getBean("student");

		System.out.println(student.say());
	}

}
