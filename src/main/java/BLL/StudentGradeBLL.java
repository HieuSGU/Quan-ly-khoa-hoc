/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAO.StudentGradeDAO;
import DTO.*;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class StudentGradeBLL implements DataManager<StudentGradeDTO>{

    private StudentGradeDTO student;
    private StudentGradeDAO studentGradeDAO;
    private ArrayList<StudentGradeDTO> List;
    
    public StudentGradeBLL() {
        student = new StudentGradeDTO();
        List = new ArrayList<>();
        this.studentGradeDAO = new StudentGradeDAO();
    }
    
    public ArrayList<StudentGradeDTO> getAll(){
        ArrayList<StudentGradeDTO> list = studentGradeDAO.getAll();
        return list;
    }
    
    @Override
    public void add(StudentGradeDTO object) {
        studentGradeDAO.insert(object);
    }

    @Override
    public void delete(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentGradeDTO find(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void show(StudentGradeDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
