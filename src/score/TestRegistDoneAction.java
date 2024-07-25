package score;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistDoneAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        int num = Integer.parseInt(request.getParameter("selectedNum"));
        Subject subject = new Subject();
        subject.setCd(request.getParameter("selectedSubject"));
        String classNum = request.getParameter("selectedClass"); // 修正

        Teacher teacher = Util.getUser(request);
        School school = teacher.getSchool();

        List<Test> tests = new ArrayList<>();
        boolean isValid = true;
        for (String key : request.getParameterMap().keySet()) {
            if (key.startsWith("point_")) {
                String studentNo = key.substring(6);
                int point = Integer.parseInt(request.getParameter(key));
                if (point < 0 || point > 100) {
                    isValid = false;
                    break;
                }
                Student student = new Student();
                student.setNo(studentNo);

                Test test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(num);
                test.setPoint(point);
                test.setClassNum(classNum);

                // デバッグ用ログ
                System.out.println("Student No: " + studentNo + ", ClassNum: " + classNum);

                tests.add(test);
            }
        }

        if (!isValid) {
            request.setAttribute("message", "点数は0から100の範囲で入力してください。");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        TestDao testDao = new TestDao();
        boolean result = testDao.save(tests);

        if (result) {
            request.setAttribute("message", "登録が完了しました。");
        } else {
            request.setAttribute("message", "登録に失敗しました。");
        }

        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}
