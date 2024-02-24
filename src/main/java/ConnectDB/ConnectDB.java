/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ConnectDB {
    public static Connection connect() throws  SQLException{
        Connection con = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/school";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            System.out.print("khong the ket noi");
            throw e;
        }
        return con;
    }
    public static void closeConnection(Connection conn) throws  SQLException{
        
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.print("khong the ket noi");
            throw e;
        }
    }
    
    public static void insertData(String tableName, String[] columns, String[] values) throws SQLException {
        Connection conn = null;
        try {
            conn = connect();
            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
            for (String column : columns) {
                query.append(column).append(", ");
            }
            query.delete(query.length() - 2, query.length()); // Xóa dấu phẩy cuối cùng
            query.append(") VALUES (");
            for (String value : values) {
                query.append("?, ");
            }
            query.delete(query.length() - 2, query.length()); // Xóa dấu phẩy cuối cùng
            query.append(")");

            try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                for (int i = 0; i < values.length; i++) {
                    pstmt.setString(i + 1, values[i]);
                }
                pstmt.executeUpdate();
            }
        } finally {
            closeConnection(conn);
        }
    }

    public static void updateData(String tableName, String[] columns, String[] values, String conditionColumn, String conditionValue) throws SQLException {
        Connection conn = null;
        try {
            conn = connect();
            StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
            for (int i = 0; i < columns.length; i++) {
                query.append(columns[i]).append(" = ?, ");
            }
            query.delete(query.length() - 2, query.length()); // Xóa dấu phẩy cuối cùng
            query.append(" WHERE ").append(conditionColumn).append(" = ?");

            try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                for (int i = 0; i < values.length; i++) {
                    pstmt.setString(i + 1, values[i]);
                }
                pstmt.setString(values.length + 1, conditionValue);
                pstmt.executeUpdate();
            }
        } finally {
            closeConnection(conn);
        }
    }

    public static void deleteData(String tableName, String conditionColumn, String conditionValue) throws SQLException {
        Connection conn = null;
        try {
            conn = connect();
            String query = "DELETE FROM " + tableName + " WHERE " + conditionColumn + " = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, conditionValue);
                pstmt.executeUpdate();
            }
        } finally {
            closeConnection(conn);
        }
    }

    public static ResultSet selectData(String tableName, String[] columns, String conditionColumn, String conditionValue) throws SQLException {
        Connection conn = null;
        try {
            conn = connect();
            StringBuilder query = new StringBuilder("SELECT ");
            for (String column : columns) {
                query.append(column).append(", ");
            }
            query.delete(query.length() - 2, query.length()); // Xóa dấu phẩy cuối cùng
            query.append(" FROM ").append(tableName);
            if (conditionColumn != null && conditionValue != null) {
                query.append(" WHERE ").append(conditionColumn).append(" = ?");
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                if (conditionColumn != null && conditionValue != null) {
                    pstmt.setString(1, conditionValue);
                }
                return pstmt.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
