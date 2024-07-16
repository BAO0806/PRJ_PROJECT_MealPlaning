/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author Cong Tuong
 */
public class Customers extends User {

    private String address;

    public Customers(int userId, String userName, String password, String fullName, String email, String phone, String status, Date createAt) {
        super(userId, userName, password, fullName, email, phone, status, createAt);
    }

    public Customers(String address, int userId, String userName, String password, String fullName, String email, String phone, String status, Date createAt) {
        super(userId, userName, password, fullName, email, phone, status, createAt);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
