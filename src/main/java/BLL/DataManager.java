/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BLL;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface DataManager<T> {
    
    /**
     *
     */

    // Phương thức để thêm một đối tượng
    void add(T object);

    // Phương thức để xóa một đối tượng
    void delete(T object);

    // Phương thức để sửa đổi một đối tượng
    void edit(T object);

    // Phương thức để thêm các đối tượng từ một tệp
    void addFromFile(String filePath);

    // Phương thức để tìm kiếm một đối tượng
    T find(String objectId);

    // Phương thức để hiển thị thông tin của đối tượng
    void show(T object);
}
