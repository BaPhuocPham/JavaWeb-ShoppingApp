/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author BaPhuoc
 */
public class PropertiesFileHelper {
     public static Properties getProperties(ServletContext context, String fileRelativePath){
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            Logger.getLogger(PropertiesFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }

}