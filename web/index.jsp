<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="loginColumns animated fadeInDown">
        <div class="row">
            <div class="col-md-6">
                <h2 class="font-bold">Bienvenido a TuTienda</h2>
            </div>
            <div class="col-md-6">
                <div class="ibox-content">
                    <form class="m-t" role="form" method="POST" action="login">
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" placeholder="Username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Password" required>
                        </div>
                        <input type="submit" name="button" class="btn btn-primary block full-width m-b" value="Ingresar">

                        <p class="text-muted text-center">
                            <small>Aun no tienes una cuenta?</small>
                        </p>
                        <a class="btn btn-sm btn-white btn-block" href="register.jsp">Crear una cuenta</a>
                    </form>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-md-6">
                Copyright TuTienda
            </div>
            <div class="col-md-6 text-right">
               <small>© 2018-2019</small>
            </div>
        </div>
    </div>
    
    <%
        HttpSession webSession = request.getSession();
        User user;
        
        if(request.getParameter("logout") != null){
            session.invalidate();
        }else if(request.getAttribute("user") != null){
            user = (User)request.getAttribute("user");
            webSession.setAttribute("user", user);
            response.sendRedirect("home.jsp");
        } else if(webSession.getAttribute("user") != null){
            response.sendRedirect("home.jsp");
        }
    %>
</body>
</html>