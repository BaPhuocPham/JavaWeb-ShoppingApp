///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package phuocpb.controller;
//
//import javax.servlet.RequestDispatcher;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author TienPhong
// * 
// */
//@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
//public class DispatchServlet extends HttpServlet {
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String TRIGGER_APP_CONTROLLER = "TriggerAppServlet";
//    private final String ADD_ITEM_TO_CARTT_CONTROLLER = "AddItemToCartServlet";
//    private final String REMOVE_ITEM_TO_CART_CONTROLLER = "RemoveItemToCartServlet"; 
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
//    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
//    
//    
//    private final String LOGIN_PAGE = "login.html";
//    private final String SIGUP_PAGE = "createNewAccount.html";
//    private final String SHOPPING_PAGE = "ShoppingServlet";
//    private final String CHECKOUT_PAGE = "checkout.jsp";
//    private final String PRINTORDER_PAGE = "viewOrder.jsp";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        String url = LOGIN_PAGE;
//        //Which button did user clicked?
//        String button = request.getParameter("btAction");
//        
//        try  {
//            if(button == null) {
//                //transfer to Login page
//                //check cookie
//                url = TRIGGER_APP_CONTROLLER;
//            } else if (button.equals("login") || button.equals("Home")) {
//                url = LOGIN_CONTROLLER;
//                //url = siteMaps.getProperties(LOGIN_CONTROLLER);
//            } else if (button.equals("Sign Up")) {
//                url = SIGUP_PAGE;
//            } else if (button.equals("search")) {
//                url = SEARCH_LASTNAME_CONTROLLER;
//            } else if (button.equals("Logout")) {
//                url = LOGOUT_CONTROLLER;
//            } else if (button.equals("update")) {
//                url = UPDATE_ACCOUNT_CONTROLLER;
//            } else if (button.equals("delete")) {
//                url = DELETE_ACCOUNT_CONTROLLER;
//            } else if (button.equals("Buy Book") || button.equals("Continue to buy Book")) {
//                url = SHOPPING_PAGE;
//            } else if (button.equals("Add Book to Your Cart")) {
//                url = ADD_ITEM_TO_CARTT_CONTROLLER;
//            } else if (button.equals("View Your Cart")) {
//                url = VIEW_CART_PAGE;
//            } else if (button.equals("Remove Item")) {
//                url = REMOVE_ITEM_TO_CART_CONTROLLER;
//            } else if (button.equals("Create New Account")) {
//                url = CREATE_NEW_ACCOUNT_CONTROLLER;
//            } else if (button.equals("Checkout")) {
//                url = CHECKOUT_PAGE;
//            } else if (button.equals("Print Order")) {
//                url = CHECKOUT_CONTROLLER;
//            } else if (button.equals("Order Page")) {
//                url = PRINTORDER_PAGE;
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
