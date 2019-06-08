<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Item"%>
<%@page import="java.util.List"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<String> imageList = ic.getItemImages(Integer.parseInt(request.getParameter("itemid")));
    Item currentItem = ic.getItemDataFull(Integer.parseInt(request.getParameter("itemid")));
    String itemRating = ic.getItemRating(Integer.parseInt(request.getParameter("itemid")));
    List<String[]> itemComments = ic.getItemComments(Integer.parseInt(request.getParameter("itemid")));
    boolean boughtItem = ic.getBoughtItems(loggedIn.getId(), Integer.parseInt(request.getParameter("itemid")));
    ic.addItemView(Integer.parseInt(request.getParameter("itemid")));
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Articulo</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Articulo</strong>
            </li>
        </ol>
    </div>
</div>

<div class="row m-b-lg m-t-lg">
    <div class="col-md-6">
        <%
            for(String image : imageList) {
        %>
            <div class="profile-image">
                <img src="data:image/jpg;base64,<%= image %>" class="rounded-circle circle-border m-b-md" alt="img">
            </div>
        <% } %>
        <div class="profile-info">
            <div class="">
                <div>
                    <h2 class="no-margins">
                        <%=currentItem.getName() %>
                    </h2>
                    <h5> Descripcion: <%=currentItem.getDescription() %> </h5>
                    <h5> Stock: <%=currentItem.getStock() %> En stock</h5>
                    <h5> Precio: <%=currentItem.getPrice() %>$</h5>
                    <h5> Rating: <%=itemRating %> Estrellas</h5>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 text-right">
        <button onclick="addCart(<%=loggedIn.getId() %>, <%=currentItem.getId() %>)" class="btn btn-primary btn-lg">Agregar al Carrito</button>
        <%
            if(boughtItem == true) {
        %>
            <button onclick="rate(<%=currentItem.getId() %>, <%=loggedIn.getId() %>)" class="btn btn-primary btn-lg">Valorar</button>
        <% } %>
    </div>
</div>

<div class="row">
    <div class="col-md-2 col-centered">
        <div class="ibox">
            <div class="ibox-title">
                <h5>Video</h5>
            </div>
            <div class="ibox-content">
                <video controls>
                    <source src="data:video/mp4;base64,<%=currentItem.getVideoString() %>" type="video/mp4">
                </video>
            </div>
        </div>
    </div>
    <div class="col-md-10">
        <div class="ibox chat-view">
            <div class="ibox-title">
                Comments
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-md-12">
                        <div class="chat-discussion">
                            <%
                                Iterator<String[]> itr = itemComments.iterator();
                                while (itr.hasNext()){
                                    String[] comment = itr.next();
                            %>
                                <div class="chat-message left">
                                    <img class="message-avatar" src="data:image/jpg;base64,<%=comment[2] %>" alt="userimg" >
                                    <div class="message">
                                        <strong class="message-author"> <%=comment[0] %></strong>
                                        <span class="message-content">
                                            <%=comment[1] %>
                                        </span>
                                    </div>
                                </div>
                            <% } %>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="chat-message-form">
                            <div class="form-group">
                                <textarea 
                                    class="form-control message-input" 
                                    name="message"
                                    id="comment"
                                    placeholder="Type your comment"
                                    onkeyup="if(event.keyCode==13) insertComment(<%=currentItem.getId() %>, <%=loggedIn.getId() %>);"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>

