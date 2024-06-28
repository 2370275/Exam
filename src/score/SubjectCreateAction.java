package score;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Subject;

@WebServlet(urlPatterns = {"/score/Subject44"})
public class SubjectCreateAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjectList = new ArrayList<>();

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/JSE");
            try (Connection con = ds.getConnection();
                 PreparedStatement st = con.prepareStatement("SELECT * FROM SUBJECT");
                 ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rs.getInt("CD"));
                    subject.setName(rs.getString("NAME"));
                    subject.setSchool_cd(rs.getString("SCHOOL_CD"));
                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    }
}
