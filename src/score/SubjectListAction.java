package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = Util.getUser(req);
        if (teacher != null) {
            SubjectDao subjectDao = new SubjectDao();
            req.setAttribute("subjects", subjectDao.filter(teacher.getSchool()));
            req.getRequestDispatcher("/score/subject_list.jsp").forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}