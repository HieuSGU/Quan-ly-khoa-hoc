    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;
import java.util.ArrayList;
import ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
public class OnlineCourseDAO implements DataManagerDAO<OnlineCourseDTO>{

    public OnlineCourseDAO() {
    }

    @Override
    public OnlineCourseDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ResultSet getAllCoursesResultSet() {
        try {
            Connection conn = ConnectDB.connect();
            String query = "SELECT onlinecourse.CourseID, onlinecourse.url, course.Title " +
                           "FROM onlinecourse " +
                           "INNER JOIN course ON onlinecourse.CourseID = course.CourseID";
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OnlineCourseDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object[] getSingleCourseDetail(int courseID) {
        try {
            Connection conn = ConnectDB.connect();
            String query = "SELECT onlinecourse.CourseID, onlinecourse.url, course.Title, course.Credits, course.DepartmentID, courseinstructor.PersonID " +
                           "FROM onlinecourse " +
                           "INNER JOIN course ON onlinecourse.CourseID = course.CourseID " +  
                           "INNER JOIN courseinstructor ON courseinstructor.CourseID = course.CourseID " +
                           "WHERE onlinecourse.CourseID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            Object[] rowData = null;
            if (rs.next()) {
                rowData = new Object[]{rs.getInt("CourseID"), rs.getString("Title"), rs.getString("url"),rs.getInt("DepartmentID"), rs.getInt("Credits"), rs.getInt("PersonID")};}
            rs.close();
            pstmt.close();
            conn.close();
            return rowData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<OnlineCourseDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OnlineCourseDTO getOne(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<OnlineCourseDTO> getCourseByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OnlineCourseDTO> courses = new ArrayList<>();
    
        try {
            conn = ConnectDB.connect();
            String query = "SELECT * FROM onlinecourse "+
                     "INNER JOIN course ON onlinecourse.CourseID = course.CourseID " + 
                    " WHERE onlinecourse.CourseID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
    
            while (rs.next()) {
                CourseDTO courseInfo = new CourseDTO();
            courseInfo.setTitle(rs.getString("Title"));
            courseInfo.setCourseId(rs.getInt("CourseId"));

            OnlineCourseDTO course = new OnlineCourseDTO();
            course.setCourse(courseInfo);
            course.setUrl(rs.getString("url"));

            courses.add(course);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    
        return courses;
    }
    
    }