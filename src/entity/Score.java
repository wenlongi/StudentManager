package entity;

//有无返回值
//有无参数  方法的重载  方法名+参数列表(类型、)
public class Score {
	// 在类里边定义的变量叫 属性，成员变量
	// 在方法里边定义的变量叫 局部变量或者方法变量
	private int id;
	private Student stu;
	private Subject sub;
	private Integer result;
	private String grade;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public Subject getSub() {
		return sub;
	}

	public void setSub(Subject sub) {
		this.sub = sub;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

}
