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
            String entYearStr = req.getParameter("ent_year");
            HttpSession session = req.getSession();

            if (entYearStr == null || entYearStr.equals("0") || entYearStr.equals("----------")) {
                session.setAttribute("errorEntYear", "入学年度を選択してください");
                res.sendRedirect("StudentCreate.action");
                return;
            }

            Integer entYear = Integer.parseInt(entYearStr);
            String studentNumber = req.getParameter("student_number");
            String studentName = req.getParameter("student_name");
            String classNum = req.getParameter("class_num");
            boolean isAttend = req.getParameter("is_attend") != null;

            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                throw new Exception("Teacher not found in session");
            }

            School school = teacher.getSchool();

            StudentDao studentDao = new StudentDao();
            if (studentDao.isStudentNumberExists(studentNumber)) {
                session.setAttribute("errorStudentNumber", "学生番号が重複しています");
                res.sendRedirect("StudentCreate.action");
                return;
            }

            Student student = new Student();
            student.setName(studentName);
            student.setAttend(isAttend);
            student.setClassNum(classNum);
            student.setEntYear(entYear);
            student.setNo(studentNumber);
            student.setSchool(school);

            studentDao.save(student);

            res.sendRedirect("StudentCreateDone.action");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Invalid input: " + e.getMessage());
            res.sendRedirect("StudentCreate.action");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while creating the student: " + e.getMessage());
            res.sendRedirect("StudentCreate.action");
        }
    }
}
