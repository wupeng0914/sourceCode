package org.springframework.test.context.model;

/**
 * @Description Student
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/24 4:59 下午
 **/
public class Student {

	private String name;

	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String say(){
		return "HelloWorld !";
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", car=" + car +
				'}';
	}
}
