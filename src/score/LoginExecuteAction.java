package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        TeacherDao dao = new TeacherDao();

        try {
            Teacher teacher = dao.login(id, password);
            if (teacher != null) {
                session.setAttribute("isLoggedIn", true);
                session.setAttribute("teacher", teacher);
                session.removeAttribute("errorMessage"); // ログイン成功時にエラーメッセージをクリア
                response.sendRedirect("menu.jsp");
            } else {
                session.setAttribute("errorMessage", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            session.setAttribute("errorMessage", "エラーが発生しました。");
            response.sendRedirect("login.jsp");
        }
    }
}
