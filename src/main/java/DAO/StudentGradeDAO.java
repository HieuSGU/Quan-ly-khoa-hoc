/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.*;


import ConnectDB.ConnectDB;
import DTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import ConnectDB.ConnectDB;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class StudentGradeDAO implements DataManagerDAO<StudentGradeDTO> {

    private Connection c;
    public StudentGradeDAO() {
        //khỏi tạo đồng thời kết nối với csdl
        try {
            this.c = (Connection) ConnectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override

    public StudentGradeDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    public ArrayList<StudentGradeDTO> getAll() {
        String query = "select EnrollmentID, studentgrade.CourseID, course.Title CourseTitle, StudentID, person.Firstname StudentFirstName, person.Lastname"
                + " StudentLastName, Grade from studentgrade left join course on studentgrade.CourseID = course.CourseID left join person on person.PersonID = studentgrade.StudentID";
        ArrayList<StudentGradeDTO> list = new ArrayList<>();
        try {
            Statement s = (this.c).createStatement();
            ResultSet rs = s.executeQuery(query);
            if(rs != null){
                while(rs.next()){                    
                    int enrollmentID = rs.getInt("EnrollmentID");
                    int courseID = rs.getInt("CourseID");
                    int studentID = rs.getInt("StudentID");
                    float grade = rs.getFloat("Grade");
                    
                    CourseDTO course = new CourseDTO();
                    course.setCourseId(courseID);
                    course.setTitle(rs.getString("CourseTitle"));
                    
                    StudentDTO student = new StudentDTO();
                    student.setPersonId(studentID);
                    student.setFirstName(rs.getString("StudentFirstName"));
                    student.setLastName(rs.getString("StudentLastName"));
                    
                    StudentGradeDTO studentGrade = new StudentGradeDTO(enrollmentID, course,student,grade);
                    list.add(studentGrade);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;

    }
    
    @Override

    public ArrayList<StudentGradeDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    public StudentGradeDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
    
    @Override
    public void update(StudentGradeDTO object) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

         try {
            String query = "Update studentgrade set Grade = ? where EnrollmentID = ?";
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, object.getGrade()+"");
            s.setString(2, object.getEnrollmentId()+"");
            
            // Thực hiện truy vấn update
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void insert(StudentGradeDTO object) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<StudentGradeDTO> find(String condition) {
        ArrayList<StudentGradeDTO> studentGrades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.connect();
            String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate "
                    +
                    "FROM StudentGrade sg " +
                    "JOIN Course c ON sg.CourseID = c.CourseID " +
                    "JOIN Person s ON sg.StudentID = s.PersonID " +
                    "WHERE " + condition;
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"),
                        null);
                StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"),
                        rs.getString("FirstName"), null);
                if (rs.getDate("EnrollmentDate") != null) {
                    student.setEnrollmentDate(rs.getDate("EnrollmentDate").toLocalDate());
                }
                StudentGradeDTO studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student,
                        rs.getFloat("Grade"));
                studentGrades.add(studentGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    ConnectDB.closeConnection(conn);
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
            String query = "SELECT sg.EnrollmentID, sg.Grade, c.CourseID, c.Title, c.Credits, s.PersonID, s.LastName, s.FirstName, s.EnrollmentDate "
                    +
                    "FROM StudentGrade sg " +
                    "JOIN Course c ON sg.CourseID = c.CourseID " +
                    "JOIN Person s ON sg.StudentID = s.PersonID " +
                    "WHERE " + condition + " LIMIT 1";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"),
                        null);
                StudentDTO student = new StudentDTO(rs.getInt("PersonID"), rs.getString("LastName"),
                        rs.getString("FirstName"), null);
                if (rs.getDate("EnrollmentDate") != null) {
                    student.setEnrollmentDate(rs.getDate("EnrollmentDate").toLocalDate());
                }
                studentGrade = new StudentGradeDTO(rs.getInt("EnrollmentID"), course, student, rs.getFloat("Grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentGrade;
    }


        try {
            String query = "UPDATE studentgrade set Grade = ? where StudentID = ? and CourseID = ?";
            PreparedStatement s = c.prepareStatement(query);
            s.setFloat(1, object.getGrade());
            s.setInt(2, object.getStudent().getPersonId());
            s.setInt(3, object.getCourse().getCourseId());
            
            // Thực hiện truy vấn insert
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public StudentGradeDTO getOne(String condition) {
        StudentGradeDTO studentGradeDTO = null;
        try {
            String query = "select EnrollmentID, studentgrade.CourseID, course.Title CourseTitle, StudentID, person.Firstname StudentFirstName, person.Lastname"
                + " StudentLastName, Grade from studentgrade left join course on studentgrade.CourseID = course.CourseID left join person on person.PersonID = studentgrade.StudentID where EnrollmentID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, condition);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                int enrollmentID = rs.getInt("EnrollmentID");
                    int courseID = rs.getInt("CourseID");
                    int studentID = rs.getInt("StudentID");
                    float grade = rs.getFloat("Grade");
                    
                    CourseDTO course = new CourseDTO();
                    course.setCourseId(courseID);
                    course.setTitle(rs.getString("CourseTitle"));
                    
                    StudentDTO student = new StudentDTO();
                    student.setPersonId(studentID);
                    student.setFirstName(rs.getString("StudentFirstName"));
                    student.setLastName(rs.getString("StudentLastName"));
                    
                     studentGradeDTO = new StudentGradeDTO(enrollmentID, course,student,grade);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  studentGradeDTO;
    }

    @Override
    public void delete(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<StudentGradeDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
