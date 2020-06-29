package org.springframework.test.context.model;

/**
 * @Description Car
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/28 11:46 上午
 **/
public class Car {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car{" +
				"name='" + name + '\'' +
				'}';
	}
}
