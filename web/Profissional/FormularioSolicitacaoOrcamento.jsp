<%-- 
    Document   : FormularioSolicitacaoOrcamento
    Created on : 19/04/2017, 12:28:53
    Author     : brenda
--%>


<%@page import="Model.Cliente"%>
<%@page import="Model.Profissional"%>
<%@page import="Model.Usuario"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>

        <title>Formulario de Solicitacao de Orcamento</title>
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
        <link rel="stylesheet" type="text/css" href="./css/materialize.css">
        <link rel="stylesheet" type="text/css" href="./css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <script src='./js/materialize.js'></script>
        <script src='./js/materialize.min.js'></script>

        <link rel="stylesheet" type="text/css" href="./css/perfilCliente.css">
        <script src='./js/perfil.js'></script>
        <script>
            $('.timepicker').pickatime({
                default: 'now', // Set default time: 'now', '1:30AM', '16:30'
                fromnow: 0, // set default time to * milliseconds from now (using with default = 'now')
                twelvehour: false, // Use AM/PM or 24-hour format
                donetext: 'OK', // text for done-button
                cleartext: 'Clear', // text for clear-button
                canceltext: 'Cancel', // Text for cancel-button
                autoclose: false, // automatic close timepicker
                ampmclickable: true, // make AM PM clickable
                aftershow: function () {} //Function for after opening timepicker
            });
        </script>
    </head>
    <body style="background-color: white">
        <%
            Profissional profissional = (Profissional) request.getAttribute("pessoaProfissional");
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
        %>  
        <div class="container">
            <div class="row">
                <div class="navbar-wrapper"  >
                    <div class="container-fluid">
                        <div class="navbar navbar-static-top" style="background-color: white" role="navigation">
                            <div class="container-fluid">
                                <div class="navbar-header" >


                                    <div class="w3-center"><a  href="index.jsp" ><img src="imagens/icone_1.png" alt="Sandwich" style="width:190px"> </a></div>
                                </div>
                                <div class="navbar-collapse collapse">
                                    <ul class="nav navbar-nav" >
                                        <li><a href="../index.jsp"style="color: #000;">Home</a></li>
                                        <li><span class="badge badge-important"></span><a href="./consultarPedidos?idPedido="<%=usuario.getId()%>><i class="fa fa-bell-o fa-lg" style="color: #000;" aria-hidden="true"></i></a></li>
                                        <li><a href="#"><i class="fa fa-envelope-o fa-lg" aria-hidden="true"></i></a></li>
                                        <li><a href="#"><i class="fa fa-question-circle-o fa-lg" aria-hidden="true"></i></a></li>
                                    </ul>
                                    <input type="text" hidden name="txtIdUsuario" value="<%= usuario.getId()%>">
                                    <ul class="nav navbar-nav navbar-right">
                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                <span class="user-avatar pull-left" style="margin-right:8px; margin-top:-5px;">
                                                    <img src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="img-responsive img-circle"  width="30px" height="30px">
                                                </span>

                                                <b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <div class="navbar-content">
                                                        <div class="row">
                                                            <div class="col-md-5">
                                                                <p><center><img src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="img-responsive img-circle"  width="60px" height="60px"></center></p>
                                                                <p class="text-center small"><a href="./3X6zm">Trocar Foto</a></p>
                                                            </div>
                                                            <div class="col-md-7">

                                                                <p class="text-muted small">
                                                                    <%=usuario.getEmail()%></p>
                                                                <div class="divider">
                                                                </div>
                                                                <a href="./editarCliente?idEditar=<%= usuario.getId()%>"><i class="fa fa-edit fa-lg"></i> Editar Perfil</a>
                                                                <!--<a href="./editarProfissional?idEditar=<%=usuario.getId()%>" ><i class="fa fa-edit fa-lg"></i> Editar Perfil</a>-->
                                                                <br/>
                                                                <a href="#" ><i class="fa fa-cogs fa-lg" aria-hidden="true"></i> Configurações</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="navbar-footer">
                                                        <div class="navbar-footer-content">
                                                            <div class="row">

                                                                <div class="col-md-6">
                                                                    <a href="Cliente/ConfirmarInativacao.jsp" class="btn btn-default btn-sm pull-right"><i class="fa fa-power-off" aria-hidden="true"></i>Inativar</a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="ControleAcessoCliente?acao=Sair" class="btn btn-default btn-sm pull-right"><i class="fa fa-power-off blue " aria-hidden="true"></i>Sair</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div class="container">
            <div class="row">     
                <div class="view">
                    <div class="caption">
                        <div class="col-sm-6">
                            <h4>Confirme os dados do profissional escolhido</h4>

                            <p><i class="fa fa-user-o fa-fw w3-margin-right w3-large w3-text-teal"></i><%=profissional.getNome()%> <%=profissional.getSobrenome()%></p>
                            <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i><%=profissional.getUsuario().getEmail()%></p>
                            <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i> <%=profissional.getTelefone()%> | <%=profissional.getCelular()%> </p>
                            <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i><%= profissional.getEndereco().getRua()%>, <%=profissional.getEndereco().getNumero()%> </p>
                            <p><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i><%=profissional.getEndereco().getBairro()%>, <%=profissional.getEndereco().getCidade()%> - <%=profissional.getEndereco().getEstado()%> </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form action="./enviarSolicitacao" method="post" > 
            <div class="container" style="padding-top: 5%">
                <br>
                <!-- id do u-->
                <input type="text" hidden name="txtIdProfissional" value="<%=profissional.getId()%>">
                <input type="text" hidden name="txtIdCliente" value="<%=cliente.getId()%>">

                <center>
                    <h4>Enviar Solicitação de Orçamento</h4>
                </center>

                <div class="form-group">
                    <label for="comment">Descrição do Problema</label>
                    <textarea class="form-control" rows="5" name="txtDescricaoProblema" id="descricaoProblema"></textarea>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-2">
                        <label for="nome">Data desejada:</label>

                    </div>
                    <div class="col-sm-2">
                        <input type="date" class="form-control" id="dataDesejada" name="txtDataDesejada" >
                    </div>

                    <div class="form-group col-sm-2">                       
                        <label for="horario">Horário desejado:</label>
                        <!--<a href="#" class="dcontexto"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><span>Coloque o horário em que você estará disponível no local em que será realizado o serviço.</span></a>-->
                    </div>
                    <div class="form-group col-sm-2">
                        <!--<input type="time" class="ss-q-time required" dir="auto" width="20px" id="horarioInicialDisponivel" required="" name="txtHorarioInicialDisponivel">hs-->
                        <input type="text" class="timepicker">
                    </div>

                    <div class="form-group col-sm-1">                       
                        <label for="nome">às:</label>
                    </div>
                    <div class="form-group col-sm-2">
                        <!--<input type="time" class="ss-q-time required" dir="auto" width="20px" id="horarioFinalDisponivel" required="" name="txtHorarioFinalDisponivel">hs-->
                        <input type="text" class="timepicker">
                    </div>


                </div>
                <br>
                <div class="row">

                </div>

                <div class="row">
                    <div class="col-sm-5">
                    </div>
                    <div class="col-sm-2">
                        <input  type="submit" class="btn btn-success center-block"  id="btnEnviar"  value="Enviar">                        
                    </div>
                    <div class="col-sm-5">
                    </div>
                </div>
            </div>

        </form>
        <hr>
        <!-- Footer -->
        <footer class="container-fluid text-center">
            <br/>
            <p>ISERVICES | 2017</p>
            <p>Brenda Renata & Leticia Youssef</p>
        </footer>

    </body>
</html>
