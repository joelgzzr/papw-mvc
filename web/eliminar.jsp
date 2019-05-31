<%@page import="java.util.List"%>
<%@page import="models.Item"%>
<%@page import="clases.ItemController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    ItemController ic = new ItemController();
    List<Item> itemList = ic.getAllItems(loggedIn.getId());
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Editar Articulo</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Editar Articulo</strong>
            </li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Editar un Articulo</h5>
                </div>
                <div class="ibox-content">
                    <form method="POST" action="delete-item">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Articulo a Borrar</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" name="item" required>
                                    <%
                                        for(Item item : itemList) {
                                            out.print("<option value="+ item.getId() +">"+ item.getName() +"</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary btn-sm" name="button" type="submit">Borrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
