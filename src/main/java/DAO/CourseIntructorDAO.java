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
        //khỏi tạo đồng thời kết nối với csdl
        try {
            this.c = (Connection) ConnectDB.connect();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CourseInstructorDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CourseInstructorDTO> getAll() {
        String query = "SELECT * FROM CourseInstructor";
        ArrayList<CourseInstructorDTO> list = new ArrayList<>();
        try {
            Statement s = (this.c).createStatement();
            ResultSet rs = s.executeQuery(query);
            if(rs != null){
                while(rs.next()){
                    
                    int courseid = rs.getInt("CourseID");
                    int personid = rs.getInt("PersonID");
                    CourseInstructorDTO courseinstructor = new CourseInstructorDTO(courseid, personid);
                    list.add(courseinstructor);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public void update(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(CourseInstructorDTO object) {
        try {
            String query = "INSERT INTO courseinstructor (CourseID, PersonID) VALUES (?, ?)";
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, object.getCourse().getCourseId()+"");
            s.setString(2, object.getInstructor().getPersonId()+"");
            
            // Thực hiện truy vấn insert
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CourseInstructorDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CourseInstructorDTO getOne(String condition) {
        CourseInstructorDTO courseinstructor = null;
        try {
            String query = "SELECT * FROM CourseInstructor WHERE CourseID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, condition);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                int courseId = rs.getInt("CourseID");
                int personId = rs.getInt("PersonID");
                //tạo đối tượng course
                CourseDTO course = this.getCourse(courseId+"");
                
                //tạo đối tượng instructor
                
                InstructorDTO instructor = this.getInstructor(personId+"");
                courseinstructor = new CourseInstructorDTO(course, instructor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  courseinstructor;
    }
    
    public CourseDTO getCourse(String couseId){
        CourseDTO course = null;
        try {
            String query = "SELECT * FROM course WHERE CourseID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, couseId);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
//              tạo đối tượng course
                int courseId = rs.getInt("CourseID");
                String title = rs.getString("Title");
                int credits = rs.getInt("Credits"); 
                int departmentId = rs.getInt("DepartmentID");
                course = new CourseDTO(courseId, title, credits, departmentId);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  course;
        
    }
    
    public InstructorDTO getInstructor(String instructorId){
        InstructorDTO instructor = null;
        try {
            String query = "SELECT * FROM person WHERE PersonID = ?";
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1, instructorId);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
//              tạo đối tượng instructor
                int personId = rs.getInt("PersonID");
                String lastName = rs.getString("Lastname");
                String firstName = rs.getString("Firstname");
                
                //ép kiểu từ Date của database sang localdate
                LocalDate hireDate = null;
                Date hireDateSQL = rs.getDate("HireDate");
//                if(hireDateSQL != null){
//                    Instant instant = hireDateSQL.toInstant();
//                    hireDate = instant.atZone(ZoneId.of("UTC")).toLocalDate();
//                }else{
//                    System.out.println("No hire date available.");
//                }
                instructor = new InstructorDTO(personId, lastName, firstName, hireDate);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instructor;
    }
    
}
