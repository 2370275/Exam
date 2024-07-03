package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
	private String subjectName;
	private String subject;
	private int num;
	private int point;

	public String getSubjectName() {
		return subjectName ;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNum() {
	    return num;
	}

	public void setNum(int num) {
	    this.num = num;
	}
	public int getPoint() {
	    return point;
	}

	public void setPoint(int point) {
	    this.point = point;
	}
}