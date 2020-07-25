package org.springframework.test.context;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description DispatcherTest
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/7/10 11:27 上午
 **/
public class DispatcherTest {

	public static class UserModel{

		private String name;

		public UserModel() {
		}

		public UserModel(String name) {
			this.name = name;
		}

		public void say(){
			System.out.println("Hello，" + name);
		}
	}

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass();
	}

}
