<%-- 
    Document   : shopping
    Created on : Mar 14, 2023, 10:52:53 PM
    Author     : AliensVN
--%>

<%@page import="phuocpb.tbl_Product.Tbl_Product_DTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            List<Tbl_Product_DTO> products = (List<Tbl_Product_DTO>)
                        request.getAttribute("PRODUCT");
            if (products != null){
        %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Tbl_Product_DTO dto : products){
                    %>
                    <form action="cartAddItem">
                        <tr>
                            <td>
                                <%= ++count %>
                                <input type="hidden" name="txtSku" 
                                       value="<%= dto.getSku()%>" /> 
                            </td>
                            <td>
                                <%= dto.getName() %>
                                <input type="hidden" name="txtName" 
                                       value="<%= dto.getName()%>" /> 
                            </td>
                            <td>
                                <%= dto.getDescription() %>
                                <input type="hidden" name="txtDescription" 
                                       value="<%= dto.getDescription()%>" /> 
                            </td>
                            <td>
                                <%= dto.getPrice() %>
                                <input type="hidden" name="txtPrice" 
                                       value="<%= dto.getPrice()%>" /> 
                            </td>
                            <td>
                                <%= dto.getQuantity() %>
                                <input type="hidden" name="txtQuantity" 
                                       value="<%= dto.getQuantity()%>" /> 
                            </td>
                            <td>
                                <input type="submit" value="Add Book to Your Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                    <%
                        }//end traverse dto
                    %>
                    <form name="cartPage" action="cartPage">
                        <input type="submit" value="View Your Cart" name="btAction" />
                    </form>

                </tbody>
            </table>
        <%
            } else {
        %>
              <h2>
                  No record is matched!!!
              </h2>   
        <%  
            }//end no result found
        %>            
    </body>
</html>