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
            String studentNumber = req.getParameter("no");
            String studentName = req.getParameter("name");
            String classNum = req.getParameter("class_num");

            HttpSession session = req.getSession();
            session.setAttribute("ent_year", entYearStr);
            session.setAttribute("no", studentNumber);
            session.setAttribute("name", studentName);
            session.setAttribute("class_num", classNum);

            if (entYearStr == null || entYearStr.equals("0") || entYearStr.equals("----------")) {
                session.setAttribute("errorEntYear", "入学年度を選択してください");
                res.sendRedirect("StudentCreate.action");
                return;
            }

            Integer entYear = Integer.parseInt(entYearStr);
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

            // セッション変数のクリア
            session.removeAttribute("ent_year");
            session.removeAttribute("no");
            session.removeAttribute("name");
            session.removeAttribute("class_num");
            session.removeAttribute("errorEntYear");
            session.removeAttribute("errorStudentNumber");
            session.removeAttribute("error");

            res.sendRedirect("StudentCreateDone.action");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            HttpSession session = req.getSession();
            session.setAttribute("error", "Invalid input: " + e.getMessage());
            res.sendRedirect("StudentCreate.action");
        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = req.getSession();
            session.setAttribute("error", "An error occurred while creating the student: " + e.getMessage());
            res.sendRedirect("StudentCreate.action");
        }
    }
}
