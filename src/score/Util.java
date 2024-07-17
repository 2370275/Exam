package score;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;

public class Util {

    public static Teacher getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Teacher) session.getAttribute("teacher");
    }

    public static void setClassNumSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjects = subjectDao.filter(teacher.getSchool());
            request.setAttribute("subjects", subjects);
        }
        if (teacher != null) {
            School school = teacher.getSchool();
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.filter(school, true); // 通学中の学生をフィルタ
            List<String> classNums = students.stream().map(Student::getClassNum).distinct().collect(Collectors.toList());
            request.setAttribute("class_num_set", classNums);
        }
    }

    public static void setEntYearSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            School school = teacher.getSchool();
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.filter(school, true); // 通学中の学生をフィルタ
            List<Integer> entYears = students.stream().map(Student::getEntYear).distinct().collect(Collectors.toList());
            request.setAttribute("ent_year_set", entYears);
        }
    }

    public static void setSubjects(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        if (teacher != null) {
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjects = subjectDao.filter(teacher.getSchool());
            request.setAttribute("subjects", subjects);
        }
    }

    public static void setNumSet(HttpServletRequest request) {
        request.setAttribute("nums", Arrays.asList(1, 2)); // 回数は1, 2を設定
    }
}
