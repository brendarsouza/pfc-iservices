<%-- 
    Document   : ConfirmacaoEnvioSolicitacao
    Created on : 18/04/2017, 19:01:37
    Author     : brenda
--%>


<%@page import="Model.Cliente"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Sexo"%>
<%@page import="Model.Pedido"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Confirma��o de Edi��o Perfil</title>
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

            Cliente cliente = (Cliente) request.getAttribute("clienteEditado");
            Cliente clienteEditado = (Cliente) session.getAttribute("cliente");
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
        %>
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="index.jsp" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b><img src="./imagens/logo2.png" style="height:40px; width: 40px;" class="img-thumbnail" alt="ISERVICES"></b></span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b><img src="./imagens/icone2.png" style="height:48px; width: 170px; align-self: center;"class="img-lg" alt="ISERVICES"></b></span>
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
                                <form action="areaCliente" method="post"> 
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
                                    <img src="./imagens/user-login2.png" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs"><%=cliente.getNome()%></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="./imagens/user-login2.png" class="img-circle" alt="User Image">

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
                                            <a href="./LoginCliente/LogoutCliente.jsp" class="btn btn-default btn-flat"><i class="fa fa-power-off" aria-hidden="true"></i></a>
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
                            <img src="./imagens/user-login.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p><%=cliente.getNome()%></p>
                            <p><%=cliente.getUsuario().getEmail() %></p>
                        </div>
                    </div>
                    <center><form action="areaCliente" method="post"> 
                            <input type="text" name="txtIdCliente" value="<%=clienteEditado.getId()%>" hidden>
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
                        Seu perfil foi editado com sucesso!
                    </h1>
                    <small>Confira seus dados abaixo.</small>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Perfil </li>
                    </ol>
                </section>

                <section class="content">

                    <div class="row">
                        <div class="col-md-12">

                            <!-- Profile Image -->
                            <div class="box box-primary">
                                <div class="box-body box-profile">
                                    <%
                                    if(cliente.getSexo() == Sexo.FEMININO){
                                    %>
                                    <p class="text-muted text-right"><a style="color: #FA396F;" data-toggle="tooltip" title="Sexo Feminino"><i class="fa fa-female fa-3x" aria-hidden="true"></i></a></p>

                                    <%
                                                                        }else{
                                    %>
                                    <p class="text-muted text-right"><a style="color: #0059bc;" data-toggle="tooltip" title="Sexo Masculino"><i class="fa fa-male fa-3x" aria-hidden="true"></i></a></p>
                                            <%
                                       }
                                            %>
                                    <img class="profile-user-img img-responsive img-circle" src="./imagens/user-login.png" alt="User profile picture">

                                    <h3 class="profile-username text-center"><%=cliente.getNome()%> <%=cliente.getSobrenome()%></h3>

                                    <p class="text-muted text-center"><%=cliente.getUsuario().getEmail()%></p>
                                    <p><br/></p>
                                    <p class="text-muted text-center">
                                    <div class="row">
                                        <div class="col-sm-6 text-center">
                                            <b>CPF: </b> <a class="text-muted"><%=cliente.getCpf()%></a>
                                        </div>
                                        <div class="col-sm-6 text-center">
                                            <b>RG:</b> <a class="text-muted"><%=cliente.getRg()%></a>
                                        </div>
                                    </div>
                                    </p>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                            <!-- About Me Box -->
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Sobre mim</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <strong><i class="fa fa-user-o margin-r-5"></i>Dados pessoais </strong>
                                    <p><br/></p>
                                    <p class="text-muted"><i class="fa fa-birthday-cake text-fuchsia" aria-hidden="true"></i> <%= cliente.getDataNascimento()%></p>
                                    <p class="text-muted"><i class="fa fa-mobile text-black" aria-hidden="true"></i> <%= cliente.getCelular()%></p>
                                    <p class="text-muted"><i class="fa fa-phone text-aqua" aria-hidden="true"></i> <%= cliente.getTelefone()%></p>
                                    <hr>

                                    <strong><i class="fa fa-map-marker margin-r-5 text-blue"></i> Endere�o</strong>
                                    <p><br/></p>
                                    <p class="text-muted"><%= cliente.getEndereco().getRua()%>, <%= cliente.getEndereco().getNumero()%></p>
                                    <p class="text-muted">CEP: <%= cliente.getEndereco().getCep()%></p>
                                    <p class="text-muted"><%= cliente.getEndereco().getBairro()%>, <%= cliente.getEndereco().getCidade()%> - <%= cliente.getEndereco().getEstado()%></p>

                                </div>
                                    <div class="box-footer">
                                    </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                    </div>
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
                                <a href="./Cliente/ConfirmarInativacao.jsp">
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