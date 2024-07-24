package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends DAO {
    private String baseSql = "SELECT subject.name, test.subject_cd, test.no, test.point " +
                         	"FROM test " +
                         	"JOIN subject ON test.subject_cd = subject.cd " +
                         	"WHERE student_no = ?";

    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        try {
            while (rSet.next()) {
                // Initialize TestListStudent instance
                TestListStudent testListStudent = new TestListStudent();

                // Set values from the result set
                testListStudent.setSubjectName(rSet.getString("name"));
                testListStudent.setSubject(rSet.getString("subject_cd"));
                testListStudent.setNum(rSet.getInt("no"));
                testListStudent.setPoint(rSet.getInt("point"));

                // Add to list
                list.add(testListStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
            throw e;  // Re-throw the exception after logging
        }

        return list;
    }


    public List<TestListStudent> filter(Student student) {
        List<TestListStudent> testListStudents = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(baseSql)) {
            stmt.setString(1, student.getNo()); // Using the 'no' field as the student identifier
            try (ResultSet rSet = stmt.executeQuery()) {
                testListStudents = postFilter(rSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testListStudents;
    }
}
