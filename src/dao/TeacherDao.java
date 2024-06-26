package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends DAO {
    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();
        PreparedStatement st;
        st = con.prepareStatement("select * from teacher where id=?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setPassword(rs.getString("password"));

            // school_cd を取得して School オブジェクトに設定
            School school = new School();
            school.setCd(rs.getString("school_cd"));
            // 必要に応じて他の属性も設定します
            teacher.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();
        return teacher;
    }

    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();
        PreparedStatement st;
        st = con.prepareStatement("select * from teacher where id=? and password=?");
        st.setString(1, id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setPassword(rs.getString("password"));

            // school_cd を取得して School オブジェクトに設定
            School school = new School();
            school.setCd(rs.getString("school_cd"));
            // 必要に応じて他の属性も設定します
            teacher.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return teacher;
    }
}
