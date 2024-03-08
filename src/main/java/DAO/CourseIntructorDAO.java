/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.*;
import java.sql.Connection;
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
        String query = "SELECT * FROM CourseinStructor";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
