package entity;

//���޷���ֵ
//���޲���  ����������  ������+�����б�(���͡�)
public class Score {
	// ������߶���ı����� ���ԣ���Ա����
	// �ڷ�����߶���ı����� �ֲ��������߷�������
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
