package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Util.setClassNumSet(request);
        Util.setEntYearSet(request);
        Util.setSubjects(request);
        Util.setNumSet(request);

        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        if (f1 != null && f2 != null && f3 != null && f4 != null) {
            if (f1.equals("0") || f2.equals("0") || f3.equals("0") || f4.equals("0")) {
                request.setAttribute("message", "すべてのフィールドを入力してください");
                request.getRequestDispatcher("test_regist.jsp").forward(request, response);
                return;
            }

            int entYear = Integer.parseInt(f1);
            String classNum = f2;
            String subjectCd = f3;
            int num = Integer.parseInt(f4);

            Subject subject = new Subject();
            subject.setCd(subjectCd);
            // 科目名を取得し、設定するロジックを追加
            for (Subject s : (List<Subject>) request.getAttribute("subjects")) {
                if (s.getCd().equals(subjectCd)) {
                    subject.setName(s.getName());
                    break;
                }
            }

            TestDao testDao = new TestDao();
            List<Test> students = testDao.filter(entYear, classNum, subject, num, Util.getUser(request).getSchool());

            request.setAttribute("students", students);
            request.setAttribute("selectedSubject", subject);
            request.setAttribute("selectedNum", num);
            request.setAttribute("selectedClass", classNum);
            request.setAttribute("selectedYear", entYear);
        }

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}
