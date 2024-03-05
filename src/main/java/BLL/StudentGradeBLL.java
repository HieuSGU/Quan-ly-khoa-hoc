package BLL;

import DAO.StudentGradeDAO;
import DTO.StudentGradeDTO;
import java.util.ArrayList;

public class StudentGradeBLL implements DataManager<StudentGradeDTO> {

    private StudentGradeDAO studentGradeDAO;

    public StudentGradeBLL() {
        studentGradeDAO = new StudentGradeDAO();
    }

    @Override
    public void add(StudentGradeDTO object) {
        studentGradeDAO.insert(object);
    }

    @Override
    public void delete(StudentGradeDTO object) {
        studentGradeDAO.delete(object);
    }

    @Override
    public void edit(StudentGradeDTO object) {
        studentGradeDAO.update(object);
    }

    @Override
    public void addFromFile(String filePath) {
        // Implement this method to read data from a file and add it to the database
        // For example: studentGrades = FileReader.readFile(filePath);
    }

    @Override
    public StudentGradeDTO find(String objectId) {
        return studentGradeDAO.getOne(objectId);
    }

    @Override
    public void show(StudentGradeDTO object) {
        // Display information about the StudentGradeDTO object
        // This can be printing to the console or displaying in a UI
        // For example: System.out.println(object.toString());
    }
    
    // Add a method to retrieve all student grades
    public ArrayList<StudentGradeDTO> getAll() {
        return studentGradeDAO.getAll();
    }
    public StudentGradeDTO getStudentGradeByCondition(String condition) {
        // Call the getOne() method from the DAO and return the result
        return studentGradeDAO.getOne(condition);
    }
    // Add a method to find student grades based on a specific condition

}
