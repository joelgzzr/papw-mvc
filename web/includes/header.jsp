<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession webSession = request.getSession();
    User loggedIn = null;

    if(webSession.getAttribute("user") == null){
        response.sendRedirect("index.jsp");
        return;
    } else {
         loggedIn = (User)webSession.getAttribute("user");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>TuTienda</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <img alt="image" class="rounded-circle" src="data:image/jpeg;base64,<%= loggedIn.getAvatarString() %>" height="100" width="100"/>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="block m-t-xs font-bold"><%= loggedIn.getFullname() %></span>
                            <span class="text-muted text-xs block">Gestion de articulos <b class="caret"></b></span>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="dropdown-item" href="agregar.jsp">Agregar</a></li>
                                <li><a class="dropdown-item" href="editar.jsp">Modificar</a></li>
                                <li><a class="dropdown-item" href="eliminar.jsp">Eliminar</a></li>
                                <li><a class="dropdown-item" href="borradores.jsp">Publicar Borrador</a></li>
                            </ul>
                        </a>
                    </div>
                    <div class="logo-element">
                        TuT
                    </div>
                </li>
                <li>
                    <a href="home.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Home</span></a>
                </li>
                <li>
                    <a href="home.jsp#vendidos"><i class="fa fa-th-large"></i> <span class="nav-label">Mas Vendidos</span></a>
                </li>
                <li>
                    <a href="home.jsp#vistos"><i class="fa fa-th-large"></i> <span class="nav-label">Mas Vistos</span></a>
                </li>
                <li>
                    <a href="home.jsp#calificados"><i class="fa fa-th-large"></i> <span class="nav-label">Mejor Calificados</span></a>
                </li>
                <li>
                    <a href="carrito.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Carrito</span></a>
                </li>
                <li>
                    <a href="historial.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Historial de Compras</span></a>
                </li>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" action="search.jsp">
                        <div class="form-group">
                            <input type="text" placeholder="Busca algo..." class="form-control" name="search" id="top-search">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">Bienvenido a TuTienda</span>
                    </li>
                    <li>
                        <a href="index.jsp?logout=true">
                            <i class="fa fa-sign-out"></i> Salir
                        </a>
                    </li>
                </ul>
            </nav>
        </div>