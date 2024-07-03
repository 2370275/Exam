package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
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
        boolean result = subjectDao.deleteByCd(cd, school);

        if (result) {
            req.getRequestDispatcher("/score/subject_delete_done.jsp").forward(req, res);

            if (result) {
                req.getRequestDispatcher("/score/subject_delete_done.jsp").forward(req, res);
            } else {
                req.setAttribute("error", "科目の削除に失敗しました");
                req.getRequestDispatcher("/score/subject_delete.jsp").forward(req, res);
            }
        }
    }
}