package org.springframework.test.context;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

/**
 * @Description LazyLoaderTest1
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/7/10 11:33 上午
 **/
public class LazyLoaderTest1 {

	public static class UserModel{
		private String name;

		public UserModel() {
		}

		public UserModel(String name) {
			this.name = name;
		}

		public void say(){
			System.out.println("Hello," + name);
		}
	}

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserModel.class);

		LazyLoader lazyLoader = new LazyLoader() {
			@Override
			public Object loadObject() throws Exception {
				System.out.println("调用LazyLoader.loadObject()方法");
				return new UserModel("须弥子");
			}
		};

		enhancer.setCallback(lazyLoader);
		Object proxy = enhancer.create();
		UserModel userModel = (UserModel) proxy;
		System.out.println("第一次调用say方法");
		userModel.say();
		System.out.println("第二次调用say方法");
		userModel.say();
	}

}
