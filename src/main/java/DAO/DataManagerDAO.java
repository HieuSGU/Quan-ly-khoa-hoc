/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface DataManagerDAO<T> {
    // Đọc một đối tượng dựa trên khóa chính
    T read(Object primaryKey);

    // Lấy tất cả các đối tượng
    ArrayList<T> getAll();

    // Cập nhật một đối tượng
    void update(T object);

    // Chèn một đối tượng mới
    void insert(T object);

    // Xóa một đối tượng
    void delete(T object);

    // Tìm kiếm đối tượng dựa trên một điều kiện nào đó
    ArrayList<T> find(String condition);

    // Lấy một đối tượng dựa trên một điều kiện nào đó
    T getOne(String condition);
}
