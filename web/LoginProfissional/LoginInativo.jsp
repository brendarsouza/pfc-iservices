<%-- 
    Document   : LoginInativo
    Created on : 25/04/2017, 17:19:07
    Author     : brenda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISERVICES - LOGIN INATIVO</title>
        <link rel="icon" type="image/png" href="../imagens/logo2.png"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/perfilCliente.css">
    <script src='./js/perfil.js'></script>

    </head>
    <body>
        <nav class="navbar">
            <nav class="w3-sidenav w3-card-4 w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:30%;min-width:20px" id="mySidenav">
                <a href="javascript:void(0)" onclick="w3_close()" class="w3-closenav">Fechar ( X )</a>
                <a href="index.jsp" onclick="w3_close" >Home</a>
                <a href="LoginProfissional/LoginProfissional.jsp">Área do Profissional</a>
                <a href="cadastro.jsp">Cadastre-se</a>
                <a href="LoginCliente/LoginCliente.jsp">Entrar</a>
            </nav>
        </nav>
        <div class="w3-top">
            <div class="w3-white w3-xlarge w3-padding-xlarge" style="max-width:1200px;margin:auto;">
                <div class="w3-opennav w3-left w3-hover-text-grey" style="margin-bottom: auto" onclick="w3_open()">☰</div>
                <div class="w3-center"><a href="index.jsp"><img src="imagens/icone_1.png" alt="Sandwich" style="width:190px"> </a>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="LoginProfissional/LoginProfissional.jsp">Área do Profissional</a></li>  
                        <li><a href="cadastro.jsp">Cadastre-se</a></li>
                        <li><a href="LoginCliente/LoginCliente.jsp">Entrar</a></li> 
                    </ul>   
                </div>
            </div>
        </div>
        <div>
            <div class="container-fluid">
                <div class="bg-1">
                    <br>
                    <center><h3>Seu login esta inativo, deseja ativá-lo? </h3>    
                        <br>
                        <a href="./LoginProfissional/AtivarLogin.jsp"><img src="imagens/icone.png" alt="Sandwich" style="width:80%; opacity: 0.4"></a>
                        <br><br>
                        <h4 ><a href="./LoginProfissional/AtivarLogin.jsp" style="color: red">Clique aqui para ativar seu login.</a></h4>
                        <br> 
                    </center>                
                </div> 

            </div>
        </div>

        <!-- Footer -->
        <footer class="container-fluid text-center">
            <p>ISERVICES | 2017</p>
            <p>Brenda Renata & Leticia Youssef</p>
        </footer>
    </body>
</html>

