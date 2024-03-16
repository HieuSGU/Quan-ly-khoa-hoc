/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import java.util.ArrayList;
import DAO.*;

/**
 *
 * @author HP
 */
public class CourseInstructorBLL implements DataManager<CourseInstructorDTO> {

    private CourseInstructorDTO courseinstructor;
    private ArrayList<CourseInstructorDTO> List;
    private CourseIntructorDAO courseinstructorDAO;

    public CourseInstructorBLL() {
        courseinstructor = new CourseInstructorDTO();
        List = new ArrayList<>();
        this.courseinstructorDAO = new CourseIntructorDAO();

    }
    
    public ArrayList<CourseInstructorDTO> getAll(){
        ArrayList<CourseInstructorDTO> list = courseinstructorDAO.getAll();
        return list;

    }


    @Override
    public void add(CourseInstructorDTO object) {
        courseinstructorDAO.insert(object);
    }

    @Override
    public void delete(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CourseInstructorDTO find(String objectId) {
        CourseIntructorDAO courseInstructorDAO = new CourseIntructorDAO();
        return courseInstructorDAO.getOne(objectId);
    }

    @Override
    public void show(CourseInstructorDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    public CourseInstructorDTO getOneCourseInstructorRow(String courseId){
//        courseinstrructorDAO.getOne(courseID);
        return courseinstructorDAO.getOne(courseId);
    }
    
    public CourseInstructorDTO getOne(String courseId){
        return courseinstructorDAO.getOne(courseId);
    }
    

}
