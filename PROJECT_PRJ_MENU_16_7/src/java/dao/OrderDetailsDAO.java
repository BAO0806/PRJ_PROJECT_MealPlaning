/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DButil;

/**
 *
 * @author Cong Tuong
 */
public class OrderDetailsDAO implements DAOInterface<OrderDetails>{
    
    public ArrayList<OrderDetails> getOrderDetails(int orderId){
        ArrayList<OrderDetails> list=new ArrayList<>();
        Connection cn=null;
        try {
            //b1tao ket noi
            cn=DButil.makeConnection();
            if(cn!=null){
                //b2:viet query va exec query
                String query = "SELECT od.detailId, od.dishId, d.name AS dishname, od.quantity, od.orderId, d.imagePath AS imageUrl, d.estimatedPrice AS price " +
                           "FROM OrderDetails od " +
                           "JOIN Dishes d ON od.dishId = d.id " +
                           "WHERE od.orderId = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            // Bước 3: Đọc kết quả từ ResultSet
            while (rs.next()) {
                int detailId = rs.getInt("detailId");
                int dishId = rs.getInt("dishId");
                String dishname = rs.getString("dishname");
                int quantity = rs.getInt("quantity");
                String imageUrl = rs.getString("imageUrl");
                int price = rs.getInt("price");

                OrderDetails orderDetails = new OrderDetails(detailId, dishId, dishname, quantity, orderId, imageUrl, price);
                list.add(orderDetails);
            }
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
    public ArrayList<OrderDetails> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderDetails selectById(OrderDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(OrderDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertAll(ArrayList<OrderDetails> arr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(OrderDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteAll(ArrayList<OrderDetails> arr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(OrderDetails t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
