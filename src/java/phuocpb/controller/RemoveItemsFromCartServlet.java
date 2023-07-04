/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuocpb.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuocpb.cart.CartObj;
import phuocpb.tbl_Product.Tbl_Product_DTO;
import phuocpb.util.MyApplicationConstants;

/**
 *
 * @author BaPhuoc
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer takes his/her cart
                CartObj cart = (CartObj)session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer gets all items
                    Map<Integer, Tbl_Product_DTO> items = cart.getItems();
                    if (items != null) {
                        //4. Remove items from cart
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            for (String sku : selectedItems) {
                                int newSku = Integer.parseInt(sku);
                                cart.removeItemFromCart(newSku, 1);
                            }//end removeing one item
                            //store total of cart
                            float total = cart.getTotal();
                            session.setAttribute("CART_TOTAL", total);
                            session.setAttribute("CART", cart);
                        }//end customer checked at least an item
                    }//end items has existed
                }//end cart has existed
            }//end session has existed
        } finally {
            // 5. refresh page by calling view cart feature again using url rewriting
            ServletContext context = this.getServletContext();
            Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
            String url = siteMaps.getProperty(MyApplicationConstants.RemoveItemsFromCartServlet.VIEW_CART_PAGE)
                    + "?btAction=View Your Cart";
            response.sendRedirect(url);
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
