package entity;

//有无返回值
//有无参数  方法的重载  方法名+参数列表(类型、)
public class Student {
	// 在类里边定义的变量叫 属性，成员变量
	// 在方法里边定义的变量叫 局部变量或者方法变量
	private String name;
	private String sex;
	private int age;
	private BanJi bj;

	public BanJi getBj() {
		return bj;
	}

	public void setBj(BanJi bj) {
		this.bj = bj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		// this.sex = "男";
		// if (sex.equals("女")) {
		// this.sex = "女";
		// }
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		// if (age > 0 && age < 120) {
		// this.age = age;
		// } else {
		// this.age = 20;
		// }
		this.age = age;
	}

}
