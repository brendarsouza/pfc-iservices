<!DOCTYPE html>
<%@page import="Model.Profissional"%>
<%@page import="Model.Avaliacao"%>
<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Pedido"%>
<%@page import="Model.Cliente"%>
<%@page import="Model.Endereco"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Sexo"%>
<%@page import="java.util.List"%>
<%@page import="Model.Avaliacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Visita"%>
<%@page import="Model.Status_Pedido"%>
<%@page import="Model.Status_Visita"%>
<%@page import="Model.Status_Avaliacao"%>
<%@page import="Model.Avaliacao"%>
<%@page import="Model.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="Model.Status_Servico"%>
<%@page import="Model.Status_Orcamento"%>
<%@page import="Model.AgendaDeServico"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Listar Avalia��es</title>
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
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
            List<Avaliacao> avaliacoes = (List<Avaliacao>) request.getAttribute("avaliacao");
            Profissional profissional = (Profissional) session.getAttribute("profissional");

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
                                <li><a href="./consultarVisitasCanceladasProfissional">Visitas Canceladas</a></li>
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
                        Listar Avalia��es
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Listar Avalia��es</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!--PENDENTES-->
                    <div class="box">
                        <div class="box-header with-border" style="background-color: #ffb300; ">
                            <h3 class="box-title" style="color: white;">Avalia��es Pendentes</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                                    <i class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <% 
                                for (Avaliacao avaliacao : avaliacoes) {
                                if(avaliacao.getStatus() == Status_Avaliacao.PENDENTE){
                            %> 
                            <div class="panel-body">
                                <div class="row-fluid">
                                    <div class="span3">
                                        <div class="row">
                                            <div class="col-md-5">

                                                <p><h2><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getNome()%> <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getSobrenome()%> </h2></p>
                                                <p><small><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Endere�o: </b><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getCidade()%> - <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getEstado()%></small></p> 
                                                <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Data do servi�o: <%=avaliacao.getAgenda().getDataAgendamento()%> �s <%=avaliacao.getAgenda().getHoraAgendamento()%> </p>                                                
                                                <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Descri��o da Solu��o:</b> <br/> <i>&emsp;"<%= avaliacao.getAgenda().getOrcamento().getDescricaoSolucao()%>"</i> </p> 
                                                <p><i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Prazo para conclus�o do Servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPrazo()%></p>
                                                <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Gastos: </b><%=avaliacao.getAgenda().getOrcamento().getGastos()%></p>
                                                <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Pre�o Final: </b><%=avaliacao.getAgenda().getOrcamento().getPrecoFinal()%></p>


                                            </div>
                                            <div class="col-md-5">

                                                <div style="text-align: right" class="text-muted"><b> N� Pedido: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getId()%></div>
                                                <% if (avaliacao.getStatus() == Status_Avaliacao.PENDENTE){%>
                                                <p style="text-align: right"><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Pendente</span></p>
                                                <% } else if (avaliacao.getStatus() == Status_Avaliacao.AVALIADO){%>
                                                <p style="text-align: right"><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Avaliado </span></p>
                                                <% } %>

                                                <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data da Finaliza��o do Servico: </b><%=avaliacao.getAgenda().getDataFinalizacao()%></p>
                                                <p></p>
                                                <p style="text-align: right"><i class="fa fa-commenting-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Comentario: </b> <%=avaliacao.getComentario()%> </p>
                                                <p style="text-align: right"><i class="fa fa-star fa-fw w3-margin-right w3-large w3-text-teal text-yellow"></i><b>Avalia��o Geral: </b> <%=avaliacao.getAvaliacaoGeral()%></p>
                                                <p style="text-align: right"><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal text-green"></i><b>Avalia��o de Custo: </b> <%=avaliacao.getAvaliacaoCusto()%></p>
                                                <p style="text-align: right"><i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal text-aqua"></i><b>Avalia��o de Rapidez: </b> <%=avaliacao.getAvaliacaoRapidez()%></p>


                                            </div>
                                            <div class="col-md-2">

                                                <span class="pull-right">
                                                    <button class="btn btn-primary btn-lg" style="width: 100%" type="button" data-toggle="modal" data-target="#myModal<%=avaliacao.getAgenda().getOrcamento().getId()%>">
                                                        <a href="#" style="color: white"><i class="fa fa-plus-circle img-circle text-default"></i> Ver mais</a>
                                                    </button>
                                                        
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>

                            <!-- Modal -->
                            <div class="row slideanim">
                                <div class="col-sm-12">
                                    <!-- Modal -->
                                    <div id="myModal<%=avaliacao.getAgenda().getOrcamento().getId()%>" class="modal fade" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Or�amento recebido</h4>
                                                </div>

                                                <div class="modal-body">
                                                    <h4><i class="fa fa-user-o" aria-hidden="true"></i> | <%=avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getNome()%></span></h4>
                                                    <p><small><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Endere�o: </b><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getCidade()%> - <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getEstado()%></small></p> 

                                                    <p><hr/></p>
                                                    <p> <h4>Pedido </h4> </p>
                                                    <p style="text-align: right" class="text-muted"><i class="fa fa-clock-o" aria-hidden="true"></i> <b>Data do Pedido:</b> <%=avaliacao.getAgenda().getOrcamento().getPedido().getDataPedido()%></p>
                                                    <% if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.APROVADO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Aprovado </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.REPROVADO){%>
                                                    <p><span class="label label-danger"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Reprovado </span></p>
                                                    <% } %>


                                                    <p><b> N� Pedido: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getId()%></p>

                                                    <p><i class="fa fa-calendar-check-o" aria-hidden="true"></i> <b>Dia dispon�vel para receber o servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getDataDesejada()%></p>
                                                    <p><i class="fa fa-clock-o" aria-hidden="true"></i><b> Hor�rio dispon�vel para receber o servi�o:</b> <%=avaliacao.getAgenda().getOrcamento().getPedido().getHorarioInicialDisponivel()%> �s <%=avaliacao.getAgenda().getOrcamento().getPedido().getHorarioFinalDisponivel()%> </p>
                                                    <p><i class="fa fa-file-text-o" aria-hidden="true"></i><b> Descri��o Problema:</b><br/> <i>"<%=avaliacao.getAgenda().getOrcamento().getPedido().getDescricaoProblema()%>"</i></p>
                                                    <p><hr/></p>

                                                    <p> <h4>Orcamento </h4> </p>
                                                    <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data do Or�amento: </b><%=avaliacao.getAgenda().getOrcamento().getDataOrcamento()%></p>
                                                            <% if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.APROVADO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Aprovado </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.REPROVADO){%>
                                                    <p><span class="label label-danger"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Reprovado </span></p>
                                                    <% } %>

                                                    <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data in�cio do Servi�o: </b><%=avaliacao.getAgenda().getDataAgendamento()%> �s <i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <%=avaliacao.getAgenda().getHoraAgendamento()%></p>
                                                    <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Descri��o da Solu��o:</b> <br/> <i>"<%= avaliacao.getAgenda().getOrcamento().getDescricaoSolucao()%>"</i> </p> 
                                                    <p><i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Prazo para conclus�o do Servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPrazo()%></p>
                                                    <p><i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Gastos: </b><%=avaliacao.getAgenda().getOrcamento().getGastos()%></p>
                                                    <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Pre�o Final: </b><%=avaliacao.getAgenda().getOrcamento().getPrecoFinal()%></p>
                                                    <p><hr/></p>

                                                    <p> <h4>Agendamento </h4> </p>
                                                    <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data da Finaliza��o: </b><%=avaliacao.getAgenda().getDataFinalizacao()%></p>
                                                            <% if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.CONCLUIDO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Conclu�do </span></p>
                                                    <% } else if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.CANCELADO){%>
                                                    <p><span class="label label-info"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Cancelado </span></p>
                                                    <% } %>
                                                    <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Data do servi�o: <%=avaliacao.getAgenda().getDataAgendamento()%> �s <%=avaliacao.getAgenda().getHoraAgendamento()%> </p>
                                                    <hr/>
                                                    <p><i class="fa fa-commenting-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Comentario: </b> <%=avaliacao.getComentario()%> </p>
                                                    <p><i class="fa fa-star fa-fw w3-margin-right w3-large w3-text-teal text-yellow"></i><b>Avalia��o Geral: </b> <%=avaliacao.getAvaliacaoGeral()%></p>
                                                    <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal text-green"></i><b>Avalia��o de Custo: </b> <%=avaliacao.getAvaliacaoCusto()%></p>
                                                    <p><i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal text-aqua"></i><b>Avalia��o de Rapidez: </b> <%=avaliacao.getAvaliacaoRapidez()%></p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Fechar</button>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                

                            <%
                                }
                            }
                            %>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">

                        </div>
                    </div>

                        <!--AVALIADOS-->
                    <div class="box">
                        <div class="box-header with-border bg-green">
                            <h3 class="box-title" style="color: white;">Avalia��es Avaliados</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                                    <i class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <% 
                                for (Avaliacao avaliacao : avaliacoes) {
                                if(avaliacao.getStatus() == Status_Avaliacao.AVALIADO){
                            %> 
                            <div class="panel-body">
                                <div class="row-fluid">
                                    <div class="span3">
                                        <div class="row">
                                            <div class="col-md-5">

                                                <p><h2><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getNome()%> <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getSobrenome()%> </h2></p>
                                                <p><small><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Endere�o: </b><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getCidade()%> - <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getEstado()%></small></p> 
                                                <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Data do servi�o: <%=avaliacao.getAgenda().getDataAgendamento()%> �s <%=avaliacao.getAgenda().getHoraAgendamento()%> </p>                                                
                                                <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Descri��o da Solu��o:</b> <br/> <i>&emsp;"<%= avaliacao.getAgenda().getOrcamento().getDescricaoSolucao()%>"</i> </p> 
                                                <p><i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Prazo para conclus�o do Servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPrazo()%></p>
                                                <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Gastos: </b><%=avaliacao.getAgenda().getOrcamento().getGastos()%></p>
                                                <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Pre�o Final: </b><%=avaliacao.getAgenda().getOrcamento().getPrecoFinal()%></p>


                                            </div>
                                            <div class="col-md-5">

                                                <div style="text-align: right" class="text-muted"><b> N� Pedido: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getId()%></div>
                                                <% if (avaliacao.getStatus() == Status_Avaliacao.PENDENTE){%>
                                                <p style="text-align: right"><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Pendente</span></p>
                                                <% } else if (avaliacao.getStatus() == Status_Avaliacao.AVALIADO){%>
                                                <p style="text-align: right"><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Avaliado </span></p>
                                                <% } %>

                                                <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data da Finaliza��o do Servico: </b><%=avaliacao.getAgenda().getDataFinalizacao()%></p>
                                                <p></p>
                                                <p style="text-align: right"><i class="fa fa-commenting-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Comentario: </b> <%=avaliacao.getComentario()%> </p>
                                                <p style="text-align: right"><i class="fa fa-star fa-fw w3-margin-right w3-large w3-text-teal text-yellow"></i><b>Avalia��o Geral: </b> <%=avaliacao.getAvaliacaoGeral()%></p>
                                                <p style="text-align: right"><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal text-green"></i><b>Avalia��o de Custo: </b> <%=avaliacao.getAvaliacaoCusto()%></p>
                                                <p style="text-align: right"><i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal text-aqua"></i><b>Avalia��o de Rapidez: </b> <%=avaliacao.getAvaliacaoRapidez()%></p>


                                            </div>
                                            <div class="col-md-2">

                                                <span class="pull-right">
                                                    <button class="btn btn-primary btn-lg" style="width: 100%" type="button" data-toggle="modal" data-target="#myModal<%=avaliacao.getAgenda().getOrcamento().getId()%>">
                                                        <a href="#" style="color: white"><i class="fa fa-plus-circle img-circle text-default"></i> Ver mais</a>
                                                    </button>
                                                        
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </div>

                            <!-- Modal -->
                            <div class="row slideanim">
                                <div class="col-sm-12">
                                    <!-- Modal -->
                                    <div id="myModal<%=avaliacao.getAgenda().getOrcamento().getId()%>" class="modal fade" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Or�amento recebido</h4>
                                                </div>

                                                <div class="modal-body">
                                                    <h4><i class="fa fa-user-o" aria-hidden="true"></i> | <%=avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getNome()%></span></h4>
                                                    <p><small><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Endere�o: </b><%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getCidade()%> - <%= avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getEndereco().getEstado()%></small></p> 

                                                    <p><hr/></p>
                                                    <p> <h4>Pedido </h4> </p>
                                                    <p style="text-align: right" class="text-muted"><i class="fa fa-clock-o" aria-hidden="true"></i> <b>Data do Pedido:</b> <%=avaliacao.getAgenda().getOrcamento().getPedido().getDataPedido()%></p>
                                                    <% if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.APROVADO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Aprovado </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getPedido().getStatus_Pedido() == Status_Pedido.REPROVADO){%>
                                                    <p><span class="label label-danger"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Reprovado </span></p>
                                                    <% } %>


                                                    <p><b> N� Pedido: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getId()%></p>

                                                    <p><i class="fa fa-calendar-check-o" aria-hidden="true"></i> <b>Dia dispon�vel para receber o servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPedido().getDataDesejada()%></p>
                                                    <p><i class="fa fa-clock-o" aria-hidden="true"></i><b> Hor�rio dispon�vel para receber o servi�o:</b> <%=avaliacao.getAgenda().getOrcamento().getPedido().getHorarioInicialDisponivel()%> �s <%=avaliacao.getAgenda().getOrcamento().getPedido().getHorarioFinalDisponivel()%> </p>
                                                    <p><i class="fa fa-file-text-o" aria-hidden="true"></i><b> Descri��o Problema:</b><br/> <i>"<%=avaliacao.getAgenda().getOrcamento().getPedido().getDescricaoProblema()%>"</i></p>
                                                    <p><hr/></p>

                                                    <p> <h4>Orcamento </h4> </p>
                                                    <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data do Or�amento: </b><%=avaliacao.getAgenda().getOrcamento().getDataOrcamento()%></p>
                                                            <% if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.APROVADO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Aprovado </span></p>
                                                    <% } else if (avaliacao.getAgenda().getOrcamento().getStatus_Orcamento() == Status_Orcamento.REPROVADO){%>
                                                    <p><span class="label label-danger"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Reprovado </span></p>
                                                    <% } %>

                                                    <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data in�cio do Servi�o: </b><%=avaliacao.getAgenda().getDataAgendamento()%> �s <i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <%=avaliacao.getAgenda().getHoraAgendamento()%></p>
                                                    <p><i class="fa fa-file-text-o fa-fw w3-margin-right w3-large w3-text-teal"></i> <b>Descri��o da Solu��o:</b> <br/> <i>"<%= avaliacao.getAgenda().getOrcamento().getDescricaoSolucao()%>"</i> </p> 
                                                    <p><i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Prazo para conclus�o do Servi�o: </b><%=avaliacao.getAgenda().getOrcamento().getPrazo()%></p>
                                                    <p><i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Gastos: </b><%=avaliacao.getAgenda().getOrcamento().getGastos()%></p>
                                                    <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Pre�o Final: </b><%=avaliacao.getAgenda().getOrcamento().getPrecoFinal()%></p>
                                                    <p><hr/></p>

                                                    <p> <h4>Agendamento </h4> </p>
                                                    <p style="text-align: right" class="text-muted"> <i class="fa fa-calendar-check-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Data da Finaliza��o: </b><%=avaliacao.getAgenda().getDataFinalizacao()%></p>
                                                            <% if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.EM_ESPERA){%>
                                                    <p><span class="label label-warning"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Em Espera </span></p>
                                                    <% } else if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.CONCLUIDO){%>
                                                    <p><span class="label label-success"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Conclu�do </span></p>
                                                    <% } else if (avaliacao.getAgenda().getStatus_servico() == Status_Servico.CANCELADO){%>
                                                    <p><span class="label label-info"><i class="fa fa-clock-o text-default" aria-hidden="true"></i> Cancelado </span></p>
                                                    <% } %>
                                                    <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>Data do servi�o: <%=avaliacao.getAgenda().getDataAgendamento()%> �s <%=avaliacao.getAgenda().getHoraAgendamento()%> </p>
                                                    <hr/>
                                                    <p><i class="fa fa-commenting-o fa-fw w3-margin-right w3-large w3-text-teal"></i><b>Comentario: </b> <%=avaliacao.getComentario()%> </p>
                                                    <p><i class="fa fa-star fa-fw w3-margin-right w3-large w3-text-teal text-yellow"></i><b>Avalia��o Geral: </b> <%=avaliacao.getAvaliacaoGeral()%></p>
                                                    <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal text-green"></i><b>Avalia��o de Custo: </b> <%=avaliacao.getAvaliacaoCusto()%></p>
                                                    <p><i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal text-aqua"></i><b>Avalia��o de Rapidez: </b> <%=avaliacao.getAvaliacaoRapidez()%></p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Fechar</button>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                

                            <%
                                }
                            }
                            %>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">

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

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->
    </body>
</html>