/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.tbl_Registration;

import java.io.Serializable;
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
public class Tbl_Registration_DAO implements Serializable {

    public Tbl_Registration_DTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_Registration_DTO result = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "Select username, lastname, isAdmin "
                        + "From tbl_Registration "
                        + "Where username = ? And password = ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    result = new Tbl_Registration_DTO(username, null, fullName, role);
                }
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
        return result;
    }

    private List<Tbl_Registration_DTO> accountList;

    public List<Tbl_Registration_DTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "Select username, password, lastname, isAdmin "
                        + "From tbl_Registration "
                        + "Where lastname Like ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Execute statement to get result
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    Tbl_Registration_DTO dto = new Tbl_Registration_DTO(
                            username, password, fullName, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end accouint list has NOT existed
                    this.accountList.add(dto);
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

    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "DELETE From tbl_Registration "
                        + "Where username = ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute statement to get result
                int row = stm.executeUpdate();
                //5. process result
                if (row > 0) {
                    return true;
                }
            }//end connection
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "UPDATE tbl_Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username LIKE ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4.Execute statement to get result
                int row = stm.executeUpdate();
                //5. process result
                if (row > 0) {
                    result = true;
                }
            }//end connection
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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

    public boolean createNewAccount(Tbl_Registration_DTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Write SQL command
                String sql = "INSERT INTO tbl_Registration ("
                            + "username, password, lastname, isAdmin"
                        + ") Values ("
                        + "?, ?, ?, ?"
                        + ")";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4.Execute statement to get result
                int effectRow = stm.executeUpdate();
                //5. process result
                if (effectRow > 0) {
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
}
