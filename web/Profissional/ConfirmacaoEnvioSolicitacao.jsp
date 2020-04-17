<%-- 
    Document   : ConfirmacaoEnvioSolicitacao
    Created on : 18/04/2017, 19:01:37
    Author     : brenda
--%>


<%@page import="Model.Cliente"%>
<%@page import="Model.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enviado com Sucesso</title>
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
        <script>
            // Script to open and close sidenav
            function w3_open() {
                document.getElementById("mySidenav").style.display = "block";
            }
            function w3_close() {
                document.getElementById("mySidenav").style.display = "none";
            }
        </script>
    </head>
<body>
    <%

        
        Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

    %>
    <!-- Navbar -->
    <nav class="w3-sidenav w3-card-2 w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidenav">
        <a href="javascript:void(0)" onclick="w3_close()" class="w3-closenav">Fechar ( X )</a>
        <a href="../index.jsp" onclick="w3_close" >Home</a>
        <a href="Cliente/PerfilCliente.jsp" onclick="w3_close" >Perfil</a>
        <a href="Cliente/EditarCliente.jsp" onclick="w3_close" >Editar Perfil</a>
        <a href="#pedidos" onclick="w3_close()">Pedidos</a>
        <a href="#os" onclick="w3_close()">O.S.</a>
        <a href="#relatorios" onclick="w3_close()">Relatórios</a>
        <a href="#financeiros" onclick="w3_close()">Financeiro</a>
        <a href="#configuracoes" onclick="w3_close()">Configurações</a>

    </nav>
    <div class="w3-top">
        <div class="w3-white w3-xlarge w3-padding-xlarge" >
            <div class="w3-opennav w3-left w3-hover-text-grey" onclick="w3_open()">☰</div>
            <div class="w3-center">ISERVICES</div> 
            <div class="w3-right"><%=usuario.getEmail()%></div> 


        </div>
    </div>
    <div class="container">
        <div class="container-fluid ">
            <br><br><br><br>
            <center><h1>Solicitação Enviada com Sucesso!</h1></center>
            <br><br><HR><br><br>

        </div>
    </div>
    <hr>



    <!-- Footer -->
    <footer class="container-fluid bg-4 text-center">
        <p>ISERVICES</p>
    </footer>
</body>
</html>

