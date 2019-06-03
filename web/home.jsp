<%@page import="java.util.List"%>
<%@page import="models.Item"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<Item> itemList = ic.getMostSoldItems();
    List<Item> itemListViews = ic.getMostViewedItems();
    List<Item> itemListRating = ic.getBestRatedItems();
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Los Mejores</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Los Mejores</strong>
            </li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox" id="vendidos">
                <div class="ibox-title">
                    <h5>Los Mas Vendidos</h5>
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
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox" id="vistos">
                <div class="ibox-title">
                    <h5>Los Mas Vistos</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <%
                            for(Item itemViews : itemListViews) {
                        %>
                            <div class="col-md-3">
                                <a href="item.jsp?itemid=<%=itemViews.getId() %>">
                                    <div class="ibox">
                                        <div class="ibox-content product-box">
                                            <img class="" src="data:image/png;base64, <%=itemViews.getImgString1() %>" height="200px" width="100%">
                                            <div class="product-desc">
                                                <a href="item.jsp?itemid=<%=itemViews.getId() %>" class="product-name"><%=itemViews.getName() %></a>
                                                <div class="small m-t-xs">
                                                    <%=itemViews.getDescription() %>
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
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox" id="calificados">
                <div class="ibox-title">
                    <h5>Los Mejor Calificados</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <%
                            for(Item itemRating : itemListViews) {
                        %>
                            <div class="col-md-3">
                                <a href="item.jsp?itemid=<%=itemRating.getId() %>">
                                    <div class="ibox">
                                        <div class="ibox-content product-box">
                                            <img class="" src="data:image/png;base64, <%=itemRating.getImgString1() %>" height="200px" width="100%">
                                            <div class="product-desc">
                                                <a href="item.jsp?itemid=<%=itemRating.getId() %>" class="product-name"><%=itemRating.getName() %></a>
                                                <div class="small m-t-xs">
                                                    <%=itemRating.getDescription() %>
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
