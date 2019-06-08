<%@page import="java.util.List"%>
<%@page import="models.Item"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<Item> itemList = ic.getItemsBySearch(request.getParameter("search"));
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Busqueda</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Busqueda</strong>
            </li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox" id="vendidos">
                <div class="ibox-title">
                    <h5>Busqueda</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <%
                            for(Item item : itemList) {
                        %>
                            <div class="col-md-3">
                                <a href="item.jsp?itemid=<%=item.getId() %>">
                                    <div class="ibox">
                                        <div class="ibox-content product-box">
                                            <img class="" src="data:image/png;base64, <%=item.getImgString1() %>" height="200px" width="100%">
                                            <div class="product-desc">
                                                <a href="item.jsp?itemid=<%=item.getId() %>" class="product-name"><%=item.getName() %></a>
                                                <div class="small m-t-xs">
                                                    <%=item.getDescription() %>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
