/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAO.OnlineCourseDAO;
import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class OnlineCourseBLL implements DataManager<DTO.OnlineCourseDTO> {

    private OnlineCourseDAO courseDAO;
    private OnlineCourseDTO onlinecourse;
    private ArrayList<OnlineCourseDTO> List;
    
    public OnlineCourseBLL() {
        this.courseDAO = new OnlineCourseDAO();
        onlinecourse = new OnlineCourseDTO();
    }

    @Override
    public void add(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<OnlineCourseDTO> getCourseByID(int id) throws SQLException {
        return courseDAO.getCourseByID(id);
    }

    public ResultSet getAllCoursesResultSet() {
        return courseDAO.getAllCoursesResultSet();
    }   
    public Object[] getSingleCourseDetail(int courseID) {
        return courseDAO.getSingleCourseDetail(courseID);
    }

    @Override
    public OnlineCourseDTO find(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void show(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
