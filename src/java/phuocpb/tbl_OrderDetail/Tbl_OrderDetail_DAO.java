/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.tbl_OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuocpb.util.DBHelper;

/**
 *
 * @author BaPhuoc
 */
public class Tbl_OrderDetail_DAO {
    public boolean insertOrderDetail (Tbl_OrderDetail_DTO dto) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
       try {
        //1.Connect DB
        con = DBHelper.makeConnection();
        if (con != null){
            //2.Write SQL command
            String sql = "INSERT INTO tbl_OrderDetail ("
                    + "order_id, sku, quantity, price, total"
                    + ") Values ("
                    + "?, ?, ?, ?, ?"
                    + ")";
            //3.Create Statement Object
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getOrder_id());
            stm.setInt(2, dto.getSku());
            stm.setInt(3, dto.getQuantity());
            stm.setFloat(4, dto.getPrice());
            stm.setFloat(5, dto.getTotal());
            //4.Execute statement to get result
            int effectRow = stm.executeUpdate();
            //5. process result
            if (effectRow > 0) {
                result = true;
            }
        }//end connection
    } finally {
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
