package DAO;

import DTO.CourseDTO;
import DTO.DepartmentDTO;
import ConnectDB.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO implements DataManagerDAO<CourseDTO> {
    private static CourseDAO instance;

    public static CourseDAO getInstance() {
        if (instance == null) {
            instance = new CourseDAO();
        }
        return instance;
    }

    public CourseDAO() {
    }

    private static CourseDTO createCourseFromResultSet(ResultSet rs) throws SQLException {
        int courseId = rs.getInt("CourseID");
        String title = rs.getString("Title");
        int credits = rs.getInt("Credits");
        int department = rs.getInt("DepartmentID");
        return new CourseDTO(courseId, title, credits, department);
    }

    public ArrayList<CourseDTO> readDB() {
        ArrayList<CourseDTO> courseList = new ArrayList<>();
        try (ResultSet rs = ConnectDB.executeQuery("SELECT * FROM Course")) {
            while (rs.next()) {
                CourseDTO courseDTO = createCourseFromResultSet(rs);
                courseList.add(courseDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public CourseDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(CourseDTO courseDTO) {
        String updateSql = "UPDATE Course SET Title = ?, Credits = ?, DepartmentID = ? WHERE CourseID = ?";
        Object[] args = {
                courseDTO.getTitle(),
                courseDTO.getCredits(),
                courseDTO.getDepartmentId(),
                courseDTO.getCourseId()
        };
        try {
            ConnectDB.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateCourse(CourseDTO courseDTO) {
        String updateSql = "UPDATE Course SET Title = ?, Credits = ?, DepartmentID = ? WHERE CourseID = ?";
        Object[] args = { courseDTO.getCourseId(), courseDTO.getTitle(), courseDTO.getCredits(), courseDTO.getDepartmentId() };

        try {
            return ConnectDB.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void insert(CourseDTO courseDTO) {
        String insertSql = "INSERT INTO Course (CourseID, Title, Credits, DepartmentID) VALUES (?, ?, ?, ?)";
        Object[] args = {
                courseDTO.getCourseId(),
                courseDTO.getTitle(),
                courseDTO.getCredits(),
                courseDTO.getDepartmentId()
        };
        try {
            ConnectDB.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CourseDTO courseDTO) {
        String updateStatusSql = "DELETE FROM Course WHERE CourseID = ?";
        Object[] args = {courseDTO.getCourseId()};
        try {
            ConnectDB.executeUpdate(updateStatusSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public int deleteCourse(int courseId) {
        String updateStatusSql = "DELETE FROM OnsiteCourse WHERE CourseID = ?";
        Object[] args = {courseId}; // Assuming CourseDTO has a method to get the CourseID
        try {
            return ConnectDB.executeUpdate(updateStatusSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    } 

    @Override
    public ArrayList<CourseDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CourseDTO getOne(String condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<CourseDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
}
