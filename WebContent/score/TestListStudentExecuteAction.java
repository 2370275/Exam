package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.TestListStudent;
import bean.User;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.isAuthenticated()) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve parameter from request
        String studentNumber = request.getParameter("f4");

        // Fetch student details
        Student student = new Student();
        student.setNo(studentNumber); // Assuming 'no' is the student number

        // Fetch and set data for the view
        TestListStudentDao testListStudentDao = new TestListStudentDao();
        List<TestListStudent> testListStudents = testListStudentDao.filter(student);

        request.setAttribute("students", testListStudents);
        request.setAttribute("student_name", ""); // Set student name from fetched data if needed
        request.setAttribute("student_number", studentNumber); // Pass student number for display

        // Forward to the JSP
        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}
