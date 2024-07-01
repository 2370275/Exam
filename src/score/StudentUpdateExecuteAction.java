package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String studentId = request.getParameter("student_id");
        String entranceYear = request.getParameter("entrance_year");
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

//        Student student = new Student();
//        student.setId(studentId);
//        student.setEntranceYear(entranceYear);
//        student.setStudentNumber(studentNumber);
//        student.setName(name);
//        student.setClassNum(classNum);
//
//        StudentDao studentDao = new StudentDao();
//        studentDao.update(student);

//        return "student_update_done.jsp";
    }
}
