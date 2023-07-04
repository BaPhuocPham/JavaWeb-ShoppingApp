/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuocpb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuocpb.tbl_Registration.Tbl_RegistrationCreateError;
import phuocpb.tbl_Registration.Tbl_Registration_DAO;
import phuocpb.tbl_Registration.Tbl_Registration_DTO;
import phuocpb.util.MyApplicationConstants;

/**
 *
 * @author BaPhuoc
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.CreateNewAccountServlet.ERROR_PAGE);
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean foundError = false;

        Tbl_RegistrationCreateError errors = new Tbl_RegistrationCreateError();
        try {
            //1. check validattion of all user error
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username is required input from 6 to 20 characters");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Username is required input from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match passwords");
            }

            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundError = true;
                errors.setFullnameLengthError("Fullname is required input from 6 to 30 characters");
            }

            //2.
            if (foundError) {
                request.setAttribute("CREATE_ERRORS", errors);

            } else {
                Tbl_Registration_DAO dao = new Tbl_Registration_DAO();
                Tbl_Registration_DTO dto = new Tbl_Registration_DTO(username, password, fullname, false);
                boolean result = dao.createNewAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(MyApplicationConstants.CreateNewAccountServlet.LOGIN_PAGE);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("Create New _ SQL" + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("Create New _ Naming" + ex.getMessage());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
