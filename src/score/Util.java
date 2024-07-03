package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;

public class Util {

    public static Teacher getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Teacher) session.getAttribute("teacher");
    }

    public static void setClassNumSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjects = subjectDao.filter(teacher.getSchool());
            request.setAttribute("subjects", subjects);
        }
    }

    public static void setEntYearSet(HttpServletRequest request) {
        // 実装内容
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
        // 実装内容
    }
}
