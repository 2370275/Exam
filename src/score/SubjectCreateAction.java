package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = Util.getUser(req);
        if (teacher != null) {
            req.getRequestDispatcher("/score/subject_create.jsp").forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
