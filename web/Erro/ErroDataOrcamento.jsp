<%-- 
    Document   : ErroData
    Created on : 14/10/2017, 09:28:06
    Author     : brenda
--%>

<%@page import="Model.Profissional"%>
<%@page import="Model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISERVICES - ERRO DATA ORCAMENTO</title>
        <link rel="icon" type="image/png" href="imagens/logo2.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="./css/erro.css">
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
        <%
            Profissional profissional = (Profissional) session.getAttribute("profissional");
        %>
        <nav class="navbar">
            <nav class="w3-sidenav w3-card-4 w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:30%;min-width:20px" id="mySidenav">
                <a href="javascript:void(0)" onclick="w3_close()" class="w3-closenav">Fechar ( X )</a>
                <a href="index.jsp" onclick="w3_close" >Home</a>
                <a href="./LoginProfissional/LoginProfissional.jsp">Área do Profissional</a>
                <a href="./cadastro.jsp">Cadastre-se</a>
                <a href="./LoginCliente/LoginCliente.jsp">Entrar</a>
            </nav>
        </nav>
        <div class="w3-top">
            <div class="w3-white w3-xlarge w3-padding-xlarge" style="max-width:1200px;margin:auto;">
                <div class="w3-opennav w3-left w3-hover-text-grey" style="margin-bottom: auto" onclick="w3_open()">☰</div>
                <div class="w3-center"><a href="index.jsp"><img src="../imagens/icone_1.png" alt="ISERVICES" style="width:190px"> </a>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="./LoginProfissional/LoginProfissional.jsp">Área do Profissional</a></li>  
                        <li><a href="cadastro.jsp">Cadastre-se</a></li>
                        <li><a href="./LoginCliente/LoginCliente.jsp">Entrar</a></li> 
                    </ul>   
                </div>
            </div>
        </div>        

        <div>
            <form action="../areaProfissional" method="post" > 
                <div class="container-fluid">
                    <div class="bg-1">
                        <br>
                        <center>
                            <input type="text" name="txtIdProfissional" value="<%=profissional.getId()%>" hidden>
                            <h3 style="color: black">Erro a data informada é inválida! <br/>Tente novamente!</h3>
                            <input type="submit" class="btn-info" value="Voltar">
                            <br>
                            <br>
                            <br> 
                        </center>                
                    </div> 
                </div>
            </form>
        </div>

        <!-- Footer -->
        <footer class="container-fluid text-center">
            <p>ISERVICES | 2017</p>
            <p>Brenda Renata & Leticia Youssef</p>
        </footer>
    </body>
</html>
