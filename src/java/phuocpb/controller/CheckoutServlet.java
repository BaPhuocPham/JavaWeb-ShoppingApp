/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuocpb.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuocpb.cart.CartObj;
import phuocpb.tbl_Order.Tbl_Order_DAO;
import phuocpb.tbl_Order.Tbl_Order_DTO;
import phuocpb.tbl_OrderDetail.Tbl_OrderDetail_DAO;
import phuocpb.tbl_OrderDetail.Tbl_OrderDetail_DTO;
import phuocpb.tbl_Product.Tbl_Product_DTO;
import phuocpb.util.MyApplicationConstants;
import phuocpb.tbl_Order.Tbl_OrderCreateError;


/**
 *
 * @author BaPhuoc
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        boolean foundError = false;
        HttpSession session = request.getSession();
        Tbl_OrderCreateError errors = new Tbl_OrderCreateError();
        String urlRewriting = siteMaps.getProperty(MyApplicationConstants.PayingServlet.ERROR_PAGE);
        try {
            if (name.trim().length() < 2 || name.trim().length() > 50) {
                foundError = true;
                errors.setNameLengthError("Name is required input from 2 to 50 characters");
            }
            if (address.trim().length() < 6 || address.trim().length() > 250) {
                foundError = true;
                errors.setAddressLengthError("Username is required input from 6 to 250 characters");
            }
            //2.
            if (foundError) {
                request.setAttribute("CREATE_ERRORS", errors);
                
            } else {
                urlRewriting = siteMaps.getProperty(MyApplicationConstants.PayingServlet.VIEW_ORDER_PAGE);
                float total = (float) session.getAttribute("CART_TOTAL");
                Tbl_Order_DAO dao = new Tbl_Order_DAO();
                long time = System.currentTimeMillis();
                Date date = new Date(time);
                String orderId = null;
                Tbl_Order_DTO dto = new Tbl_Order_DTO(name, address, date, total);
                orderId = dto.getId();
                System.out.println(orderId);
                dao.createOrder(dto);
                //3 Store cart to OrderDetail DB
                //3.1. Goes to cart place
                //3.2. Takes cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                Map<Integer, Tbl_Product_DTO> items = cart.getItems();
                if (cart != null) {
                    //3.3. Store each item in cart to OrderDetail DB
                    Tbl_OrderDetail_DAO orderDetailDAO = new Tbl_OrderDetail_DAO();
                    for (Tbl_Product_DTO item : items.values()) {
                        Tbl_OrderDetail_DTO orderDetailDTO = new Tbl_OrderDetail_DTO(orderId, item.getSku(), item.getQuantity(), item.getPrice(), total);
                        orderDetailDAO.insertOrderDetail(orderDetailDTO);
                    }
                }//end cart has existed
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
