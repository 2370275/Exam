package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String studentId = request.getParameter("student_id");

        StudentDao studentDao = new StudentDao();
//        Student student = studentDao.find(studentId);
//
//        if (student == null) {
//            return "error.jsp";
//        }
//
//        List<ClassNum> classes = studentDao.getClasses();

//        request.setAttribute("student", student);
//        request.setAttribute("classes", classes);
//        return "student_update.jsp";

        return;
    }
}
