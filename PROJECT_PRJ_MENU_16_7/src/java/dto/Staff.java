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
public class Staff extends User {
    private String position;

    public Staff(int userId, String userName, String password, String fullName, String email, String phone, String status, Date createAt) {
        super(userId, userName, password, fullName, email, phone, status, createAt);
    }

    public Staff(String position, int userId, String userName, String password, String fullName, String email, String phone, String status, Date createAt) {
        super(userId, userName, password, fullName, email, phone, status, createAt);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
}
