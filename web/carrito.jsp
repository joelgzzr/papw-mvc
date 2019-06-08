
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<String[]> allCart = ic.getUserCart(loggedIn.getId());
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Carrito</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Carrito</strong>
            </li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Carrito</h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
                                    <th>Nombre del Articulo</th>
                                    <th>Precio</th>
                                    <th>Comprar o Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    Iterator<String[]> itr = allCart.iterator();
                                    while (itr.hasNext()){
                                        String[] cart = itr.next();
                                %>
                                    <tr>
                                        <td><%=cart[2] %></td>
                                        <td><%=cart[3] %></td>
                                        <td><button 
                                        class="btn btn-primary btn-sm" 
                                        onclick="checkout(<%=cart[0] %>, <%=cart[1] %>, <%=cart[4] %>)">Comprar</button> 
                                        <button 
                                        class="btn btn-danger btn-sm"
                                        onclick="deleteCart(<%=cart[0] %>)">Eliminar</button></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
