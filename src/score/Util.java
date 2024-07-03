package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Teacher;

public class Util {
    public static Teacher getUser(HttpServletRequest req) {
        return (Teacher) req.getSession().getAttribute("teacher");
    }

    public static void setClassNumSet(HttpServletRequest request, List<String> classNumSet) {
        request.setAttribute("class_num_set", classNumSet);
    }

    public static void setEntYearSet(HttpServletRequest request, List<Integer> entYearSet) {
        request.setAttribute("ent_year_set", entYearSet);
    }

    public static void setSubjects(HttpServletRequest request, List<String> subjects) {
        request.setAttribute("subjects", subjects);
    }

    public static void setNumSet(HttpServletRequest request, List<Integer> numSet) {
        request.setAttribute("num_set", numSet);
    }
}
