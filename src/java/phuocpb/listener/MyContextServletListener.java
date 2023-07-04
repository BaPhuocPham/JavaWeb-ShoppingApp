/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package phuocpb.listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import phuocpb.util.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author BaPhuoc
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteMapLocation = context.getInitParameter(
                "SITEMAP_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty = PropertiesFileHelper.getProperties(context,
                siteMapLocation);
        context.setAttribute("SITE_MAPS", siteMapProperty);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
