/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
        for (OnsiteCourseDTO onsiteCourseDTO : listOnsiteCourse) {
            if (onsiteCourseDTO.getCourseId() == id) {
                return onsiteCourseDTO;
            }
        }
        return null;
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
        Object[] args = { onsiteCourseDTO.getCourseId() };
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

    public List<OnsiteCourseDTO> searchModel(String value, String[] columns) {
        List<OnsiteCourseDTO> items = OnsiteCourseDAO.getInstance().search(value,columns);
        List<OnsiteCourseDTO> results = new ArrayList<>();
        for (OnsiteCourseDTO item : items) {
            if (checkFilter(item, value, columns)) {
                results.add(item);
            }
        }
        return results;
    }
    

    private boolean checkFilter(OnsiteCourseDTO onsiteCourseDTO, String value, String[] columns) {
        for (String column : columns) {
            switch (column) {
                case "CourseID":
                    if (String.valueOf(onsiteCourseDTO.getCourseId()).contains(value)) {
                        return true;
                    }
                    break;
                case "Location":
                    if (onsiteCourseDTO.getLocation().toLowerCase().contains(value.toLowerCase())) {
                        return true;
                    }
                    break;
                case "Days":
                    if (onsiteCourseDTO.getDays().toLowerCase().contains(value.toLowerCase())) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }



    private boolean checkAllColumns(OnsiteCourseDTO onsiteCourseDTO, String value) {
        return (onsiteCourseDTO.getCourseId() == Integer.parseInt(value) ||
                onsiteCourseDTO.getTitle().toLowerCase().contains(value) ||
                onsiteCourseDTO.getCredits() == Integer.parseInt(value) ||
                onsiteCourseDTO.getDepartmentId() == Integer.parseInt(value) ||
                onsiteCourseDTO.getLocation().toLowerCase().contains(value) ||
                onsiteCourseDTO.getDays().toLowerCase().contains(value));
    }

    private OnsiteCourseDTO mapToEntity(OnsiteCourseDTO from) {
        OnsiteCourseDTO to = new OnsiteCourseDTO();
        updateEntityFields(from, to);
        return to;
    }

    private void updateEntityFields(OnsiteCourseDTO from, OnsiteCourseDTO to) {
        to.setCourseId(from.getCourseId());
        to.setTitle(from.getTitle());
        to.setCredits(from.getCredits());
        to.setDepartmentId(from.getDepartmentId());
        to.setLocation(from.getLocation());
        to.setDays(from.getDays());
        to.setTime(from.getTime());
    }

    @Override
    public void addFromFile(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OnsiteCourseDTO find(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void show(OnsiteCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
