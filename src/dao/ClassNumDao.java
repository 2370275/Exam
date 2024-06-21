package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends DAO{
	public List<String> filter(String school) throws Exception{
		List<String> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from class_num where class_num=?");
		st.setString(1, school);
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			String class_num=rs.getString("class_num");
			list.add(class_num);
		}

		st.close();
		con.close();

		return list;
	}

<<<<<<< HEAD
	public List
=======
	public List;
>>>>>>> branch 'master' of https://github.com/2370275/Exam.git
}
