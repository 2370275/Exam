package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                req.setAttribute("error", "ユーザーがログインしていません");
                req.getRequestDispatcher("/score/login.jsp").forward(req, res);
                return;
            }

            String cd = req.getParameter("cd");
            School school = teacher.getSchool();

            SubjectDao subjectDao = new SubjectDao();
            Subject subject = subjectDao.get(cd, school);

            if (subject == null) {
                req.setAttribute("error", "科目が存在していません");
                req.getRequestDispatcher("/score/subject_update.jsp").forward(req, res);
                return;
            } else {
                req.setAttribute("subject", subject);
            }

            req.getRequestDispatcher("/score/subject_update.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "エラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("/score/subject_update.jsp").forward(req, res);
        }
    }
}