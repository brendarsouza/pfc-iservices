<%-- 
    Document   : ListarProf
    Created on : 05/10/2016, 18:21:17
    Author     : brenda
--%>

<%@page import="Model.Pedido"%>
<%@page import="Model.Orcamento"%>
<%@page import="Model.Status_Orcamento"%>
<%@page import="Model.Cliente"%>

<%@page import="Model.Endereco"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Sexo"%>

<%@page import="java.util.List"%>
<%@page import="Model.Avaliacao"%>
<%@page import="java.util.ArrayList"%>

<%@page import="Model.Visita"%>

<%@page import="Model.Status_Pedido"%>
<%@page import="Model.Pedido"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Confirmar Or�amento Cliente</title>
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

            Cliente cliente = (Cliente) session.getAttribute("cliente");
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
            Orcamento orcamento = (Orcamento) request.getAttribute("orcamento");
            Pedido pedido = (Pedido) request.getAttribute("pedido");

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
                                <form action="./areaCliente" method="post"> 
                                    <input type="text" name="txtIdCliente" value="<%=cliente.getId()%>" hidden>
                                    <button type="submit" name="AreaCliente" class="btn btn-warning"><i class="fa fa-home"></i>
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
                                    <span class="hidden-xs"><%=cliente.getNome()%></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="imagens/user-login2.png" class="img-circle" alt="User Image">

                                        <p> 
                                            <%=cliente.getNome()%> <%=cliente.getSobrenome()%>
                                            <small><%=cliente.getUsuario().getEmail()%></small> 
                                        </p>
                                    </li>
                                    <!-- Menu Body -->

                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="./editarCliente?idEditar=<%= cliente.getId()%>" class="btn btn-default btn-flat"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Editar Perfil</a>
                                        </div>

                                        <div class="pull-right">
                                            <a href="LoginCliente/LogoutCliente.jsp" class="btn btn-default btn-flat"><i class="fa fa-power-off" aria-hidden="true"></i></a>
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
                            <p><%=cliente.getNome()%></p>
                            <p><%=cliente.getUsuario().getEmail()%></p>
                        </div>
                    </div>
                    <center><form action="./areaCliente" method="post"> 
                            <input type="text" name="txtIdCliente" value="<%=cliente.getId()%>" hidden>
                            <button type="submit" name="AreaCliente" class="btn btn-md btn-default" style="width: 100%;"><i class="fa fa-home fa-lg"></i></button>
                        </form></center>
                    <!-- /.search form -->

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

                                <li><a href="./consultarPedidosCliente">Todos Pedidos </a></li>
                                <li><a href="./consultarPedidosEmEsperaCliente">Pedidos Em Espera</a></li>
                                <li><a href="./consultarPedidosAprovadosCliente">Pedidos Aprovados</a></li>
                                <li><a href="./consultarPedidosReprovadosCliente">Pedidos Reprovados</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-calendar-check-o text-green"></i> <span>Visitas</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="./consultarVisitasCliente">Todas Visitas </a></li>
                                <li><a href="./consultarVisitasEmEsperaCliente">Visitas Em Espera </a></li>
                                <li><a href="./consultarVisitasAprovadasCliente">Visitas Aprovadas </a></li>
                                <li><a href="./consultarVisitasReprovadasCliente">Visitas Reprovadas </a></li>
                                <li><a href="./consultarVisitasCanceladasCliente">Visitas Canceladas </a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-usd text-orange"></i> <span>Or�amentos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarOrcamentoCliente">Todos Orcamentos </a></li>
                                <li><a href="./consultarOrcamentosEmEsperaCliente">Orcamentos Em Espera</a></li>
                                <li><a href="./consultarOrcamentosAprovadosCliente">Orcamentos Aprovados</a></li>
                                <li><a href="./consultarOrcamentosReprovadosCliente">Orcamentos Reprovados</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-book text-red"></i> <span>Agendamento</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarAgendaCliente">Todas Agendamentos </a></li>
                                <li><a href="./consultarAgendaEmEsperaCliente">Agendamentos Em Espera </a></li>
                                <li><a href="./consultarAgendaConcluidaCliente">Agendamentos Conclu�dos </a></li>
                                <li><a href="./consultarAgendaCanceladaCliente">Agendamentos Cancelados </a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-star text-purple"></i> <span>Avalia��es</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./buscarAvaliacoesCliente">Todas Avalia��es </a></li>
                                <li><a href="./consultarNaoAvaliados">Avalia��es Pendentes </a></li>
                                <li><a href="./buscarPedidosAvaliadosCliente">Pedidos Avaliados </a></li>

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
                        Recusar Or�amento Recebido
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="./index.jsp"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Confirma��o Or�amento</li>
                    </ol>
                </section>

                <section class="content">
                    <form action="./recusarOrcamento" method="post">
                        <input type="name" hidden  name="txtIdOrcamento" value=<%= orcamento.getId()%>>
                        <input type="name" hidden name="txtIdPedido" value=<%= orcamento.getPedido().getId()%>>

                        <input type="name" hidden name="txtIdCliente" value=<%= orcamento.getPedido().getCliente().getId()%>>

                        <input type="name" hidden name="txtIdProfissional" value=<%= orcamento.getPedido().getProfissional().getId()%>>
                        <input type="name" hidden readonly="true" name="txtDescricaoSolucao" value="<%=orcamento.getDescricaoSolucao()%>">
                        <input type="date" hidden readonly="true" name="txtDataOrcamento" value="<%=orcamento.getDataOrcamento()%>">
                        <input type="text" hidden readonly="true" name="txtGastos" value="<%=orcamento.getGastos()%>">
                        <input type="text" hidden readonly="true" name="txtPrazo" value="<%=orcamento.getPrazo()%>">                        
                        <input type="date" hidden readonly="true" name="txtDataInicio" value="<%=orcamento.getDataPrevistaInicio()%>">                        
                        <input type="date" hidden readonly="true" name="txtDataTermino" value="<%=orcamento.getPrazo()%>">                        
                        <input type="text" hidden readonly="true" name="txtPrecoFinal" value="<%=orcamento.getPrecoFinal()%>">                        
                        <div class="row">
                            <div class="col-md-6">
                                <!-- Widget: user widget style 1 -->
                                <div class="box box-widget">
                                    <!-- Add the bg color to the header using any of the bg-* classes -->
                                    <div class="widget-user-header bg-aqua-active" style="padding: 5px 5px 5px 5px">
                                        <h3 style="text-align: center;">Dados do Pedido</h3>
                                    </div>
                                    <div class="box-footer">
                                        <h3 class="widget-user-username"><%=orcamento.getPedido().getProfissional().getNome()%> <%=orcamento.getPedido().getProfissional().getSobrenome()%></h3>
                                        <small><%=orcamento.getPedido().getProfissional().getProfissao().getProfissaoNome()%> | <%=orcamento.getPedido().getProfissional().getProfissao().getCategoria().getCategoriaNome()%></small><br/>
                                        <%--<small><%=orcamento.getPedido().getProfissional().getCidadeAtendimento()%> - <%=orcamento.getPedido().getProfissional().getEstadoAtendimento()%></small>--%>
                                        <h5 class="widget-user-desc"><b>Data do Pedido: </b><%=orcamento.getPedido().getDataPedido()%></h5>
                                        <h5 class="widget-user-desc"><b>Descri��o do Problema: </b><br/>"<i><%=orcamento.getPedido().getDescricaoProblema()%></i>"</h5>
                                        <h5 class="widget-user-desc"><a style="color: #000;" data-toggle="tooltip" title="Dia em que o cliente est� dispon�vel para receber o servi�o!"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data dispon�vel: </b></a><%=orcamento.getPedido().getDataDesejada()%></h5>
                                        <h5 class="widget-user-desc"><a style="color: #000;" data-toggle="tooltip" title="Hor�rio em que o cliente est� dispon�vel para receber o servi�o!"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Hor�rio dispon�vel: </b></a><%=orcamento.getPedido().getHorarioInicialDisponivel()%> at� <%=orcamento.getPedido().getHorarioFinalDisponivel()%></h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- Widget: user widget style 1 -->
                                <div class="box box-widget">
                                    <!-- Add the bg color to the header using any of the bg-* classes -->
                                    <div class="widget-user-header bg-aqua-active" style="padding: 5px 5px 5px 5px">
                                        <h3 style="text-align: center;">Dados do Or�amento</h3>
                                    </div>
                                    <div class="box-footer">
                                        <h5 class="widget-user-desc" style="text-align: right;"><b>Data do Or�amento: </b><%=orcamento.getDataOrcamento()%></h5>
                                        <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data in�cio do Servi�o: </b><%=orcamento.getDataPrevistaInicio()%> �s <i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <%=orcamento.getHoraVisita()%></p>
                                        <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Descri��o da Solu��o:</b> <br/> <i>"<%= orcamento.getDescricaoSolucao()%>"</i> </p> 
                                        <p><i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Prazo: </b><%=orcamento.getPrazo()%></p>
                                        <p><i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i><b> Gastos: </b><%=orcamento.getGastos()%></p>
                                        <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Pre�o Final: </b><%=orcamento.getPrecoFinal()%></p>
                                    </div>
                                </div>
                            </div>
                            <input  type="submit" class="btn btn-success center-block"  id="btnSalvar"  value="Recusar">
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
                                <a href="./editarCliente?idEditar=<%= cliente.getId()%>" >
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
                                <a href="Cliente/ConfirmarInativacao.jsp">
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

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->
    </body>
</html>