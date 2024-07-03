package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            String subjectCd = req.getParameter("subject_cd");
            String subjectName = req.getParameter("subject_name");

            if (subjectCd == null || subjectCd.isEmpty() || subjectCd.length() != 3) {
                req.setAttribute("error", "科目コードは3文字で入力してください");
                req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
                return;
            }

            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                throw new Exception("Teacher not found in session");
            }

            School school = teacher.getSchool();

            SubjectDao subjectDao = new SubjectDao();
            if (subjectDao.isSubjectCdExists(subjectCd)) {
                req.setAttribute("error", "科目コードが重複しています");
                req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
                return;
            }

            Subject subject = new Subject();
            subject.setCd(subjectCd);
            subject.setName(subjectName);
            subject.setSchool(school);

            subjectDao.save(subject);

            req.getRequestDispatcher("/score/subject_create_done.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while creating the subject: " + e.getMessage());
            req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
        }
    }
}
