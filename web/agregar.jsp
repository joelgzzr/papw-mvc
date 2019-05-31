<%@page import="java.util.List"%>
<%@page import="models.Category"%>
<%@page import="clases.CategoryController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@include file="/includes/header.jsp" %>
<%
    CategoryController cc = new CategoryController();
    List<Category> categoryList = cc.getAllCategories();
%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>Agregar Nuevo Articulo</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active">
                <strong>Nuevo Articulo</strong>
            </li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Agregar un Nuevo Articulo</h5>
                </div>
                <div class="ibox-content">
                    <form method="POST" enctype="multipart/form-data" action="items">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Nombre</label>
                            <div class="col-sm-10"><input type="text" name="name" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Descripcion</label>
                            <div class="col-sm-10"><input type="text" name="description" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Categoria</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" name="category" required>
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
                                    <input type="number" name="price" class="form-control" required>
                                    <div class="input-group-append">
                                        <span class="input-group-addon">.00</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Stock</label>
                            <div class="col-sm-10"><input type="number" name="stock" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Imagen 1</label>
                            <div class="col-sm-10"><input type="file" name="img1" class="form-control" required></div>
                            <label class="col-sm-2 col-form-label">Imagen 2</label>
                            <div class="col-sm-10"><input type="file" name="img2" class="form-control" required></div>
                            <label class="col-sm-2 col-form-label">Imagen 3</label>
                            <div class="col-sm-10"><input type="file" name="img3" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Video</label>
                            <div class="col-sm-10"><input type="file" name="video" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row"><label class="col-sm-2 col-form-label">Guardar como borrador? <br/>
                            <small class="text-navy">Si lo guardas como borrador no sera visto publicamente</small></label>

                            <div class="col-sm-10">
                                <div><label> <input type="checkbox" name="draft" value="1"> Borrador? </label></div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary btn-sm" name="button" type="submit">Agregar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
