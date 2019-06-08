<%@page import="models.Item"%>
<%@page import="clases.ItemController"%>
<%@page import="java.util.List"%>
<%@page import="models.Category"%>
<%@page import="clases.CategoryController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    CategoryController cc = new CategoryController();
    List<Category> categoryList = cc.getAllCategories();
    
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
                    <form method="POST" action="modify-item">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Articulo a Editar</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" onchange="getItemData()" name="item" id="editItem" required>
                                    <option selected="true" disabled="disabled">Escoge un Articulo</option>
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
                            <label class="col-sm-2 col-form-label">Nombre</label>
                            <div class="col-sm-10"><input type="text" name="name" id="editName" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Descripcion</label>
                            <div class="col-sm-10"><input type="text" id="editDesc" name="description" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Categoria</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" name="category" id="editCategory" required>
                                    <%
                                        for(Category category : categoryList) {
                                            out.print("<option value="+ category.getId() +">"+ category.getName() +"</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Precio</label>
                            <div class="col-sm-10">
                                <div class="input-group m-b">
                                    <div class="input-group-prepend">
                                        <span class="input-group-addon">$</span>
                                    </div>
                                    <input type="number" name="price" id="editPrice" class="form-control" required>
                                    <div class="input-group-append">
                                        <span class="input-group-addon">.00</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Stock</label>
                            <div class="col-sm-10"><input type="number" name="stock" id="editStock" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary btn-sm" name="button" type="submit">Editar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
