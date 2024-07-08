package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {
    private String baseSql = "SELECT * FROM TEST WHERE SCHOOL_CD = ?";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM TEST WHERE NO = ?");
            statement.setInt(1, no);
            rSet = statement.executeQuery();

            if (rSet.next()) {
                test = new Test();
                test.setNo(rSet.getInt("no"));
                test.setClassNum(rSet.getString("class_num"));
                test.setPoint(rSet.getInt("point"));
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return test;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setNo(rSet.getInt("no"));
                test.setClassNum(rSet.getString("class_num"));
                test.setPoint(rSet.getInt("point"));
                test.setSchool(school);
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }


    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String condition = " AND end_year = ? AND class_num = ? AND subject_cd = ? AND num = ?";
        String order = " ORDER BY no ASC";

        try {
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subject.getCd());
            statement.setInt(5, num);
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        PreparedStatement insertStatement = null;
        PreparedStatement updateStatement = null;
        int count = 0;

        try {
            // Insert SQL文とUpdate SQL文
            String insertSql = "INSERT INTO TEST (no, class_num, point, student_cd, subject_cd, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
            String updateSql = "UPDATE TEST SET class_num = ?, point = ?, student_cd = ?, subject_cd = ?, school_cd = ? WHERE no = ?";

            insertStatement = connection.prepareStatement(insertSql);
            updateStatement = connection.prepareStatement(updateSql);

            for (Test test : list) {
                // 既存のテストデータを取得
                Test oldTest = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

                if (oldTest == null) {
                    // データが存在しない場合は新規挿入
                    insertStatement.setInt(1, test.getNo());
                    insertStatement.setString(2, test.getClassNum());
                    insertStatement.setInt(3, test.getPoint());
                    insertStatement.setString(4, test.getStudent().getNo());
                    insertStatement.setString(5, test.getSubject().getCd());
                    insertStatement.setString(6, test.getSchool().getCd());
                    count += insertStatement.executeUpdate();
                } else {
                    // データが存在する場合は更新
                    updateStatement.setString(1, test.getClassNum());
                    updateStatement.setInt(2, test.getPoint());
                    updateStatement.setString(3, test.getStudent().getNo());
                    updateStatement.setString(4, test.getSubject().getCd());
                    updateStatement.setString(5, test.getSchool().getCd());
                    updateStatement.setInt(6, test.getNo());
                    count += updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (updateStatement != null) {
                try {
                    updateStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return count == list.size();
    }

    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement insertStatement = null;
        PreparedStatement updateStatement = null;
        boolean isSaved = false;

        try {
            // Insert SQL文とUpdate SQL文
            String insertSql = "INSERT INTO TEST (no, class_num, point, student_cd, subject_cd, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
            String updateSql = "UPDATE TEST SET class_num = ?, point = ?, student_cd = ?, subject_cd = ?, school_cd = ? WHERE no = ?";

            // 既存のテストデータを取得
            Test oldTest = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

            if (oldTest == null) {
                // データが存在しない場合は新規挿入
                insertStatement = connection.prepareStatement(insertSql);
                insertStatement.setInt(1, test.getNo());
                insertStatement.setString(2, test.getClassNum());
                insertStatement.setInt(3, test.getPoint());
                insertStatement.setString(4, test.getStudent().getNo());
                insertStatement.setString(5, test.getSubject().getCd());
                insertStatement.setString(6, test.getSchool().getCd());
                isSaved = insertStatement.executeUpdate() > 0;
            } else {
                // データが存在する場合は更新
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, test.getClassNum());
                updateStatement.setInt(2, test.getPoint());
                updateStatement.setString(3, test.getStudent().getNo());
                updateStatement.setString(4, test.getSubject().getCd());
                updateStatement.setString(5, test.getSchool().getCd());
                updateStatement.setInt(6, test.getNo());
                isSaved = updateStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (updateStatement != null) {
                try {
                    updateStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return isSaved;
    }

    public boolean delete(List<Test> list) throws Exception {
        Connection connection = getConnection();
        PreparedStatement deleteStatement = null;
        int count = 0;

        try {
            // Delete SQL文
            String deleteSql = "DELETE FROM TEST WHERE no = ? AND student_cd = ? AND subject_cd = ? AND school_cd = ?";
            deleteStatement = connection.prepareStatement(deleteSql);

            for (Test test : list) {
                deleteStatement.setInt(1, test.getNo());
                deleteStatement.setString(2, test.getStudent().getNo());
                deleteStatement.setString(3, test.getSubject().getCd());
                deleteStatement.setString(4, test.getSchool().getCd());
                count += deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStatement != null) {
                try {
                    deleteStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return count == list.size();
    }

    private boolean delete(Test test, Connection connection) throws Exception {
        PreparedStatement deleteStatement = null;
        boolean isDeleted = false;

        try {
            // Delete SQL文
            String deleteSql = "DELETE FROM TEST WHERE no = ? AND student_cd = ? AND subject_cd = ? AND school_cd = ?";
            deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, test.getNo());
            deleteStatement.setString(2, test.getStudent().getNo());
            deleteStatement.setString(3, test.getSubject().getCd());
            deleteStatement.setString(4, test.getSchool().getCd());

            isDeleted = deleteStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStatement != null) {
                try {
                    deleteStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return isDeleted;
    }
}