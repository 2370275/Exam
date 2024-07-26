package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class StudentCreateDoneAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute("ent_year");
        session.removeAttribute("no");
        session.removeAttribute("name");
        session.removeAttribute("class_num");
        session.removeAttribute("errorEntYear");
        session.removeAttribute("errorStudentNumber");
        session.removeAttribute("error");
        req.getRequestDispatcher("/score/student_create_done.jsp").forward(req, res);
    }
}
