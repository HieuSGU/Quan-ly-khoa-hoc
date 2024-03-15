/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectDB;

/**
 *
 * @author HP
 */
public class StudentGradeDAO implements DataManagerDAO<StudentGradeDTO> {

    public StudentGradeDAO() {
    }

    @Override
    public StudentGradeDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<StudentGradeDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
