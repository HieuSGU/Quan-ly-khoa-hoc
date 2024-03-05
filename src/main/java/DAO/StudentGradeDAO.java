package DAO;

import DTO.StudentGradeDTO;
import ConnectDB.ConnectDB;
import DTO.CourseDTO;
import DTO.StudentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentGradeDAO implements DataManagerDAO<StudentGradeDTO> {

    @Override
    public StudentGradeDTO read(Object primaryKey) {
        int enrollmentId = (int) primaryKey;
        StudentGradeDTO studentGrade = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.connect();
            String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate " +
                           "FROM StudentGrade sg " +
                           "JOIN Course c ON sg.CourseID = c.CourseID " +
                           "JOIN Person s ON sg.StudentID = s.PersonID " +
                           "WHERE sg.EnrollmentID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, enrollmentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), null);
                StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getDate("EnrollmentDate").toLocalDate());
                studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student, rs.getFloat("Grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentGrade;
    }

    @Override
public ArrayList<StudentGradeDTO> getAll() {
    ArrayList<StudentGradeDTO> studentGrades = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = ConnectDB.connect();
        String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate " +
                       "FROM StudentGrade sg " +
                       "JOIN Course c ON sg.CourseID = c.CourseID " +
                       "JOIN Person s ON sg.StudentID = s.PersonID";
        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), null);
            StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"), rs.getString("FirstName"), null);
            
            // Kiểm tra xem trường EnrollmentDate có giá trị null hay không trước khi gọi phương thức toLocalDate()
            if (rs.getDate("EnrollmentDate") != null) {
                student.setEnrollmentDate(rs.getDate("EnrollmentDate").toLocalDate());
            }
            
            StudentGradeDTO studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student, rs.getFloat("Grade"));
            studentGrades.add(studentGrade);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return studentGrades;
}

 @Override
    public void update(StudentGradeDTO object) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.connect();
            String query = "UPDATE StudentGrade SET Grade = ? WHERE EnrollmentID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setFloat(1, object.getGrade());
            pstmt.setInt(2, object.getEnrollmentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(StudentGradeDTO object) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.connect();
            String query = "INSERT INTO StudentGrade (CourseID, StudentID, Grade) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, object.getCourse().getCourseId());
            pstmt.setInt(2, object.getStudent().getPersonId());
            pstmt.setFloat(3, object.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(StudentGradeDTO object) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.connect();
            String query = "DELETE FROM StudentGrade WHERE EnrollmentID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, object.getEnrollmentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

@Override
public ArrayList<StudentGradeDTO> find(String condition) {
    ArrayList<StudentGradeDTO> studentGrades = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = ConnectDB.connect();
        String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate " +
                       "FROM StudentGrade sg " +
                       "JOIN Course c ON sg.CourseID = c.CourseID " +
                       "JOIN Person s ON sg.StudentID = s.PersonID " +
                       "WHERE " + condition;
        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), null);
            StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"), rs.getString("FirstName"), null);
            if (rs.getDate("EnrollmentDate") != null) {
                student.setEnrollmentDate(rs.getDate("EnrollmentDate").toLocalDate());
            }
            StudentGradeDTO studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student, rs.getFloat("Grade"));
            studentGrades.add(studentGrade);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return studentGrades;
}

@Override
public StudentGradeDTO getOne(String condition) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StudentGradeDTO studentGrade = null;

    try {
        conn = ConnectDB.connect();
        String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate " +
                       "FROM StudentGrade sg " +
                       "JOIN Course c ON sg.CourseID = c.CourseID " +
                       "JOIN Person s ON sg.StudentID = s.PersonID " +
                       "WHERE " + condition + " LIMIT 1";
        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), null);
            StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"), rs.getString("FirstName"), null);
            if (rs.getDate("EnrollmentDate") != null) {
                student.setEnrollmentDate(rs.getDate("EnrollmentDate").toLocalDate());
            }
            studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student, rs.getFloat("Grade"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return studentGrade;
}


}
