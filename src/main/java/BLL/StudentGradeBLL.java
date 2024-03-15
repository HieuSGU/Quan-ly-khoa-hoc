/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;
import java.util.ArrayList;
import DAO.StudentGradeDAO;
import DTO.StudentGradeDTO;

/**
 *
 * @author HP
 */
public class StudentGradeBLL implements DataManager<StudentGradeDTO> {

    private StudentGradeDAO studentGradeDAO;

    public StudentGradeBLL() {
        studentGradeDAO = new StudentGradeDAO();
    }

    @Override
    public void add(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentGradeDTO find(String objectId) {
        return studentGradeDAO.getOne(objectId);
    }

    @Override
    public void show(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
