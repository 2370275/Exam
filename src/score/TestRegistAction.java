package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        School school = teacher.getSchool();
        if (school == null) {
            throw new Exception("Teacher's school information is missing");
        }

        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        List<String> classNumList = classNumDao.filter(school);
        if (classNumList == null) {
            throw new Exception("Class numbers not found for school: " + school.getCd());
        }

        List<Subject> subjectList = subjectDao.filter(school);
        if (subjectList == null) {
            throw new Exception("Subjects not found for school: " + school.getCd());
        }

        req.setAttribute("class_num_list", classNumList);
        req.setAttribute("subject_list", subjectList);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
