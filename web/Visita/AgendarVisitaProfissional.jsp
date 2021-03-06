<%@page import="Model.Status_Orcamento"%>
<%@page import="Model.Orcamento"%>
<!DOCTYPE html>
<%@page import="Model.Status_Servico"%>
<%@page import="Model.Status_Visita"%>
<%@page import="Model.AgendaDeServico"%>
<%@page import="Model.Visita"%>
<%@page import="Model.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Status_Pedido"%>
<%@page import="Model.Profissional"%>
<%@page import="Model.Endereco"%>
<%@page import="Model.Cliente"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>-->
        <title>ISERVICES - Agendar Visita Profissional</title>
        <link rel="icon" type="image/png" href="imagens/logo2.png"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="admtle/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="admtle/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="admtle/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="admtle/AdminLTE.min.css">
        <link rel="stylesheet" href="admtle/skin-yellow.css">
        <link rel="stylesheet" href="admtle/skin-yellow.min.css">
        <link rel="stylesheet" href="admtle/skin-yellow-light.min.css">



        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <!--
    BODY TAG OPTIONS:
    =================
    Apply one or more of the following classes to get the
    desired effect
    |---------------------------------------------------------|
    | SKINS         | skin-blue                               |
    |               | skin-black                              |
    |               | skin-purple                             |
    |               | skin-yellow                             |
    |               | skin-red                                |
    |               | skin-green                              |
    |---------------------------------------------------------|
    |LAYOUT OPTIONS | fixed                                   |
    |               | layout-boxed                            |
    |               | layout-top-nav                          |
    |               | sidebar-collapse                        |
    |               | sidebar-mini                            |
    |---------------------------------------------------------|
    -->
    <body class="hold-transition skin-yellow-light sidebar-mini">
        <%
            Profissional profissional = (Profissional) session.getAttribute("profissional");
            Pedido pedido = (Pedido) request.getAttribute("pedido");
            List<Visita> visita = (List<Visita>) request.getAttribute("visita");
            List<AgendaDeServico> agenda = (List<AgendaDeServico>) request.getAttribute("agenda");
            List<Orcamento> orcamento = (List<Orcamento>) request.getAttribute("orcamento");
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
        %>
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="index.jsp" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b><img src="imagens/logo2.png" style="height:40px; width: 40px;" class="img-thumbnail" alt="ISERVICES"></b></span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b><img src="imagens/icone2.png" style="height:48px; width: 170px; align-self: center;"class="img-lg" alt="ISERVICES"></b></span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Navega��o</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
                            <li class="dropdown messages-menu" style="padding-top: 5%;">
                                <form action="./areaProfissional" method="post"> 
                                    <input type="text" name="txtIdProfissional" value="<%=profissional.getId()%>" hidden>
                                    <button type="submit" name="AreaProfissional" class="btn btn-warning"><i class="fa fa-home"></i>
                                    </button>
                                </form>
                            </li>
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="imagens/user-login2.png" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs"><%=profissional.getNome()%></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="imagens/user-login2.png" class="img-circle" alt="User Image">

                                        <p> <%=profissional.getNome()%> <%=profissional.getSobrenome()%>
                                            <small><%=profissional.getUsuario().getEmail()%></small> 
                                            <small> <%=profissional.getProfissao().getProfissaoNome()%> | <%=profissional.getProfissao().getProfissaoNome()%></small></p>
                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="#"><i class="fa fa-star"></i> <%=profissional.getMediaAvaliacaoGeral()%></a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#"><i class="fa fa-usd"></i> <%=profissional.getMediaAvaliacaoCusto()%></a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#"><i class="fa fa-clock-o"></i> <%=profissional.getMediaAvaliacaoRapidez()%></a>
                                            </div>
                                        </div>
                                        <!-- /.row -->
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="./editarProfissional?idEditar=<%=profissional.getId()%>" class="btn btn-default btn-flat"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Editar Perfil</a>
                                        </div>

                                        <div class="pull-right">
                                            <a href="LoginProfissional/LogoutProfissional.jsp" class="btn btn-default btn-flat"><i class="fa fa-power-off" aria-hidden="true"></i></a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="imagens/user-login.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p><%=profissional.getNome()%></p>
                            <p><%=profissional.getProfissao().getProfissaoNome()%></p>
                        </div>
                    </div>
                    <center><form action="./areaProfissional" method="post"> 
                            <input type="text" name="txtIdProfissional" value="<%=profissional.getId()%>" hidden> 
                            <button type="submit" name="AreaProfissional" class="btn btn-md btn-default" style="width: 100%;"><i class="fa fa-home fa-lg"></i></button>
                        </form></center>


                    <!-- /.search form -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <br/>

                        <li class="header"><h5>M�dia de Avalia��es</h5></li>
                        <li><a href="#"><i class="fa fa-star text-yellow"></i> <span><%=profissional.getMediaAvaliacaoGeral()%></span></a></li>
                        <li><a href="#"><i class="fa fa-usd text-green"></i> <span><%=profissional.getMediaAvaliacaoCusto()%></span></a></li>
                        <li><a href="#"><i class="fa fa-clock-o text-aqua"></i> <span><%=profissional.getMediaAvaliacaoRapidez()%></span></a></li>
                    </ul>

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <br/>
                        <li class="header"><h5>Fun��es</h5></li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-check-square-o text-aqua"></i> <span>Pedidos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarTodosPedidosProfissional">Todos Pedidos </a></li>
                                <li><a href="./consultarPedidosEmEsperaProfissional">Pedidos Em Espera </a></li>
                                <li><a href="./consultarPedidosAprovadosProfissional">Pedidos Aprovados </a></li>
                                <li><a href="./consultarPedidosReprovadosProfissional">Pedidos Reprovados </a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-calendar-check-o text-green"></i> <span>Visitas</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarVisitasProfissional">Todas Visitas</a></li>
                                <li><a href="./consultarVisitasEmEsperaProfissional">Visitas Em Espera</a></li>
                                <li><a href="./consultarVisitasAprovadasProfissional">Visitas Aprovadas</a></li>
                                <li><a href="./consultarVisitasReprovadasProfissional">Visitas Reprovadas</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-usd text-orange"></i> <span>Or�amentos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="./consultarOrcamentoProfissional">Todos Orcamentos </a></li>
                                <li><a href="./consultarOrcamentosEmEsperaProfissional">Orcamentos Em Espera</a></li>
                                <li><a href="./consultarOrcamentosAprovadosProfissional">Orcamentos Aprovados</a></li>
                                <li><a href="./consultarOrcamentosReprovadosProfissional">Orcamentos Reprovados</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-book text-red"></i> <span>Agendamento</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarAgendaProfissional">Todas Agendamentos</a></li>
                                <li><a href="./consultarAgendaEmEsperaProfissional">Agendamentos Em Espera</a></li>
                                <li><a href="./consultarAgendaConcluidaProfissional">Agendamentos Conclu�dos</a></li>
                                <li><a href="./consultarAgendaCanceladaProfissional">Agendamentos Cancelados</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-star text-purple"></i> <span>Avalia��es</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./buscarTodasAvaliacoesProfissional">Todas Avalia��es</a></li>
                                <li><a href="./buscarAvaliacoesPendentesProfissional">Avalia��es Pendentes</a></li>
                                <li><a href="./buscarAvaliadosProfissional">Pedidos Avaliados</a></li>

                            </ul>
                        </li>

                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Agendar Visita
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Agendar Visita</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <form action="./agendarVisita" method="post" > 
                        <div class="container-fluid">
                            <br>
                            <!-- id do u-->
                            <input type="text" hidden name="txtIdCliente" value="<%=pedido.getCliente().getId()%>">
                            <input type="text" hidden name="txtIdPedido" value="<%=pedido.getId()%>">
                            <input type="text"  hidden class="idProfissional" name="txtIdProfissional" value="<%=pedido.getProfissional().getId()%>">
                            <input type="text" hidden name="txtDescricaoProblema" value="<%=pedido.getDescricaoProblema()%>">
                            <input type="text" hidden name="txtDataDesejada" value="<%=pedido.getDataDesejada()%>">
                            <input type="text" hidden name="txtHorarioInicialDisponivel" value="<%=pedido.getHorarioInicialDisponivel()%>">
                            <input type="text" hidden name="txtHorarioFinalDisponivel" value="<%=pedido.getHorarioFinalDisponivel()%>">        
                            <input type="text" hidden name="txtDataPedido" value="<%=pedido.getDataPedido()%>">
                            <div class="row">

                                <div class="col-md-3">
                                    <!-- Widget: user widget style 1 -->
                                    <div class="box box-widget">
                                        <!-- Add the bg color to the header using any of the bg-* classes -->
                                        <div class="widget-user-header bg-aqua-active" style="padding: 5px 5px 5px 5px">
                                            <h3 style="text-align: center;">Datas Reservadas</h3>
                                        </div>
                                        <div class="box-footer"><center>
                                                <h4><b>Visitas</b></h4>
                                                <%for (Visita v : visita) {%>
                                                <%if (v.getStatus_Visita() != Status_Visita.CANCELADA && v.getStatus_Visita() != Status_Visita.REPROVADA){ %>

                                                <p style="color:red;"><i class="fa fa-calendar-times-o fa-fw w3-margin-right w3-large w3-text-teal"></i>
                                                    <%=v.getDataVisita()%> �s <%=v.getHoraVisita()%>
                                                </p>                                    
                                                <%}}%>
                                                <br/>
                                                <h4><b>Or�amentos</b></h4>
                                                <%for (Orcamento o : orcamento) {%>
                                                <% if(o.getStatus_Orcamento() != Status_Orcamento.REPROVADO){ %>
                                                <p style="color:red;"><i class="fa fa-calendar-times-o fa-fw w3-margin-right w3-large w3-text-teal"></i>
                                                    <%=o.getDataPrevistaInicio()%> �s <%=o.getHoraVisita()%>
                                                </p>                                    
                                                <%}}%>
                                                <br/>

                                                <h4><b>Agenda de Servi�os</b></h4>
                                                <%for (AgendaDeServico a : agenda) {%>
                                                <%if (a.getStatus_servico() == Status_Servico.EM_ESPERA) {%>
                                                <p style="color:red;"><i class="fa fa-calendar-times-o fa-fw w3-margin-right w3-large w3-text-teal"></i>
                                                    <%=a.getDataAgendamento()%> �s <%=a.getHoraAgendamento()%>
                                                </p>                                    
                                                <%}}%>
                                                <br/>
                                            </center></div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <!-- Widget: user widget style 1 -->
                                    <div class="box box-widget">
                                        <!-- Add the bg color to the header using any of the bg-* classes -->
                                        <div class="widget-user-header bg-aqua-active" style="padding: 5px 5px 5px 5px">
                                            <h3 style="text-align: center;">Dados do Pedido</h3>
                                        </div>
                                        <div class="box-footer">
                                            <h3 class="widget-user-username"><%=pedido.getCliente().getNome()%> <%=pedido.getCliente().getSobrenome()%></h3>
                                            <p><small><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Endere�o: </b><%= pedido.getCliente().getEndereco().getCidade()%> - <%= pedido.getCliente().getEndereco().getEstado()%></small></p> 
                                            <h5 class="widget-user-desc"><b>Data do Pedido: </b><%=pedido.getDataPedido()%></h5>
                                            <h5 class="widget-user-desc"><b>Descri��o do Problema: </b><br/>"<i><%=pedido.getDescricaoProblema()%></i>"</h5>
                                            <h5 class="widget-user-desc"><a style="color: #000;" data-toggle="tooltip" title="Dia em que o cliente est� dispon�vel para receber o servi�o!"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data dispon�vel: </b></a><%=pedido.getDataDesejada()%></h5>
                                            <h5 class="widget-user-desc"><a style="color: #000;" data-toggle="tooltip" title="Hor�rio em que o cliente est� dispon�vel para receber o servi�o!"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Hor�rio dispon�vel: </b></a><%=pedido.getHorarioInicialDisponivel()%> at� <%=pedido.getHorarioFinalDisponivel()%></h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <!-- Widget: user widget style 1 -->
                                    <div class="box box-widget">
                                        <!-- Add the bg color to the header using any of the bg-* classes -->
                                        <div class="widget-user-header bg-aqua-active" style="padding: 5px 5px 5px 5px">
                                            <h3 style="text-align: center;">Dados da Visita</h3>
                                        </div>
                                        <div class="box-footer">
                                            <h3 class="widget-user-username">Data da Visita:</h3>
                                            <div class="input-group date">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="date" class="form-control pull-right" id="dataVisita" name="txtDataVisita" >
                                            </div>

                                            <h3 class="widget-user-username">Hor�rio Visita:</h3>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-clock-o"></i>
                                                </div>
                                                <input type="time" class="ss-q-time form-control pull-left required" dir="auto" id="horarioVisita" required="" name="txtHorarioVisita">
                                            </div>

                                            <p><br/><input  type="submit" class="btn btn-success center-block"  id="btnSalvar"  value="Agendar"></p>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </form>
                </section>
            </div>

            <!-- Main Footer -->
            <footer class="main-footer">
                <!-- To the right -->
                <center><div class="pull-center hidden-sm">
                        Brenda Renata & Leticia Youssef <br/>
                    </div>
                    <!-- Default to the left -->
                    <strong>ISERVICES | 2017.</strong></center>
            </footer>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-light">
                <!-- Create the tabs -->
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                    <!--<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>-->
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <!-- Home tab content -->
                    <div class="tab-pane active" id="control-sidebar-home-tab">

                        <!--Editar Perfil-->
                        <h3 class="control-sidebar-heading">Configura��es</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="./editarProfissional?idEditar=<%=profissional.getId()%>" >
                                    <i class="menu-icon fa fa-pencil-square-o bg-aqua"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Editar Perfil</h4>
                                    </div>
                                </a>
                            </li>
                        </ul>

                        <!--Desativar conta-->

                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="Profissional/ConfirmarInativacao.jsp">
                                    <i class="menu-icon fa fa-power-off bg-red"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Desativar Conta</h4>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->


                    </div>

                </div>
            </aside>
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
            immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="admtle/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="admtle/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="admtle/adminlte.min.js"></script>
        <!--<script src="js/validacaoData.js"></script>-->
    </body>
</html>