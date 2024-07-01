	package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            Integer entYear = Integer.parseInt(req.getParameter("ent_year"));
            String studentNumber = req.getParameter("student-number");
            String studentName = req.getParameter("student-name");
            String classNum = req.getParameter("class_num");

            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                throw new Exception("User not found in session");
            }

            School school = teacher.getSchool();

            Student student = new Student();
            student.setName(studentName);
            student.setAttend(true);
            student.setClassNum(classNum);
            student.setEntYear(entYear);
            student.setNo(studentNumber);
            student.setSchool(school);

            StudentDao studentDao = new StudentDao();
            studentDao.save(student);

            req.getRequestDispatcher("/score/student_create_done.jsp").forward(req, res);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Invalid input: " + e.getMessage());
            req.getRequestDispatcher("/score/student_create.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while creating the student: " + e.getMessage());
            req.getRequestDispatcher("/score/student_create.jsp").forward(req, res);
        }
    }
}
