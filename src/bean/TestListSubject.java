package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {
	//private String point;
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private HashMap<Integer,Integer> points = new HashMap<Integer,Integer>();

	public int getEntYear() {
	    return entYear;
	}
	public void setEntYear(int entYear) {
	    this.entYear = entYear;
	}
	public String getStudentNo() {
		return studentNo ;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return points;
	}
	public void  setPoints(HashMap<Integer,Integer> points) {
		this.points = points;
	}
	public String getPoint(int key) {
		return points.get(key).toString() ;
	}
	public void putPoint(int key,int value) {
		points.put(key,value);
	}

}