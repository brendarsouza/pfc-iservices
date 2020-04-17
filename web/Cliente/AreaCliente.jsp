<%-- 
    Document   : AreaCliente
    Created on : 29/09/2017, 17:46:40
    Author     : brenda
--%>
<%@page import="Model.Avaliacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Sexo"%>
<%@page import="Model.Pedido"%>
<%@page import="Model.Cliente"%>
<%@page import="Model.Endereco"%>
<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Área do Cliente</title>
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
        <script src="js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="js/index.js"></script>

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
            Pedido pedido = (Pedido) request.getAttribute("pedido");
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
                        <span class="sr-only">Navegação</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->

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

                    <!-- /.search form -->

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <br/>
                        <li class="header"><h5>Funções</h5></li>
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
                                <li><a href="./consultarVisitasRemarcadasCliente">Visitas Remarcadas </a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-usd text-orange"></i> <span>Orçamentos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarOrcamentoCliente">Todos Orçamentos </a></li>
                                <li><a href="./consultarOrcamentosEmEsperaCliente">Orçamentos Em Espera</a></li>
                                <li><a href="./consultarOrcamentosAprovadosCliente">Orçamentos Aprovados</a></li>
                                <li><a href="./consultarOrcamentosReprovadosCliente">Orçamentos Reprovados</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-book text-red"></i> <span>Agendamento</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./consultarAgendaCliente">Todos Agendamentos </a></li>
                                <li><a href="./consultarAgendaEmEsperaCliente">Agendamentos Em Espera </a></li>
                                <li><a href="./consultarAgendaConcluidaCliente">Agendamentos Concluídos </a></li>
                                <li><a href="./consultarAgendaCanceladaCliente">Agendamentos Cancelados </a></li>
                                <li><a href="./consultarReagendadosCliente">Pedidos Reagendados </a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-star text-purple"></i> <span>Avaliações</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./buscarAvaliacoesCliente">Todas Avaliações </a></li>
                                <li><a href="./consultarNaoAvaliados">Avaliações Pendentes </a></li>
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
                        Área do Cliente
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Área do Cliente</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <!-- left column -->
                        <div class="col-md-12">
                            <!-- general form elements -->
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Buscar Profissionais</h3>
                                </div>
                                <!-- /.box-header -->
                                <!-- form start -->
                                <input type="text" hidden name="txtIdUsuario" value="<%=cliente.getUsuario().getId()%>">
                                <input type="text" hidden name="txtIdCliente" value="<%=cliente.getId()%>">
                                <form role="form" action="./buscarProfissionaisParaSolicitacao" method="get">

                                    <div class="row">
                                        <div class="box-body">

                                            <!-- left column -->
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="">Categoria à contratar:</label>
                                                    <select class="form-control" id="selecao_categoria" required>
                                                        <option> -- Selecione --</option>
                                                    </select>

                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="">Profissão à contratar:</label>
                                                    <select name="txtProfissao" class="form-control"id="selecao_profissao" required>
                                                        <option> -- Selecione --</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="exampleInputPassword1">Cidade de atendimento:</label>
                                                    <input type="search" name="txtCidadeAtendimento" class="form-control" placeholder="Ex: Mogi das Cruzes..." required>

                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <br>
                                                    <button type="submit" class="btn btn-info pull-left"><i class="fa fa-search"></i></button>
                                                    </button></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!--/.col (right) -->
                    </div>
                    <!-- /.row -->



                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h3>Pedidos</h3>

                                    <p>Solicitações de serviços</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-check-square-o"></i>

                                </div>
                                <div class="small-box-footer">
                                    <br/>
                                </div>
                                <div class="box-footer no-padding">
                                    <ul class="nav nav-stacked">
                                        <li><a href="./consultarPedidosCliente">Todos Pedidos <span class="pull-right badge bg-blue"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarPedidosEmEsperaCliente">Pedidos Em Espera <span class="pull-right badge bg-orange"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarPedidosAprovadosCliente">Pedidos Aprovados <span class="pull-right badge bg-green"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarPedidosReprovadosCliente">Pedidos Reprovados <span class="pull-right badge bg-red"><i class="fa fa-arrow-circle-right"></i></span></a></li>

                                    </ul>
                                </div>
                            </div>
                        </div>
                        
                        <!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3>Visitas</h3>

                                    <p>Solicitações de visitas</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-calendar-check-o"></i>
                                </div>
                                <div class="small-box-footer">
                                    <br/>

                                </div>
                                <div class="box-footer no-padding">
                                    <ul class="nav nav-stacked">
                                        <li><a href="./consultarVisitasCliente">Todas Visitas<span class="pull-right badge bg-blue"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarVisitasEmEsperaCliente">Visitas Em Espera <span class="pull-right badge bg-orange"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarVisitasAprovadasCliente">Visitas Aprovadas <span class="pull-right badge bg-green"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarVisitasReprovadasCliente">Visitas Reprovadas <span class="pull-right badge bg-red"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarVisitasCanceladasCliente">Visitas Canceladas <span class="pull-right badge bg-aqua"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarVisitasRemarcadasCliente">Visitas Remarcadas <span class="pull-right badge bg-fuchsia"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-orange">
                                <div class="inner">
                                    <h3>Orçamentos</h3>

                                    <p>Orçamentos</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-usd"></i>

                                </div>
                                <div class="small-box-footer">
                                    <br/>
                                </div>
                                <div class="box-footer no-padding">
                                    <ul class="nav nav-stacked">
                                        <li><a href="./consultarOrcamentoCliente">Todos Orçamentos <span class="pull-right badge bg-blue"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarOrcamentosEmEsperaCliente">Orçamentos Em Espera<span class="pull-right badge bg-orange"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarOrcamentosAprovadosCliente">Orçamentos Aprovados<span class="pull-right badge bg-green"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarOrcamentosReprovadosCliente">Orçamentos Reprovados<span class="pull-right badge bg-red"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3>Agenda</h3>

                                    <p>Pedidos agendados</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-book" aria-hidden="true"></i>

                                </div>
                                <div class="small-box-footer">
                                    <br/>

                                </div>
                                <div class="box-footer no-padding">
                                    <ul class="nav nav-stacked">
                                        <li><a href="./consultarAgendaCliente">Todos Agendamentos<span class="pull-right badge bg-blue"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarAgendaEmEsperaCliente">Agendamentos Em Espera <span class="pull-right badge bg-orange"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarAgendaConcluidaCliente">Agendamentos Concluídos <span class="pull-right badge bg-green"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarAgendaCanceladaCliente">Agendamentos Cancelados <span class="pull-right badge bg-aqua"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarReagendadosCliente">Pedidos Reagendados <span class="pull-right badge bg-fuchsia"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-purple" >
                                <div class="inner">
                                    <h3 style="color:white;">Avaliações</h3>

                                    <p style="color:white;">Avaliações realizadas</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-star" aria-hidden="true"></i>

                                </div>
                                <div class="small-box-footer">
                                    <br/>

                                </div>
                                <div class="box-footer no-padding">
                                    <ul class="nav nav-stacked">
                                        <li><a href="./buscarAvaliacoesCliente">Todas Avaliações<span class="pull-right badge bg-blue"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./consultarNaoAvaliados">Avaliações Pendentes <span class="pull-right badge bg-orange"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li><a href="./buscarPedidosAvaliadosCliente">Pedidos Avaliados <span class="pull-right badge bg-green"><i class="fa fa-arrow-circle-right"></i></span></a></li>
                                        <li></li>
                                        <li></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- ./col -->

                        <!-- /.content -->
                    </div>
                    <!-- /.content-wrapper -->
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
                        <h3 class="control-sidebar-heading">Configurações</h3>
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