<%-- 
    Document   : EditarProfissional
    Created on : 05/10/2016, 18:21:17
    Author     : brenda
--%>


<%@page import="Model.Usuario"%>
<%@page import="Model.Endereco"%>
<%@page import="Model.Profissao"%>
<%@page import="Model.Profissional"%>
<%@page import="Model.Sexo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Editar Profissional</title>
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
        <script type="text/javascript" src="js/buscaCategorias_Profissoes.js"></script>

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <script language=javascript>

            function formataCPF(src, mask)
            {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i);
                if (texto.substring(0, 1) !== saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }

            function formataRG(src, mask)
            {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i);
                if (texto.substring(0, 1) !== saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }

            function formataTelefone(src, mask)
            {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i);
                if (texto.substring(0, 1) !== saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }
            function formataCelular(src, mask)
            {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i);
                if (texto.substring(0, 1) !== saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }

            function VALIDA() {

                if (isCPF(document.form1.txtCPF.value, 1)) {
                    alert('CPF VALIDO !');
                } else {
                    alert('CPF INVALIDO ! POR FAVOR DIGITE NOVAMENTE !');
                }


            }

        </script>
        <script type="text/javascript">
            jQuery(function ($) {
                $("#cep").change(function () {
                    var cep_code = $(this).val();
                    if (cep_code.length <= 0)
                        return;
                    $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", {code: cep_code},
                            function (result) {
                                if (result.status != 1) {
                                    alert(result.message || "Houve um erro desconhecido");
                                    return;
                                }
                                $("input#cep").val(result.code);
                                $("input#estado").val(result.state);
                                $("input#cidade").val(result.city);
                                $("input#bairro").val(result.district);
                                $("input#endereco").val(result.address);
                                $("input#estado").val(result.state);

                            });
                });
            });
        </script>
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
//            Profissional profissional = (Profissional) session.getAttribute("profissional");  
            Profissional profissional = (Profissional) request.getAttribute("profissional");
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

                                        <p> 
                                            <%=profissional.getNome()%> <%=profissional.getSobrenome()%>
                                            <small><%=profissional.getUsuario().getEmail()%></small> 
                                        </p>
                                    </li>
                                    <!-- Menu Body -->

                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="./editarProfissional?idEditar=<%= profissional.getId()%>" class="btn btn-default btn-flat"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Editar Perfil</a>
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
                            <small><%=profissional.getUsuario().getEmail()%></small>
                        </div>
                    </div>
                        <center><form action="./areaProfissional" method="post"> 
                            <input type="text" name="txtIdProfissional" value="<%=profissional.getId()%>" hidden> 
                            <button type="submit" name="AreaProfissional" class="btn btn-md btn-default" style="width: 100%;"><i class="fa fa-home fa-lg"></i></button>
                        </form></center>
                        <ul class="sidebar-menu" data-widget="tree">
                        <br/>
                        
                        <li class="header"><h5>Média de Avaliações</h5></li>
                        <li><a href="#"><i class="fa fa-star text-yellow"></i> <span><%=profissional.getMediaAvaliacaoGeral()%></span></a></li>
                        <li><a href="#"><i class="fa fa-usd text-green"></i> <span><%=profissional.getMediaAvaliacaoCusto()%></span></a></li>
                        <li><a href="#"><i class="fa fa-clock-o text-aqua"></i> <span><%=profissional.getMediaAvaliacaoRapidez()%></span></a></li>
                    </ul>
                    
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
                            <a href="#"><i class="fa fa-usd text-orange"></i> <span>Orçamentos</span>
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
                                <li><a href="./consultarAgendaConcluidaProfissional">Agendamentos Concluídos</a></li>
                                <li><a href="./consultarAgendaCanceladaProfissional">Agendamentos Cancelados</a></li>

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-star text-purple"></i> <span>Avaliações</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">

                                <li><a href="./buscarTodasAvaliacoesProfissional">Todas Avaliações</a></li>
                                <li><a href="./buscarAvaliacoesPendentesProfissional">Avaliações Pendentes</a></li>
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
                        Editar Perfil
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="./index.jsp"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Editar Perfil</li>
                    </ol>
                </section>



                <section class="content">

                    <form action="./editarProfissional" method="post">
                        <div class="container-fluid">
                            <h5>Dados da Conta (Usuario)</h5>
                            <div class="form-group">
                                <input type="name" hidden name="txtIdUsuario" value="<%= profissional.getUsuario().getId()%>">
                                <input type="name" hidden name="txtIdEndereco" value="<%= profissional.getEndereco().getId()%>">
                                <input type="name" hidden name="txtIdProfissao" value="<%= profissional.getProfissao().getId()%>">
                                <input type="name" hidden name="txtIdCategoria" value="<%= profissional.getProfissao().getCategoria().getId()%>">
                                <input type="name" hidden name="txtIdProfissional" value="<%= profissional.getId()%>">
                            </div>

                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome">Nome: </label>
                                    <input type="name" class="form-control" id="nome" name="txtNome"  value="<%= profissional.getNome()%>">
                                </div>
                                <div class="col-sm-6">
                                    <label>Sobrenome: </label>
                                    <input type="name" class="form-control" id="sobrenome" name="txtSobrenome" value="<%= profissional.getSobrenome()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="nome">Data de Nascimento:</label>
                                    <input type="date" class="form-control" id="dataNascimento" name="txtDataNascimento" value="<%= profissional.getDataNascimento()%>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="telefone">Telefone:</label>
                                    <input type="text" class="form-control" id="email" maxlength=10 name="txtTelefone" value="<%= profissional.getTelefone()%>">
                                </div>
                                <div class="col-sm-4">
                                    <label for="nome">Celular:</label>
                                    <input type="name" class="form-control" id="celular" maxlength=11 name="txtCelular" value="<%= profissional.getCelular()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome">Email:</label>
                                    <input type="email" class="form-control" id="email" name="txtEmail" value="<%= profissional.getUsuario().getEmail()%>">
                                    <input type="password" hidden id="senha" name="txtSenha" value="<%= profissional.getUsuario().getSenha()%>">
                                </div>      
                                <%
                                    if (profissional.getSexo() == Sexo.FEMININO) {
                                %>
                                <div class="col-sm-6" >
                                    <br>
                                    <label for="nome">Sexo:</label>
                                    <select name="txtSexo" id="sexo" class="btn btn-primary dropdown-toggle" required>
                                        <option value="">Selecione</option>
                                        <option value="FEMININO" selected="selected" label="Feminino">
                                            Feminino
                                        </option>
                                        <option value="MASCULINO" label="Masculino">
                                            Masculino
                                        </option>
                                    </select> 
                                </div>
                                <%
                                } else {
                                %>
                                <div class="col-sm-6" >
                                    <br>
                                    <label for="nome">Sexo:</label>
                                    <select name="txtSexo" id="sexo" class="btn btn-primary dropdown-toggle" required>
                                        <option value="">Selecione</option>
                                        <option value="FEMININO" label="Feminino">
                                            Feminino
                                        </option>
                                        <option value="MASCULINO" selected="selected" label="Masculino">
                                            Masculino
                                        </option>
                                    </select> 
                                </div>
                                <%
                                    }
                                %>
                            </div>
                            <%
                                if (profissional.getCpf() == null) {
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome">Razão Social: </label>
                                    <input type="name" class="form-control" id="nomeFasia" name="txtRazaoSocial" value=<%= profissional.getRazaoSocial()%>>
                                </div> 
                                <div class="col-sm-6">
                                    <label for="nome">CNPJ:</label>
                                    <input type="name" class="form-control" id="cnpj" name="txtCNPJ" value=<%= profissional.getCnpj()%>>
                                </div>
                            </div>

                            <%
                            } else {
                            %>

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form name="form1">
                                                <label for="nome">CPF:</label>
                                                <input type="text" class="form-control" id="txtCPF" name="txtCPF" maxlength=14  value="<%= profissional.getRg()%>" onKeyPress="formataCPF(this, '###.###.###-##')"></b>
                                            </form>
                                        </div>
                                    </div>
                                </div> 
                                <div class="col-sm-6">
                                    <label for="nome">RG:</label>
                                    <input type="name" class="form-control" id="rg" name="txtRG" maxlength=9 value="<%= profissional.getRg()%>">
                                </div>
                            </div>

                            <% }%> 

                            <hr> 
                            <div class="row">
                                <div class="col-sm-6">

                                    
                                    <label for="fotoPerfil">Foto de Perfil:</label>
                                    <p></p>
                                    <label for="fotoPerfil"><%=profissional.getUsuario().getFotoPerfil()%></label>
                                    <input type="file" name="txtFotoPerfil" id="fotoPerfil" value="<%=profissional.getUsuario().getFotoPerfil()%>">
                                    <%// }%>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <div class="form-group">
                                        <label for=""><b style="color:red">*</b> Categoria</label>
                                        <select class="form-control" name="txtCategoria" id="selecao_categoria">
                                            <option> -- Selecione --</option>
                                        </select>

                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for=""><b style="color:red">*</b> Profissão</label>
                                        <select name="txtProfissao" class="form-control"id="selecao_profissao">
                                            <option> -- Selecione --</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome">Formação</label>
                                    <input type="text" class="form-control" id="formacao" name="txtFormacao" value="<%= profissional.getFormacao()%>">
                                </div>
                                <div class="col-sm-6">
                                    <label for="nome">Instituição:</label>
                                    <input type="name" class="form-control" id="instituicao" name="txtInstituicao" value="<%= profissional.getInstituicao()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label for="nome">Descrição de Serviços:</label>
                                    <input type="name" class="form-control" id="descricaoServicos" name="txtDescricaoServicos" value="<%= profissional.getDescricaoServicos()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-3">                       
                                    <label for="nome">Horário de Atendimento:</label>
                                </div>
                                <div class="form-group col-sm-2">
                                    <input type="time" class="ss-q-time form-control required" dir="auto" width="20px" id="horarioDeAtendimento" required="" name="txtHorarioDeAtendimentoInicio" value="<%= profissional.getHorarioDeAtendimentoInicio()%>">
                                </div>

                                <div class="form-group col-sm-1">                       
                                    <label for="nome">às:</label>
                                </div>
                                <div class="form-group col-sm-2">
                                    <input type="time" class="ss-q-time form-control required" dir="auto" id="horarioDeAtendimento" required="" name="txtHorarioDeAtendimentoFim"value="<%= profissional.getHorarioDeAtendimentoFim()%>">
                                </div>

                            </div>  


                            <hr>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label for="nome">CEP</label>
                                    <input type="name" class="form-control" id="CEP" maxlength=8 name="txtCEP" value="<%= profissional.getEndereco().getCep()%>">
                                </div>
                                <div class="col-sm-6">
                                    <label forl="nome">Rua</label>
                                    <input type="name" class="form-control" id="Rua" name="txtRua"  value="<%= profissional.getEndereco().getRua()%>">
                                </div>
                                <div class="col-sm-1">
                                    <label for="nome">Numero</label>
                                    <input type="name" class="form-control" id="Numero" name="txtNumero" value="<%= profissional.getEndereco().getNumero()%>">
                                </div>
                                <div class="col-sm-3">
                                    <label for="nome">Complemento</label>
                                    <input type="name" class="form-control" id="Complemento" name="txtComplemento"  value="<%= profissional.getEndereco().getComplemento()%>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <label for="nome">Bairro</label>
                                    <input type="name" class="form-control" id="Bairro" name="txtBairro" value="<%= profissional.getEndereco().getBairro()%>">
                                </div>
                                <div class="col-sm-3">
                                    <label for="nome">Cidade</label>
                                    <input type="name" class="form-control" id="Cidade" name="txtCidade" value="<%= profissional.getEndereco().getCidade()%>">
                                </div>
                                <div class="col-sm-3">
                                    <label for="nome">Estado</label>
                                    <input type="name" class="form-control" id="Estado" name="txtEstado" value="<%= profissional.getEndereco().getEstado()%>">
                                </div>
                                <div class="col-sm-3">
                                    <label for="nome">País</label>
                                    <input type="name" class="form-control" id="Pais" name="txtPais" value="<%= profissional.getEndereco().getPais()%>">
                                </div>
                            </div>

                            <br><div class="row">

                                <div class="col-sm-12">
                                    <input  type="submit" class="btn btn-success center-block"  id="btnSalvar"  value="Salvar">                        
                                </div>
                            </div>
                        </div>

                        </center><hr> 
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
                        <h3 class="control-sidebar-heading">Configurações</h3>
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
        --%>
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