package score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.DAO;
import dao.SubjectDao;

public class Util {
    public static Teacher getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Teacher) session.getAttribute("teacher");
    }

    public static void setClassNumSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            School school = teacher.getSchool();
            ClassNumDao classNumDao = new ClassNumDao();
            List<String> classNums = classNumDao.filter(school);
            request.setAttribute("class_num_set", classNums);
        }
    }

    public static void setEntYearSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            School school = teacher.getSchool();
            DAO dao = new DAO();  // DAOクラスのインスタンスを作成
            Connection connection = dao.getConnection();
            String sql = "SELECT DISTINCT ENT_YEAR FROM STUDENT WHERE SCHOOL_CD = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            ResultSet rs = statement.executeQuery();
            List<Integer> entYears = new ArrayList<>();
            while (rs.next()) {
                entYears.add(rs.getInt("ENT_YEAR"));
            }
            rs.close();
            statement.close();
            connection.close();
            request.setAttribute("ent_year_set", entYears);
        }
    }

    public static void setSubjects(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjects = subjectDao.filter(teacher.getSchool());
            request.setAttribute("subjects", subjects);
        }
    }

    public static void setNumSet(HttpServletRequest request) {
        request.setAttribute("nums", Arrays.asList(1, 2)); // 回数は1, 2を設定
    }
}
