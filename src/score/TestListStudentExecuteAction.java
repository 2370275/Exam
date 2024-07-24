package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        if (teacher.getSchool() == null) {
            throw new Exception("Teacher's school information is missing");
        }

        // Retrieve parameter from request
        String studentNumber = request.getParameter("f4");

        System.out.println("number: " + studentNumber);

        if (studentNumber == null) {
            throw new Exception("Student not found");
        }

        // Fetch student details using StudentDao
        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();

        Student student = studentDao.get(studentNumber);

        List<TestListStudent> testListStudents = testListStudentDao.filter(student);

        request.setAttribute("testListStudents", testListStudents);
        request.setAttribute("student_name", student.getName()); // Set student name from fetched data
        request.setAttribute("student_number", studentNumber); // Pass student number for display

        // Forward to the JSP
        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}
