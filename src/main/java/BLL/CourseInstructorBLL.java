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
        courseinstructorDAO.update(object);
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
    
    public boolean check (CourseInstructorDTO object){
        ArrayList<CourseInstructorDTO> coureInstructor = courseinstructorDAO.getListCourseInstructorByCourseId(object.getCourse());
        ArrayList<CourseInstructorDTO> coureInstructorALl = courseinstructorDAO.getAll();
        System.out.println(coureInstructor);
        System.out.println(coureInstructorALl);
        for (CourseInstructorDTO instructor : coureInstructor) {
            System.out.println("BLL.CourseInstructorBLL.check()1");
            if(instructor.getInstructor() == object.getInstructor()){
                
                return false;
            }
        }
        
        return true;
    }
}
