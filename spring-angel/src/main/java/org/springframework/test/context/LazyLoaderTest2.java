package org.springframework.test.context;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

/**
 * @Description LazyLoaderTest2
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/7/10 2:55 下午
 **/
public class LazyLoaderTest2 {

	public static class BlogModel{
		private String title;

		private BlogContentModel blogContentModel;

		public BlogModel() {
			this.title="Spring AOP ";
			this.blogContentModel = this.getBlogContentModel();
		}

		private BlogContentModel getBlogContentModel(){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(BlogContentModel.class);
			enhancer.setCallback(new LazyLoader() {
				@Override
				public Object loadObject() throws Exception {
					//此处模拟数据查询
					System.out.println("开始从数据库获取博客内容");
					BlogContentModel result = new BlogContentModel();
					result.setContent("须弥子学Spring！");
					return result;
				}
			});
			return (BlogContentModel) enhancer.create();
		}
	}

	private static class BlogContentModel{
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public static void main(String[] args) {
		BlogModel blogModel = new BlogModel();
		System.out.println(blogModel.title);
		System.out.println("博客内容");
		System.out.println(blogModel.blogContentModel.getContent());
	}

}
