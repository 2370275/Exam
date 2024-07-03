package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO {
    private String baseSql = "SELECT ts.ent_year, ts.student_no, ts.student_name, ts.class_num, ts.subject, ts.num, ts.point " +
                             "FROM test_scores ts " +
                             "JOIN subjects s ON ts.subject = s.cd " +
                             "WHERE ts.ent_year = ? AND ts.class_num = ? AND ts.subject = ? AND s.school_cd = ?";

    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListSubject testListSubject = new TestListSubject();
                testListSubject.setEntYear(rSet.getInt("ent_year"));
                testListSubject.setStudentNo(rSet.getString("student_no"));
                testListSubject.setStudentName(rSet.getString("student_name"));
                testListSubject.setClassNum(rSet.getString("class_num"));

                // Add the points to the existing points map
                testListSubject.putPoint(rSet.getInt("num"), rSet.getInt("point"));

                list.add(testListSubject);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {
        List<TestListSubject> testListSubjects = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(baseSql)) {
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            stmt.setString(4, school.getCd());

            try (ResultSet rSet = stmt.executeQuery()) {
                testListSubjects = postFilter(rSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testListSubjects;
    }
}
