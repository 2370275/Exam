package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateDoneAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.getRequestDispatcher("/score/student_create_done.jsp").forward(req, res);
    }
}
