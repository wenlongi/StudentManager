package entity;

//���޷���ֵ
//���޲���  ����������  ������+�����б�(���͡�)
public class Student {
	// ������߶���ı����� ���ԣ���Ա����
	// �ڷ�����߶���ı����� �ֲ��������߷�������
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
		// this.sex = "��";
		// if (sex.equals("Ů")) {
		// this.sex = "Ů";
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
