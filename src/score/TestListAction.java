package score;

import java.time.LocalDate;
import java.util.ArrayList;
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

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
   //     User user = (User) session.getAttribute("user");
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        if (teacher.getSchool() == null) {
            throw new Exception("Teacher's school information is missing");
        }

//        if (teacher == null || !user.isAuthenticated()) {
 //           response.sendRedirect("login.jsp");
 //           return;
  //      }

        School school = teacher.getSchool();

        // Initialize DAOs
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        List<String> classList = classNumDao.filter(teacher.getSchool());

        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }


        try {
            // Fetch subjects for the school
            List<Subject> subjects = subjectDao.filter(school);
            // Fetch class numbers for the school
            List<String> classNumbers = classNumDao.filter(school);


            request.setAttribute("class_num_set", classList);

            // Set attributes for JSP rendering
            request.setAttribute("subjects", subjects);
            request.setAttribute("classNumbers", classNumbers);

            // Forward to the JSP page
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            // Redirect or display error message
            response.sendRedirect("error.jsp");
        }
    }
}
