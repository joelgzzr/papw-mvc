
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<String[]> allHistory = ic.getUserCheckouts(loggedIn.getId());
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Historial</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Historial</strong>
            </li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Historial</h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
                                    <th>Nombre del Articulo</th>
                                    <th>Precio</th>
                                    <th>Fecha</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    Iterator<String[]> itr = allHistory.iterator();
                                    while (itr.hasNext()){
                                        String[] history = itr.next();
                                %>
                                    <tr>
                                        <td><%=history[0] %></td>
                                        <td><%=history[1] %></td>
                                        <td><%=history[2] %></td>
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
