package org.springframework.test.context.model;

/**
 * @Description Lesson
 * @Author wupeng
 * @Motto Stay Hungry, Stay Foolish !
 * @Date 2020/6/29 11:08 上午
 **/
public class Lesson {

	private String name;

	private int lessonCount;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLessonCount() {
		return lessonCount;
	}

	public void setLessonCount(int lessonCount) {
		this.lessonCount = lessonCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Lesson{" +
				"name='" + name + '\'' +
				", lessonCount=" + lessonCount +
				", description='" + description + '\'' +
				'}';
	}
}
