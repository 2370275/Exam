package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
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

        //セレクトボックスの中身
        SubjectDao subjectDao = new SubjectDao();

        ClassNumDao classNumDao = new ClassNumDao();

        School school = teacher.getSchool();

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


        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}
