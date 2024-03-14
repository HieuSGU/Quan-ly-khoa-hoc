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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
public class StudentGradeDAO implements DataManagerDAO<StudentGradeDTO>{

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
    public StudentGradeDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    @Override
    public void update(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(StudentGradeDTO object) {
        try {
            String query = "INSERT INTO studentgrade (StudentID, CourseID, Grade) VALUES (?, ?, ?);";
            PreparedStatement s = c.prepareStatement(query);
            s.setString(1, object.getCourse().getCourseId()+"");
            s.setString(2, object.getStudent().getPersonId()+"");
            s.setString(3, object.getGrade()+"");
            
            // Thực hiện truy vấn insert
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseIntructorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public void delete(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<StudentGradeDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentGradeDTO getOne(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
