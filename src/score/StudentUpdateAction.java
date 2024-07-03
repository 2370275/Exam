package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        String studentId = req.getParameter("no");
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentId);

        if (student == null) {
            throw new Exception("Student not found with ID: " + studentId);
        }

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classList = classNumDao.filter(teacher.getSchool());

        req.setAttribute("student", student);
        req.setAttribute("class_num_set", classList);
        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}
