/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.tbl_Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuocpb.util.DBHelper;

/**
 *
 * @author BaPhuoc
 */
public class Tbl_Product_DAO {

    private List<Tbl_Product_DTO> productList;

    public List<Tbl_Product_DTO> getProductList() {
        return productList;
    }

    public void getAllProduct()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "Select sku, name, description, price, quantity, status "
                        + "From tbl_Product";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");

                    Tbl_Product_DTO dto = new Tbl_Product_DTO(
                            sku, name, description, price, quantity, status);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }//end product list has NOT existed
                    this.productList.add(dto);
                }//end traverse rs to EOF
            }//end connection
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
