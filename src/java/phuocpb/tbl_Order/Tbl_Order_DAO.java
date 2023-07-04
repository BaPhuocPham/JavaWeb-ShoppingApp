/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.tbl_Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuocpb.util.DBHelper;

/**
 *
 * @author BaPhuoc
 */
public class Tbl_Order_DAO {
    public boolean createOrder (Tbl_Order_DTO dto) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "INSERT INTO tbl_Order("
                        + "id, name, address, date, total"
                        + ") Values("
                        + "?, ?, ?, ?, ?"
                        + ")";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getName());
                stm.setString(3, dto.getAddress());
                stm.setDate(4, dto.getDate());
                stm.setFloat(5, dto.getTotal());
                //4.Execute statement to get result                
                int effectRows = stm.executeUpdate();

                if (effectRows > 0) {
                    result = true;
                }

            }//end connection
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean checkExistId (String id) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
       try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null){
                //2.Write SQL command
                String sql = "Select id "
                        + "From tbl_Order "
                        + "Where id = ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()){
                    result = true;
                }
            }//end connection
        } finally {
           if (rs != null){
               rs.close();
           }
           if (stm != null){
               stm.close();
           }
           if (con !=null){
               con.close();
           }
       }
       return result;
    }
}
