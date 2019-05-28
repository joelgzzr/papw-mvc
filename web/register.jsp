<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Registrarse</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">TuT</h1>
            </div>
            <h3>Registrate</h3>
            <p>Crea tu cuenta</p>
            <form class="m-t" role="form" method="POST" enctype="multipart/form-data" action="register">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="Nombre de Usuario" minlength="7" required>
                </div>
                <div class="form-group">
                    <input type="text" name="fullname" class="form-control" placeholder="Nombre Completo" required>
                </div>
                <div class="form-group">
                    <input type="email" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Contraseña" minlength="8" required>
                </div>
                <div class="form-group">
                    <input type="text" name="phone" class="form-control" placeholder="Telefono (No Obligatorio)">
                </div>
                <div class="form-group">
                    <input type="text" name="address" class="form-control" placeholder="Direccion (No Obligatorio)">
                </div>
                <div class="form-group">
                    Imagen de Pefil: <input type="file" name="avatar" class="form-control">
                </div>
                <div class="form-group">
                    Portada: <input type="file" name="cover" class="form-control">
                </div>

                <input type="submit" name="button" class="btn btn-primary block full-width m-b" value="Registrarse">

                <p class="text-muted text-center"><small>Ya tienes una cuenta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="index.jsp">Ingresar</a>
            </form>
        </div>
    </div>
    
    <%
        HttpSession webSession = request.getSession();
        String username;
        String email;

        if(request.getAttribute("username") != null){
            username = (String)request.getAttribute("username");
            webSession.setAttribute("username", username);
            
            if(request.getAttribute("email") != null){
                email = (String)request.getAttribute("email");
                webSession.setAttribute("email", email);
                response.sendRedirect("home.jsp");
            }
        }
    %>

    <!-- Mainly scripts -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>

</html>

