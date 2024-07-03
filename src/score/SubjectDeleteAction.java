package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        School school = teacher.getSchool();
        String cd = req.getParameter("cd");

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, school);

        if (subject == null) {
            req.setAttribute("error", "科目が存在しません");
        } else {
            req.setAttribute("subject", subject);
        }

        req.getRequestDispatcher("/score/subject_delete.jsp").forward(req, res);
    }
}
