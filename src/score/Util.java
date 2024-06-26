package score;

import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import bean.Teacher;

public class Util {

    public static Teacher getUser(HttpServletRequest request) {
        return (Teacher) request.getSession().getAttribute("user");
    }

    public static void setClassNumSet(HttpServletRequest request, List<String> classNums) {
        request.setAttribute("class_num_set", classNums);
    }

    public static void setEntryYearSet(HttpServletRequest request, List<Integer> entryYears) {
        request.setAttribute("ent_year_set", entryYears);
    }

    public static void setSubjects(HttpServletRequest request, List<Subject> subjects) {
        request.setAttribute("subjects", subjects);
    }

    public static void setNumSet(HttpServletRequest request, List<Integer> numSet) {
        request.setAttribute("num_set", numSet);
    }
}
// aaaaaaあa