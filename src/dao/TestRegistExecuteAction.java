package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            throw new Exception("User not found in session");
        }

        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String studentNo = req.getParameter("studentNo");
        String pointStr = req.getParameter("point");

        if (entYearStr == null || classNum == null || subjectCd == null || studentNo == null || pointStr == null) {
            throw new Exception("All fields are required");
        }

        int entYear = Integer.parseInt(entYearStr);
        int point = Integer.parseInt(pointStr);

        // Insert into database
        String sql = "INSERT INTO test_scores (ent_year, class_num, subject_cd, student_no, point) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = new DAO().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subjectCd);
            stmt.setString(4, studentNo);
            stmt.setInt(5, point);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Database operation failed");
        }

        // Set message attribute for success notification
        req.setAttribute("message", "Test score registered successfully");

        // Fetch enrollment year set for dropdown population
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // Set enrollment year set as request attribute
        req.setAttribute("entYearSet", entYearSet);

        // Forward to the completion page
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
