/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.tbl_Order;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Random;
import javax.naming.NamingException;

/**
 *
 * @author BaPhuoc
 */
public class Tbl_Order_DTO {
    private String id;
    private String name;
    private String address;
    private Date date;
    private float total;

    public Tbl_Order_DTO() {
    }

    public Tbl_Order_DTO(String name, String address, Date date, float total) 
            throws SQLException, NamingException {
        this.id = createId();
        this.name = name;
        this.address = address;
        this.date = date;
        this.total = total;
    }

    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
    public String createId() 
            throws SQLException, NamingException {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string is 5.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        if (checkExistId(saltStr)) {
            saltStr = createId();
        }
        System.out.println(saltStr);
        return saltStr;
    }
    
    public boolean checkExistId(String id) 
            throws SQLException, NamingException {
        boolean result;
        Tbl_Order_DAO dao = new Tbl_Order_DAO();
        result = dao.checkExistId(id);
        return result;
    }
}
