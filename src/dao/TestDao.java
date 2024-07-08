package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {

    public List<Test> get(Student student, Subject subject, School school, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getNo());
        statement.setString(2, subject.getCd());
        statement.setString(3, school.getCd());
        statement.setInt(4, no);
        ResultSet rSet = statement.executeQuery();
        list = postFilter(rSet, school);
        rSet.close();
        statement.close();
        connection.close();
        return list;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> filteredList = new ArrayList<>();
        while (rSet.next()) {
            Test test = new Test();
            test.setStudent(new Student());
            test.getStudent().setNo(rSet.getString("STUDENT_NO"));
            test.setSubject(new Subject());
            test.getSubject().setCd(rSet.getString("SUBJECT_CD"));
            test.setSchool(school);
            test.setNo(rSet.getInt("NO"));
            test.setPoint(rSet.getInt("POINT"));
            filteredList.add(test);
        }
        return filteredList;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT * FROM TEST WHERE SCHOOL_CD = ? AND SUBJECT_CD = ? AND CLASS_NUM = ? AND ENT_YEAR = ? AND NUM = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, school.getCd());
        statement.setString(2, subject.getCd());
        statement.setString(3, classNum);
        statement.setInt(4, entYear);
        statement.setInt(5, num);
        ResultSet rSet = statement.executeQuery();
        list = postFilter(rSet, school);
        rSet.close();
        statement.close();
        connection.close();
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        boolean result = true;
        for (Test test : list) {
            result &= save(test, connection);
        }
        connection.close();
        return result;
    }

    private boolean save(Test test, Connection connection) throws Exception {
        String sql = "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) VALUES(?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE POINT = VALUES(POINT)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, test.getStudent().getNo());
        statement.setString(2, test.getSubject().getCd());
        statement.setString(3, test.getSchool().getCd());
        statement.setInt(4, test.getNo());
        statement.setInt(5, test.getPoint());
        int result = statement.executeUpdate();
        statement.close();
        return result > 0;
    }

    public boolean delete(List<Test> list) throws Exception {
        Connection connection = getConnection();
        boolean result = true;
        for (Test test : list) {
            result &= delete(test, connection);
        }
        connection.close();
        return result;
    }

    private boolean delete(Test test, Connection connection) throws Exception {
        String sql = "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, test.getStudent().getNo());
        statement.setString(2, test.getSubject().getCd());
        statement.setString(3, test.getSchool().getCd());
        statement.setInt(4, test.getNo());
        int result = statement.executeUpdate();
        statement.close();
        return result > 0;
    }
}
