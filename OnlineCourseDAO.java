/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;
import ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class OnlineCourseDAO {
    // Method to add online course data to the database
    public void addOnlineCourse(OnlineCourseDTO onlineCourse) throws SQLException {
        // Database connection
        Connection connection = null;
        try {
            connection = ConnectDB.connect();
            
            // SQL query to insert data into the database
            String sql = "INSERT INTO online_courses (course_id, title, credits, department_name, url) VALUES (?, ?, ?, ?, ?)";

            try (
                // Create a PreparedStatement to execute the SQL query
                PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                // Set values for parameters in the SQL query using OnlineCourseDTO's toObject() method
                Object[] objectArray = onlineCourse.toObject();
                statement.setInt(1, (int) objectArray[0]); // Assuming course_id is an integer
                statement.setString(2, (String) objectArray[1]); // Assuming title is a string
                statement.setInt(3, (int) objectArray[2]); // Assuming credits is an integer
                statement.setString(4, (String) objectArray[3]); // Assuming department_name is a string
                statement.setString(5, (String) objectArray[4]); // Assuming url is a string

                // Execute the SQL query to insert data into the database
                int rowsAffected = statement.executeUpdate();

                // Check if the data insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Online course data inserted successfully.");
                } else {
                    System.out.println("Failed to insert online course data.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            ConnectDB.closeConnection(connection);
        }
    }
    public void deleteOnlineCourse(int courseId) throws SQLException {
        // Database connection
        Connection connection = null;
        try {
            connection = ConnectDB.connect();
            
            // SQL query to delete data from the database
            String sql = "DELETE FROM online_courses WHERE course_id = ?";

            try (
                // Create a PreparedStatement to execute the SQL query
                PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                // Set the value for the parameter in the SQL query
                statement.setInt(1, courseId);

                // Execute the SQL query to delete data from the database
                int rowsAffected = statement.executeUpdate();

                // Check if the data deletion was successful
                if (rowsAffected > 0) {
                    System.out.println("Online course data deleted successfully.");
                } else {
                    System.out.println("Failed to delete online course data. Course ID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            ConnectDB.closeConnection(connection);
        }
    }
    public void updateOnlineCourse(OnlineCourseDTO onlineCourse) throws SQLException {
        // Database connection
        Connection connection = null;
        try {
            connection = ConnectDB.connect();
            
            // SQL query to update data in the database
            String sql = "UPDATE online_courses SET title = ?, credits = ?, department_name = ?, url = ? WHERE course_id = ?";

            try (
                // Create a PreparedStatement to execute the SQL query
                PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                // Set values for parameters in the SQL query using OnlineCourseDTO's toObject() method
                Object[] objectArray = onlineCourse.toObject();
                statement.setString(1, (String) objectArray[1]); // Assuming title is a string
                statement.setInt(2, (int) objectArray[2]); // Assuming credits is an integer
                statement.setString(3, (String) objectArray[3]); // Assuming department_name is a string
                statement.setString(4, (String) objectArray[4]); // Assuming url is a string
                statement.setInt(5, (int) objectArray[0]); // Assuming course_id is an integer

                // Execute the SQL query to update data in the database
                int rowsAffected = statement.executeUpdate();

                // Check if the data update was successful
                if (rowsAffected > 0) {
                    System.out.println("Online course data updated successfully.");
                } else {
                    System.out.println("Failed to update online course data. Course ID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            ConnectDB.closeConnection(connection);
        }
    }
}

/*public class OnlineCourseDAO implements DataManagerDAO<OnlineCourseDTO>{kiê

    public OnlineCourseDAO() {
    }

    @Override
    public OnlineCourseDTO read(Object primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OnlineCourseDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(OnlineCourseDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OnlineCourseDTO> find(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OnlineCourseDTO getOne(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}*/

