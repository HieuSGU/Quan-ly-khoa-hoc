/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import com.google.protobuf.Timestamp;

import ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class OnsiteCourseDAO implements DataManagerDAO<OnsiteCourseDTO>{
    private static OnsiteCourseDAO instance;

    public static OnsiteCourseDAO getInstance() {
        if (instance == null) {
          instance = new OnsiteCourseDAO();
        }
        return instance;
      }

    public OnsiteCourseDAO() {
    }

    private static OnsiteCourseDTO createOnsiteCourseFromResultSet(ResultSet rs) throws SQLException {
        try {
            int id = rs.getInt("CourseID");
            String title = rs.getString("Title");
            int credits = rs.getInt("Credits");
            int departmentID = rs.getInt("DepartmentID");
            String location = rs.getString("Location");
            String days = rs.getString("Days");
            Time time = rs.getTime("Time");
            return new OnsiteCourseDTO(id, title, credits, departmentID, location, days, time);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<OnsiteCourseDTO> readDB() {
        ArrayList<OnsiteCourseDTO> onsiteCourseList = new ArrayList<>();
        try (
                ResultSet rs = ConnectDB.executeQuery("SELECT * FROM OnsiteCourse, Course Where OnsiteCourse.CourseID = Course.CourseID")) {
            while (rs.next()) {
                OnsiteCourseDTO onsiteCourseDTO = createOnsiteCourseFromResultSet(rs);
                onsiteCourseList.add(onsiteCourseDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return onsiteCourseList;
    }


    @Override
    public OnsiteCourseDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OnsiteCourseDTO> getAll() {
        ArrayList<OnsiteCourseDTO> onsiteCourseList = new ArrayList<>();
    	Connection con = null;
        try {
        		con = ConnectDB.connect();
        		String qry = "SELECT * FROM course c, onsitecourse osc, department dpm "
        				+ "WHERE osc.CourseID = c.CourseID AND c.DepartmentID = dpm.DepartmentID";
                ResultSet rs = con.prepareStatement(qry).executeQuery();
            while (rs.next()) {
            	DepartmentDTO depart = new DepartmentDTO(rs.getInt("DepartmentID"), rs.getString("Name"), rs.getDouble("budget"), null, null);
            	CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), depart);
            	OnsiteCourseDTO onsite = new OnsiteCourseDTO(course, rs.getString("Location"), rs.getString("Days"), rs.getTime("Time").toLocalTime());
                onsiteCourseList.add(onsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return onsiteCourseList;
    }

    @Override
    public void update(OnsiteCourseDTO onsiteCourseDTO) {
        String updateSql = "UPDATE OnsiteCourse SET Location = ?, Days = ?, Time = ? WHERE CourseID = ?";
        Object[] args = {
            onsiteCourseDTO.getLocation(),
            onsiteCourseDTO.getDays(),
            onsiteCourseDTO.getTime()
        };
        try {
            ConnectDB.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void insert(OnsiteCourseDTO onsiteCourse) {
        String insertSql = "INSERT INTO OnsiteCourse (CourseID, Location, Days, Time) VALUES (?, ?, ?, ?)";
        Object[] args = {
                onsiteCourse.getCourseId(),
                onsiteCourse.getLocation(),
                onsiteCourse.getDays(),
                onsiteCourse.getTime()
        };
        try {
            ConnectDB.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    

    @Override
    public void delete(OnsiteCourseDTO  onsitecourseDTO) {
    }

    public int deleteOnsite(int onsitecourseDTO) {
        String updateStatusSql = "DELETE FROM OnsiteCourse WHERE CourseID = ?";
        Object[] args = {onsitecourseDTO}; // Assuming CourseDTO has a method to get the CourseID
        try {
            return ConnectDB.executeUpdate(updateStatusSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateOnSiteCourse(OnsiteCourseDTO onsiteCourseDTO) {
        String updateSql = "UPDATE Course SET Title = ?, Credits = ?, DepartmentID = ? WHERE CourseID = ?";
        Object[] args = { onsiteCourseDTO.getCourseId(), onsiteCourseDTO.getLocation(), onsiteCourseDTO.getDays(), onsiteCourseDTO.getTime() };

        try {
            return ConnectDB.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public ArrayList<OnsiteCourseDTO> getCourseByID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OnsiteCourseDTO> courses = new ArrayList<>();

        try {
            conn = ConnectDB.connect();
            String query = "SELECT * FROM onsitecourse " +
                           "INNER JOIN course ON onsitecourse.CourseID = course.CourseID " +
                           "WHERE onsitecourse.CourseID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                CourseDTO courseInfo = new CourseDTO();
                courseInfo.setTitle(rs.getString("Title"));
                courseInfo.setCourseId(rs.getInt("CourseId"));

                OnsiteCourseDTO course = new OnsiteCourseDTO();
                course.setCourse(courseInfo);
                course.setLocation(rs.getString("Location"));
                course.setDays(rs.getString("Days"));
                course.setTime(rs.getTime("Time"));

                courses.add(course);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return courses;
    }

    @Override
    public OnsiteCourseDTO getOne(String condition) {
       Connection con = null;
    	try {
    		con = ConnectDB.connect();
    		String qry = "SELECT * FROM course c, onsitecourse osc, department dpm "
    				+ "WHERE osc.CourseID = c.CourseID AND c.DepartmentID = dpm.DepartmentID AND osc.CourseID = "+Integer.parseInt(condition) ;
    		ResultSet rs = con.prepareStatement(qry).executeQuery();
    		while(rs.next()) {
    			DepartmentDTO depart = new DepartmentDTO(rs.getInt("DepartmentID"), rs.getString("Name"), rs.getDouble("Budget"), null, null);
    			CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"),depart);
    			OnsiteCourseDTO onsitecourse = new OnsiteCourseDTO(course, rs.getString("Location"), rs.getString("Days"), rs.getTime("Time").toLocalTime());
    			return onsitecourse;
    		}
    	}
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getInstructorID(String courseID){
    	Connection con = null;
    	try {
    		con = ConnectDB.connect();
    		String qry = "SELECT PersonID FROM courseinstructor WHERE CourseID = "+Integer.parseInt(courseID);
    		ResultSet rs = con.prepareStatement(qry).executeQuery();
    		if(rs.next()) {
    			return rs.getInt("PersonID");
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    @Override
    public ArrayList<OnsiteCourseDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
