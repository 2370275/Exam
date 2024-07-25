package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // リクエストパラメータを取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        // デバッグメッセージの追加
        System.out.println("Parameters - f1: " + f1 + ", f2: " + f2 + ", f3: " + f3 + ", f4: " + f4);

        if (f1 == null || f2 == null || f3 == null || f4 == null ||
            f1.equals("0") || f2.equals("0") || f3.equals("0") || f4.equals("0")) {
            request.setAttribute("message", "すべてのフィールドを入力してください");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        int entYear = Integer.parseInt(f1);
        String classNum = f2;
        String subjectCd = f3;
        int num = Integer.parseInt(f4);

        Teacher teacher = Util.getUser(request);
        TestDao testDao = new TestDao();
        Subject selectedSubject = null;

        // subjects 属性を設定
        Util.setSubjects(request);
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");

        // 科目名を取得
        for (Subject subject : subjects) {
            if (subject.getCd().equals(subjectCd)) {
                selectedSubject = subject;
                break;
            }
        }

        List<Test> students = testDao.filter(entYear, classNum, selectedSubject, num, teacher.getSchool());

        request.setAttribute("students", students);
        request.setAttribute("selectedSubject", selectedSubject);
        request.setAttribute("selectedNum", num);
        request.setAttribute("selectedClass", classNum);
        request.setAttribute("selectedYear", entYear);

        Util.setClassNumSet(request);
        Util.setEntYearSet(request);
        Util.setNumSet(request);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}
