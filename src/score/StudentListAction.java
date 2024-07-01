package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        if (teacher.getSchool() == null) {
            throw new Exception("Teacher's school information is missing");
        }

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        int entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : 0;
        boolean isAttend = isAttendStr != null;

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        List<String> list = cNumDao.filter(teacher.getSchool());
        if (list == null) {
            throw new Exception("Class numbers not found for school: " + teacher.getSchool().getCd());
        }

        List<Student> students;

        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else {
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        if (students == null) {
            throw new Exception("No students found for the given criteria");
        }

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);
        req.setAttribute("students", students);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}
