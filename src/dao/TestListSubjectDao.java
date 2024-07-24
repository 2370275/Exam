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
    private String baseSql = "SELECT st.ent_year, ts.student_no, st.name AS student_name, ts.class_num, ts.subject_cd, "+
    		 "MAX(CASE WHEN ts.NO = 1 THEN ts.point ELSE NULL END) AS point_1,"+
    		 "MAX(CASE WHEN ts.NO = 2 THEN ts.point ELSE NULL END) AS point_2 "+
    		"FROM test ts " +
    		"JOIN subject s ON ts.subject_cd = s.cd " +
    		"JOIN student st ON ts.student_no = st.NO " +
    		"WHERE st.ent_year = ? AND ts.class_num = ? AND ts.subject_cd = ? AND s.school_cd = ? " +
    		"GROUP BY st.ent_year, ts.student_no, st.name, ts.class_num, ts.subject_cd" ;



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
                Integer point1 = rSet.getObject("point_1") != null ? rSet.getInt("point_1") : -1;
                Integer point2 = rSet.getObject("point_2") != null ? rSet.getInt("point_2") : -1;

                // `putPoint` メソッドに点数を渡す
                testListSubject.putPoint(1, point1);
                testListSubject.putPoint(2, point2);

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
