/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author BaPhuoc
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws SQLException, NamingException {

        //1 get current context
        Context context = new InitialContext();
        //2 look up server context
        Context tomcat = (Context) context.lookup("java:comp/env");
        //3. look up DS
        DataSource ds = (DataSource) tomcat.lookup("PRJDEFAULTDB");
        //4 open connection
        Connection con = ds.getConnection();

        return con;
    }
}
