package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classList = cNumDao.filter(teacher.getSchool());

        System.out.println("Class list retrieved: " + classList);  // ログ出力

        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
            System.out.println("Entry year: " + i);  // ログ出力
        }

        req.setAttribute("class_num_set", classList);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("student_create.jsp").forward(req, res);
    }
}
