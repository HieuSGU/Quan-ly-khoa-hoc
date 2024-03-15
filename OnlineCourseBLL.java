/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import java.util.ArrayList;
import DAO.OnlineCourseDAO;
import java.util.Vector;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class OnlineCourseBLL implements DataManager<DTO.OnlineCourseDTO> {

    private OnlineCourseDTO onlinecourse;
    private ArrayList<OnlineCourseDTO> List;
    private OnlineCourseDAO onlineCourseDAO;
    
    public OnlineCourseBLL() {
        this.onlineCourseDAO = new OnlineCourseDAO();
        onlinecourse = new OnlineCourseDTO();
        List = new ArrayList<>();
    }
    
     public void addOnlineCourse(OnlineCourseDTO onlineCourse) {
        try {
            onlineCourseDAO.addOnlineCourse(onlineCourse);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // Method to delete an online course by courseId
    public void deleteOnlineCourse(int courseId) {
        try {
            onlineCourseDAO.deleteOnlineCourse(courseId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // Method to update an online course
    public void updateOnlineCourse(OnlineCourseDTO onlineCourse) {
        try {
            onlineCourseDAO.updateOnlineCourse(onlineCourse);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
 /*   @Override
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
*/
    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OnlineCourseDTO find(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void show(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    
}
