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

            boolean hasError = false;

            // 科目コードがnull、空、または3文字でない場合のチェック
            if (subjectCd == null || subjectCd.isEmpty() || subjectCd.length() != 3) {
                req.setAttribute("errorSubjectCd", "科目コードは3文字で入力してください");
                hasError = true;
            }

            SubjectDao subjectDao = new SubjectDao();
            // 科目コードがすでに存在するかどうかのチェック
            if (!hasError && subjectDao.isSubjectCdExists(subjectCd)) {
                req.setAttribute("errorSubjectCd", "科目コードが重複しています");
                hasError = true;
            }

            if (hasError) {
                req.setAttribute("subject_cd", subjectCd);
                req.setAttribute("subject_name", subjectName);
                req.getRequestDispatcher("subject_create.jsp").forward(req, res);
                return;
            }

            HttpSession session = req.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                throw new Exception("セッションに教師が見つかりません");
            }

            School school = teacher.getSchool();

            Subject subject = new Subject();
            subject.setCd(subjectCd);
            subject.setName(subjectName);
            subject.setSchool(school);

            subjectDao.save(subject);

            req.getRequestDispatcher("/score/subject_create_done.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "科目の作成中にエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
        }
    }
}
