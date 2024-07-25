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

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = new Test();
        Connection connection = getConnection();
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getNo());
        statement.setString(2, subject.getCd());
        statement.setString(3, school.getCd());
        statement.setInt(4, no);
        ResultSet rSet = statement.executeQuery();
        if (rSet.next()) {
            test.setStudent(new Student());
            test.getStudent().setNo(rSet.getString("STUDENT_NO"));
            test.setSubject(new Subject());
            test.getSubject().setCd(rSet.getString("SUBJECT_CD"));
            test.setSchool(school);
            test.setNo(rSet.getInt("NO"));
            test.setPoint(rSet.getInt("POINT"));
            test.setClassNum(rSet.getString("CLASS_NUM")); // クラス番号を設定
        }
        rSet.close();
        statement.close();
        connection.close();
        return test;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = "SELECT s.NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, t.POINT "
                   + "FROM STUDENT s "
                   + "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO "
                   + "AND t.SCHOOL_CD = ? "
                   + "AND t.SUBJECT_CD = ? "
                   + "AND t.NO = ? "
                   + "WHERE s.SCHOOL_CD = ? "
                   + "AND s.CLASS_NUM = ? "
                   + "AND s.ENT_YEAR = ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, school.getCd());
        st.setString(2, subject.getCd());
        st.setInt(3, num);
        st.setString(4, school.getCd());
        st.setString(5, classNum);
        st.setInt(6, entYear);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test test = new Test();
            test.setStudent(new Student());
            test.getStudent().setNo(rs.getString("NO"));
            test.getStudent().setName(rs.getString("NAME"));
            test.getStudent().setEntYear(rs.getInt("ENT_YEAR"));
            test.getStudent().setClassNum(rs.getString("CLASS_NUM"));
            test.setPoint(rs.getInt("POINT"));
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(classNum); // クラス番号を設定
            list.add(test);
        }

        st.close();
        con.close();

        return list;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> filteredList = new ArrayList<>();
        while (rSet.next()) {
            Test test = new Test();
            Student student = new Student();
            student.setNo(rSet.getString("STUDENT_NO"));
            student.setEntYear(rSet.getInt("ENT_YEAR"));
            student.setClassNum(rSet.getString("CLASS_NUM"));
            student.setName(rSet.getString("NAME"));

            test.setStudent(student);
            test.setSubject(new Subject());
            test.getSubject().setCd(rSet.getString("SUBJECT_CD"));
            test.setSchool(school);
            test.setNo(rSet.getInt("NO"));
            test.setPoint(rSet.getInt("POINT"));
            test.setClassNum(rSet.getString("CLASS_NUM")); // クラス番号を設定
            filteredList.add(test);
        }
        return filteredList;
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

    public boolean save(Test test) throws Exception {
        Connection connection = getConnection();
        boolean result = save(test, connection);
        connection.close();
        return result;
    }

    private boolean save(Test test, Connection connection) throws Exception {
        // デバッグメッセージを追加
        if (test == null) {
            System.out.println("Test object is null");
        }
        if (test.getStudent() == null) {
            System.out.println("Student object is null");
        }
        if (test.getSubject() == null) {
            System.out.println("Subject object is null");
        }
        if (test.getSchool() == null) {
            System.out.println("School object is null");
        }

        String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, test.getStudent().getNo());
        statement.setString(2, test.getSubject().getCd());
        statement.setString(3, test.getSchool().getCd());
        statement.setInt(4, test.getNo());
        statement.setInt(5, test.getPoint());
        statement.setString(6, test.getClassNum()); // クラス番号を設定

        System.out.println("Executing SQL: " + statement.toString());
        int result = statement.executeUpdate();
        System.out.println("SQL execution result: " + result);

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
