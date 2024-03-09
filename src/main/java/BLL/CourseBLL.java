/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DAO.*;

/**
 *
 * @author HP
 */
public class CourseBLL implements DataManager<CourseDTO> {
    private ArrayList<CourseDTO> List;
    private final List<CourseDTO> courseList = new ArrayList<>();
    private static CourseBLL instance;
    private CourseDAO courseDAO;
    
    public CourseBLL() {
        this.courseList.addAll(CourseDAO.getInstance().readDB());
        this.courseDAO = new CourseDAO();
    }

    public static CourseBLL getInstance() {
        if (instance == null) {
            instance = new CourseBLL();
        }
        return instance;
    }

    public void refresh() {
        courseList.clear();
        courseList.addAll(CourseDAO.getInstance().readDB());
    }

    public List<CourseDTO> getAllModels() {
        return Collections.unmodifiableList(courseList);
    }

    public CourseDTO getModelById(int id) {
        refresh();
        for (CourseDTO CourseModel : courseList) {
            if (CourseModel.getCourseId() == id) {
                return CourseModel;
            }
        }
        return null;
    }

    @Override
    public void add(CourseDTO object) {
        if (object.getTitle() == null || object.getTitle().isEmpty()
                || object.getCredits() <= 0
                || object.getDepartmentId() <= 0) {
            throw new IllegalArgumentException("error information, try again!!!");
        }
    
       CourseDAO.getInstance().insert(object);
       courseList.add(object);
    }
    
    public int deleteCourse(int id) {
        CourseDTO CourseDTO = getModelById(id);
        int result = CourseDAO.getInstance().deleteCourse(id);
        if (result > 0) {
            courseList.remove(CourseDTO);
        } else {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        return result;
    }


    public int updateCourse(CourseDTO courseDTO) {
        int result = CourseDAO.getInstance().updateCourse(courseDTO);
        if (result > 0) {
            int index = courseList.indexOf(courseDTO);
            if (index != -1) {
                courseList.set(index, courseDTO);
            }
        }
        return result;
    }

    @Override
    public void delete(CourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(CourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CourseDTO find(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void show(CourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isExist(String courseId){
        return courseDAO.isExist(courseId);
    }
    
}
