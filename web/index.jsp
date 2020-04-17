<%-- 
    Document   : index
    Created on : 27/06/2017, 10:26:33
    Author     : brenda
--%>

<%@page import="java.util.List"%>

<%@page import="Model.Profissional"%>

<%-- 
    Document   : AreaCliente
    Created on : 29/09/2017, 17:46:40
    Author     : brenda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - HOME</title>
        <link rel="icon" type="image/png" href="imagens/logo2.png"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        <link rel="stylesheet" href="admtle/dataTables.bootstrap.min.css">
        <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="js/buscaCategorias_Profissoes.js"></script>
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
    <body class="hold-transition skin-yellow-light sidebar-collapse">

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
                    <!--Sidebar toggle button-->

                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->

                            <li>
                                <a href="LoginProfissional/LoginProfissional.jsp"><i class="fa fa-briefcase"> </i>  Área do Profissional</a>
                            </li>
                            <li>
                                <a href="cadastro.jsp"><i class="fa fa-user-plus"></i> Cadastre-se</a>
                            </li>
                            <li>
                                <a href="LoginCliente/LoginCliente.jsp"><i class="fa fa-sign-in"></i> Entrar</a>
                            </li>
                            <!-- User Account Menu -->

                            <!-- Control Sidebar Toggle Button -->

                        </ul>
                    </div>
                </nav>
            </header>
            <style>
                h1{

                    color:white;
                    text-shadow: 0.05em 0.05em 0.01em #333;
                    font-family: sans-serif;
                    font-style: oblique;
                    text-align: center;
                    font-stretch: ultra-expanded;
                    font-size: 45px;

                }
            </style>

            <!-- Content Wrapper. Contains page content -->
            <!--            <div class="content-wrapper" style="background-image: url('imagens/ISERVICES3.png');">-->
            <div class="content-wrapper" style="background-image:url('imagens/ISERVICESlogo.png');background-attachment: fixed;background-position: center center; background-repeat: no-repeat;">
                <br/>
                <br/><br/>
                <h1>ENCONTRE O PROFISSIONAL QUE VOCÊ PRECISA!</h1>
                <br/><br/>
                <br/>
                <section class="content">
                    <!--style="background-image:url('imagens/ISERVICES3.png'); background-size: center; background-position: initial; background-repeat: no-repeat;">-->
                    <div class="row">
                        <!-- left column -->
                        <div class="col-md-2 text-center">
                        </div>
                        <div class="col-md-8 text-center">
                            <!-- general form elements -->
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Buscar Profissionais</h3>
                                </div>
                                <form role="form" action="./consultarProfissionais" method="get">

                                    <div class="row">
                                        <div class="box-body">
                                            <!-- left column -->
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="">Categoria à contratar:</label>
                                                    <select class="form-control" id="selecao_categoria" required="">
                                                        <option> -- Selecione --</option>
                                                    </select>

                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="">Profissão à contratar:</label>
                                                    <select name="txtProfissao" class="form-control"id="selecao_profissao" required="">
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
                    </div>

                    <%

                        //filtra pesquisa de profissional
                        String profissaoPesquisada = (request.getParameter("txtProfissao"));
                        // se for diferente de NULO ira mostrar os profissionais cadastrados
                        if (profissaoPesquisada != null) {
                            List< Profissional> profissionais = (List<Profissional>) request.getAttribute("listaPessoas");
                    %>  
                    <% for (Profissional profissional : profissionais) {%>
                    <div class="row">
                        <div class="col-xs-2">
                        </div>

                        <div class="col-xs-8">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title"> <%=profissional.getProfissao().getProfissaoNome()%> </h3>
                                    <hr/>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body bg-white">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="box-body">
                                                <div class="row">
                                                    <div class="col-sm-2">
                                                        <p style="text-align: center;"><img src="imagens/user-login.png" class="img-circle" height="50%" width="50%" alt="Foto Perfil"></p>
                                                    </div>

                                                    <div class="col-sm-8">
                                                        <h2><%= profissional.getNome()%></h2>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <p><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i> <%=profissional.getCidadeAtendimento()%> - <%=profissional.getEstadoAtendimento()%> </p>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <p> <i class="fa fa-clock-o fa-fw w3-margin-right w3-large w3-text-teal"></i> Atendimento: <%= profissional.getHorarioDeAtendimentoInicio()%> às <%= profissional.getHorarioDeAtendimentoFim()%></p>
                                                            </div>
                                                        </div>

                                                        <p style="font-style: italic;"> <%= profissional.getDescricaoServicos()%></p>
                                                        <div class="row">
                                                            <p style="text-align: center; font-size: 14px;"><b>Média de Avaliações</b></p>
                                                            <div class="col-xs-4 text-center">
                                                                <a style="color: #000;"><i class="fa fa-star text-yellow"></i> <%=profissional.getMediaAvaliacaoGeral()%></a>
                                                            </div>
                                                            <div class="col-xs-4 text-center">
                                                                <a style="color: #000;"><i class="fa fa-usd text-green"></i> <%=profissional.getMediaAvaliacaoCusto()%></a>
                                                            </div>
                                                            <div class="col-xs-4 text-center">
                                                                <a style="color: #000;"><i class="fa fa-clock-o text-aqua"></i> <%=profissional.getMediaAvaliacaoRapidez()%></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <div class="row">

                                                            <!-- small box -->
                                                            <div class="small-box bg-green">
                                                                <a href="./LoginCliente/RequisitarLoginCliente.jsp" class="small-box-footer">
                                                                    <div class="inner">
                                                                        <br/>
                                                                        <h3>Solicitar</h3>

                                                                        <br/>

                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <!-- /.box-body -->
                            </div>
                        </div>
                        <div class="col-xs-2">
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
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