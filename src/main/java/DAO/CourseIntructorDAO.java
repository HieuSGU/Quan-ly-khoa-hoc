/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class CourseIntructorDAO implements DataManagerDAO<CourseInstructorDTO> {

    private Connection c;

    public CourseIntructorDAO() {
        // khỏi tạo đồng thời kết nối với csdl
        try {
            this.c = (Connection) ConnectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CourseInstructorDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CourseInstructorDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CourseInstructorDTO> find(String condition) {
        ArrayList<CourseInstructorDTO> courseInstructor = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.connect();
            String query = "select ci.CourseID, c.Title, os.InstructorID, p.FirstName, p.LastName\n"
                    + "from CourseInstructor ci join course c\n"
                    + "ci.CourseID = c.CourseID join person p\n"
                    + "ci.PersonID = p.PersonID and p.HireDate > 0 join officeassignment os\n"
                    + "on ci.PersonID = os.InstructorID\n"
                    + "where c.Title like " + condition;
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), 0,
                        null);
                PersonDTO person = new PersonDTO(0, rs.getString("LastName"), rs.getString("FirstName"));
                int personId = rs.getInt("PersonID");
                InstructorDTO instructor = this.getInstructor(personId + "");

                courseInstructor.add(courseInstructor);
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
        return courseInstructor;
    }

    @Override
    public CourseInstructorDTO getOne(String condition) {
        CourseInstructorDTO courseinstructor = null;
        try {
            String query = "SELECT * FROM CourseInstructor WHERE CourseID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, condition);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                int courseId = rs.getInt("CourseID");
                int personId = rs.getInt("PersonID");
                // tạo đối tượng course
                CourseDTO course = this.getCourse(courseId + "");

                // tạo đối tượng instructor

                InstructorDTO instructor = this.getInstructor(personId + "");
                courseinstructor = new CourseInstructorDTO(course, instructor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseinstructor;
    }

    public CourseDTO getCourse(String couseId) {
        CourseDTO course = null;
        try {
            String query = "SELECT * FROM course WHERE CourseID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, couseId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                // tạo đối tượng course
                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
                int credits = rs.getInt("Credits");
                int departmentId = rs.getInt("DepartmentID");
                course = new CourseDTO(courseId, title, credits, departmentId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;

    }

    public InstructorDTO getInstructor(String instructorId) {
        InstructorDTO instructor = null;
        try {
            String query = "SELECT * FROM person WHERE PersonID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, instructorId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                // tạo đối tượng instructor
                int personId = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");

                // ép kiểu từ Date của database sang localdate
                LocalDate hireDate = null;
                Date hireDateSQL = rs.getDate("HireDate");
                // if(hireDateSQL != null){
                // Instant instant = hireDateSQL.toInstant();
                // hireDate = instant.atZone(ZoneId.of("UTC")).toLocalDate();
                // }else{
                // System.out.println("No hire date available.");
                // }
                instructor = new InstructorDTO(personId, lastName, firstName, hireDate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instructor;
    }

}
