/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ConnectDB.ConnectDB;
import DAO.CourseDAO;
import DAO.OnsiteCourseDAO;

/**
 *
 * @author HP
 */
public class OnsiteCourseBLL implements DataManager<OnsiteCourseDTO> {

    private final List<OnsiteCourseDTO> listOnsiteCourse = new ArrayList<>();
    private static OnsiteCourseBLL instance;

    public OnsiteCourseBLL() {
        this.listOnsiteCourse.addAll(OnsiteCourseDAO.getInstance().readDB());
    }

    public static OnsiteCourseBLL getInstance() {
        if (instance == null) {
            instance = new OnsiteCourseBLL();
        }
        return instance;
    }

    public void refresh() {
        listOnsiteCourse.clear();
        listOnsiteCourse.addAll(OnsiteCourseDAO.getInstance().readDB());
    }
    public synchronized List<OnsiteCourseDTO> getAllModels() {
        return Collections.unmodifiableList(listOnsiteCourse);
    }
    
    public OnsiteCourseDTO getModelById(int id) {
        refresh();
        return listOnsiteCourse.stream()
                .filter(course -> course.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(OnsiteCourseDTO object) {
     if (object.getLocation() == null || object.getLocation().isEmpty()
                || object.getDays() == null
                || object.getTime() == null) {
            throw new IllegalArgumentException("error information, try again!!!");
        }
    
       OnsiteCourseDAO.getInstance().insert(object);
       listOnsiteCourse.add(object);
}


    @Override
    public void delete(OnsiteCourseDTO onsiteCourseDTO) {
        String updateStatusSql = "DELETE FROM OnsiteCourse WHERE CourseID = ?";
        Object[] args = {onsiteCourseDTO.getCourseId()};
        try {
            ConnectDB.executeUpdate(updateStatusSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as logging it
        }
    }
    

    @Override
    public void edit(OnsiteCourseDTO onsiteCourse) {
        String updateSql = "UPDATE OnsiteCourse SET Location = ?, Days = ?, Time = ? WHERE CourseID = ?";
        Object[] args = {
            onsiteCourse.getLocation(),
            onsiteCourse.getDays(),
            onsiteCourse.getTime(),
            onsiteCourse.getCourseId()
        };
        try {
            ConnectDB.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as logging it
        }
    }

    public int deleteCourse(int id) {
        OnsiteCourseDTO onsiteCourseDTO = getModelById(id);
        int result = OnsiteCourseDAO.getInstance().deleteOnsite(id);
        if (result > 0) {
            listOnsiteCourse.remove(onsiteCourseDTO);
        } else {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        return result;
    }

    public int updateOnsiteCourse(OnsiteCourseDTO onsiteCourseDTO) {
        int result = OnsiteCourseDAO.getInstance().updateOnSiteCourse(onsiteCourseDTO);
        if (result > 0) {
            int index = listOnsiteCourse.indexOf(onsiteCourseDTO);
            if (index != -1) {
                listOnsiteCourse.set(index, onsiteCourseDTO);
            }
        }
        return result;
    }
    

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<OnsiteCourseDTO> search(int condition) throws SQLException{
    
        return OnsiteCourseDAO.getInstance().getCourseByID(condition);
    }
    
    @Override
    public OnsiteCourseDTO find(String objectId) {
        try {
			return OnsiteCourseDAO.getOne(objectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    public int getInstructorID(String objectId) {
    	try {
			return OnsiteCourseDAO.getInstructorID(objectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
    }
    @Override
    public void show(OnsiteCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
