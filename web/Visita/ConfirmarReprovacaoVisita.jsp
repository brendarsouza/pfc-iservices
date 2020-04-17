<%-- 
    Document   : ConfirmarCancelamentoAgenda
    Created on : 15/09/2017, 16:17:36
    Author     : brenda
--%>


<%@page import="Model.Usuario"%>
<%@page import="Model.Visita"%>
<!DOCTYPE html>
<html>
    <head>

        <title>ISERVICES - Confirmar Cancelamento de Agendamento</title>
        <link rel="icon" type="image/png" href="../imagens/logo2.png"/>

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
        <link rel="stylesheet" type="text/css" href="./css/listaPedidos.css">
        <script src='./js/perfil.js'></script>
    </head>
    <body>

        <%
//            Cliente cliente = (Cliente) request.getAttribute("clientePedido");
            Visita visita = (Visita) request.getAttribute("visita");
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
                                                                <a href="./editarProfissional?idEditar=<%= usuario.getId()%>"><i class="fa fa-edit fa-lg"></i> Editar Perfil</a>
                                                                <br/>
                                                                <a href="#" ><i class="fa fa-cogs fa-lg" aria-hidden="true"></i> Configurações</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="navbar-footer">
                                                        <div class="navbar-footer-content">
                                                            <div class="row">

                                                                <div class="col-md-6">
                                                                    <a href="Profissional/ConfirmarInativacao.jsp" class="btn btn-default btn-sm pull-right"><i class="fa fa-power-off" aria-hidden="true"></i>Inativar</a>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <a href="ControleAcessoProfissional?acao=Sair" class="btn btn-default btn-sm pull-right"><i class="fa fa-power-off blue " aria-hidden="true"></i>Sair</a>
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

        <form action="./reprovarVisita" method="post" > 
            <div class="container">
                <div class="w3-card-4" style="width:100%">
                    <header class="w3-container w3-light-grey">
                        <h3>Dados do Pedido</h3>
                    </header>
                    <div class="w3-container">
                        <br>
                        <p style="text-align: right;"><i class="fa fa-user-o"></i> <%=visita.getPedido().getCliente().getNome()%></p>
                        <p style="text-align: right;"><i class="fa fa-clock-o"></i> <%=visita.getPedido().getDataPedido()%></p>
                        <p><i class="fa fa-map-marker"></i> Endereço: <%=visita.getPedido().getCliente().getEndereco().getRua()%>,<%=visita.getPedido().getCliente().getEndereco().getNumero()%> - <%=visita.getPedido().getCliente().getEndereco().getBairro()%>, <%=visita.getPedido().getCliente().getEndereco().getCidade()%> - <%=visita.getPedido().getCliente().getEndereco().getEstado()%> - <%=visita.getPedido().getCliente().getEndereco().getPais()%> </p>
                        <p><i class="fa fa-commenting-o"></i> Descrição do Problema: <%=visita.getPedido().getDescricaoProblema()%></p>
                        <p><i class="fa fa-calendar"></i> Data Disponível: <%=visita.getPedido().getDataDesejada()%></p>                        
                        <p><i class="fa fa-clock-o"></i> Horário Disponível: <%=visita.getPedido().getHorarioInicialDisponivel()%> às <%=visita.getPedido().getHorarioFinalDisponivel()%> </p>

                    </div>
                    <footer class="w3-container w3-light-grey">
                        <br>  
                    </footer>

                </div>
                <br>
                <!-- id do u-->
                <input type="text" hidden name="txtIdVisita" value="<%=visita.getId()%>">
                <input type="text" hidden name="txtIdPedido" value="<%=visita.getPedido().getId()%>">
                <input type="text" hidden name="txtDataVisitaAnterior" value="<%=visita.getDataVisita() %>">
                <h1>Você deseja cancelar a Visita por qual motivo?</h1><br/>
                <p><input type="text-area" class="form-control" name="txtObservacao"></p><br/>
               
                <div class="row">
                    <div class="col-sm-5">

                    </div>
                    <div class="col-sm-2">
                        <input  type="submit" class="btn btn-warning center-block"  value="Salvar">                        
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
        <%----%>
    </body>
</html>
