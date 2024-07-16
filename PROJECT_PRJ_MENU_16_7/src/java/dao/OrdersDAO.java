/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Orders;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DButil;

/**
 *
 * @author Cong Tuong
 */
public class OrdersDAO implements DAOInterface<Orders> {

    ArrayList<Orders> data = new ArrayList<>();
    
    public ArrayList<Orders> getAllOrders (int status){
        ArrayList<Orders> list=new ArrayList<>();
        Connection cn=null;
        try {
            //b1tao ket noi
            cn=DButil.makeConnection();
            if(cn!=null){
                //b2:viet query va exec query
                String query = "SELECT * FROM Orders WHERE status = ?";
                PreparedStatement pst = cn.prepareStatement(query);
                pst.setInt(1, status);
                ResultSet rs = pst.executeQuery();
                
                // Bước 3: Duyệt qua kết quả và tạo đối tượng Orders
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    Date orderDate = rs.getDate("orderDate");
                    String orderStatus = rs.getString("status");
                    int total = rs.getInt("total");
                    int userId = rs.getInt("userId");

                    // Tạo đối tượng Orders và thêm vào danh sách
                    Orders order = new Orders(orderId, orderDate, orderStatus, total, userId);
                    list.add(order);
                }
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn!=null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }

    @Override
    public ArrayList<Orders> selectAll() {
        return this.data;
    }

    @Override
    public Orders selectById(Orders t) {
        for (Orders orders : data) {
            if (data.equals(t)) {
                return orders;
            }
        }
        return null;
    }

    @Override
    public int insert(Orders t) {
        if (this.selectById(t) == null) {
            this.data.add(t);
            return 1;
        }
        return 0;
    }

    @Override
    public int insertAll(ArrayList<Orders> arr) {
        for (Orders orders : arr) {
            this.insert(orders);
        }
        return 0;
    }

    @Override
    public int delete(Orders t) {
        if (selectById(t) != null) {
            this.data.remove(t);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<Orders> arr) {
        for (Orders orders : arr) {
            this.delete(orders);
        }
        return 0;
    }

    @Override
    public int update(Orders t) {
        if (selectById(t) != null) {
            this.data.remove(t);
            this.data.add(t);
            return 1;
        }
        return 0;
    }

}
