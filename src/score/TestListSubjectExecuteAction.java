package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SchoolDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
 //       User user = (User) session.getAttribute("user");

 //       if (user == null || !user.isAuthenticated()) {
 //           response.sendRedirect("login.jsp");
//            return;
//        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        if (teacher.getSchool() == null) {
            throw new Exception("Teacher's school information is missing");
        }

        // Retrieve request parameters
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");

        // Initialize DAOs
        SchoolDao schoolDao = new SchoolDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        try {
            // Fetch school information
            School school = schoolDao.get(teacher.getSchool().getCd());

            // Fetch subject information
            Subject subject = subjectDao.get(subjectCd, school);

            // Fetch test list subjects
            List<TestListSubject> testListSubjects = testListSubjectDao.filter(entYear, classNum, subject, school);

            // Set request attribute
            request.setAttribute("testListSubjects", testListSubjects);

            // Forward to JSP page
            request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            // Redirect or display error message
            response.sendRedirect("error.jsp");
        }
    }
}
