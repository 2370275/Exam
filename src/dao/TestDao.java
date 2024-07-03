package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {
    private String baseSql = "SELECT * FROM TEST WHERE SCHOOL_CD = ?";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM TEST WHERE NO = ?");
            statement.setInt(1, no);
            rSet = statement.executeQuery();


            if (rSet.next()) {
            	test = new Test();
                test.setNo(rSet.getInt("no"));
                test.setClassNum(rSet.getString("class_num"));
                test.setPoint(rSet.getInt("point"));
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (rSet != null) {
            	try{
            		rSet.close();
            	} catch (SQLException sqle) {
            		throw sqle;
            	}
            }
            if (statement != null) {
            	try{
            		statement.close();
            	} catch (SQLException sqle){
            		throw sqle;
            	}
            }
            if (connection != null) {
            	try{
            		connection.close();
            	} catch (SQLException sqle){
            		throw sqle;
            	}
            }
        }
        return test;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception{
    	List<Test> list = new ArrayList<>();
    	try{
    		while (rSet.next()){
    			Test test = new Test();
    			test.setNo(rSet.getInt("no"));
                test.setClassNum(rSet.getString("class_num"));
                test.setPoint(rSet.getInt("point"));
                test.setSchool(school);
                list.add(test);
    		}
    	} catch(SQLException | NullPointerException e){
    		e.printStackTrace();
    	}
    	return list;
    }
}
