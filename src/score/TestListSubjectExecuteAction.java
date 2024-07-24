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
import bean.TestListSubject;
import dao.ClassNumDao;
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
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        if ("0".equals(entYearStr) || "0".equals(classNum) || "0".equals(subjectCd)) {
            request.setAttribute("error_message", "入学年度とクラスと科目を選択してください");
            request.getRequestDispatcher("TestList.action").forward(request, response);
            return;
        }


        SchoolDao schoolDao = new SchoolDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        try {
        	int entYear = Integer.parseInt(entYearStr);
            // Fetch school information
            School school = schoolDao.get(teacher.getSchool().getCd());

            // Fetch subject information
            Subject subject = subjectDao.get(subjectCd, school);

            if (subject == null) {
                throw new Exception("Subject not found for code: " + subjectCd);
            }

          //セレクトボックスの中身
            ClassNumDao classNumDao = new ClassNumDao();

            LocalDate todayDate = LocalDate.now();
            int year = todayDate.getYear();
            List<Integer> entYearSet = new ArrayList<>();
            for (int i = year - 10; i <= year; i++) {
                entYearSet.add(i);
            }

            List<Subject> subjects = subjectDao.filter(school);
            List<String> classNumbers = classNumDao.filter(school);

            request.setAttribute("subjects", subjects);
            request.setAttribute("classNumbers", classNumbers);
            request.setAttribute("ent_year_set", entYearSet);

            // Fetch test list subjects
            List<TestListSubject> testListSubjects = testListSubjectDao.filter(entYear, classNum, subject, school);

            // Set request attribute
            request.setAttribute("testListSubjects", testListSubjects);


            request.setAttribute("subject_cd", subject.getCd()); // Set student name from fetched data
            request.setAttribute("subject_name", subject.getName());

            // Forward to JSP page
            request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            // Redirect or display error message
            response.sendRedirect("error.jsp");
        }
    }
}
