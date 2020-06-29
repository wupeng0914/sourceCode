package org.springframework.test.context.model;

import org.springframework.test.context.MyAutoWried;

/**
 * @Description Person
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/29 4:37 下午
 **/
public class Person {

	private String name;

	private Integer age;

	public Person() {
		System.out.println("调用无参构造 Person()");
	}

	@MyAutoWried
	public Person(String name, Integer age) {
		System.out.println("调用带参构造 Person(String name, Integer age)");
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
