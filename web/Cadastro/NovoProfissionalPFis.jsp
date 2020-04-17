<%-- 
    Document   : AreaCliente
    Created on : 29/09/2017, 17:46:40
    Author     : brenda
--%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Profissional"%>
<!DOCTYPE html>
<html>
    <head>
        <%        Usuario usuario = new Usuario();
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>ISERVICES - Cadastro Profissional</title>
        <link rel="icon" type="image/png" href="imagens/logo2.png"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="../admtle/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../admtle/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../admtle/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../admtle/AdminLTE.min.css">
        <link rel="stylesheet" href="../admtle/skin-yellow.css">
        <link rel="stylesheet" href="../admtle/skin-yellow.min.css">
        <link rel="stylesheet" href="../admtle/skin-yellow-light.min.css">
        <script src="../admtle/jquery.min.js"></script>
        <script src="../admtle/bootstrap.min.js"></script>
        <script src="../js/jquery-3.2.1.js"></script>
        

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <script>
        // Script to open and close sidenav
        function w3_open() {
            document.getElementById("mySidenav").style.display = "block";
        }
        function w3_close() {
            document.getElementById("mySidenav").style.display = "none";
        }
    </script>
    <script language=javascript>
        $("document").ready(function (){
//apos o load da pagina
	carregaSelecaoDeCategoria();
	
	function carregaSelecaoDeCategoria(){
		 $.ajax({
		        // url o recurso no servidor
		        url : "../CategoriaProfissaoControle",
		        type: 'GET',
		        // tipo de retorno
		        dataType : "json",
		        //parametros da requisição
		        data: {
		            acao:'listaCategoriasParaSelecao'
		        },
		        
		         // função para tratar o retorno
		        success : function(json){
		        
		        console.log(json);
		         
		        // preenche as options
		            for(i=0; i < json.length; i++){
		             
		  var option= "<option value="+ json[i].id +">" + json[i].categoriaNome +"</option>";
		                
		                $("#selecao_categoria").append(option);
		                
		            }
		 
		     
		        },
		        
		        error: function( xhr, status, errorThrown ) {
		            alert( "Ops!, Aconteu um erro na requisição!" );
		            console.log( "Error: " + errorThrown );
		            console.log( "Status: " + status );
		          
		        },
		            complete: function( xhr, status ) {
		            console.log( "The request is complete!" );
		        }

		    });//fim o ajax
		   
	}
	
	
	
	//quando a seleção categoria for alterada	
	$("#selecao_categoria").change(function(){	
		//recupera o valor(id) de categoria
		var idcategoria = $("#selecao_categoria").val();	
		
		 $.ajax({
		        // url o recurso no servidor
		        url : "../CategoriaProfissaoControle",
		        type: 'GET',
		        // tipo de retorno
		        dataType : "json",
		        //parametros da requisição
		        data: {
		            acao:'listaProfissaoParaCategoria',
		            categoria: idcategoria
		        },
		        
		         // função para tratar o retorno
		        success : function(json){		        
		        //lista os options 
		        $("#selecao_profissao").empty();
		        
		        $("#selecao_profissao").append("<option> -- Selecione -- </option>");
		        	        
		        // preenche as options
		            for(i=0; i < json.length; i++){
		             
                        var option= "<option value="+ json[i].id +">" + json[i].profissaoNome +"</option>";
		                
		                $("#selecao_profissao").append(option);
		                
		            }
		        },
		        
		        error: function( xhr, status, errorThrown ) {
		            alert( "Ops!, Aconteu um erro na requisição!" );
		            console.log( "Error: " + errorThrown );
		            console.log( "Status: " + status );
		          
		        },
		            complete: function( xhr, status ) {
		            console.log( "The request is complete!" );
		        }

		    });//fim o ajax

	});
	
});


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

        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="../index.jsp" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b><img src="../imagens/logo2.png" style="height:40px; width: 40px;" class="img-thumbnail" alt="ISERVICES"></b></span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b><img src="../imagens/icone2.png" style="height:48px; width: 170px; align-self: center;"class="img-lg" alt="ISERVICES"></b></span>
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
                            <li>
                                <a href="../LoginProfissional/LoginProfissional.jsp"><i class="fa fa-briefcase"> </i>  Área do Profissional</a>
                            </li>
                            <li>
                                <a href="../cadastro.jsp"><i class="fa fa-user-plus"></i> Cadastre-se</a>
                            </li>
                            <li>
                                <a href="../LoginCliente/LoginCliente.jsp"><i class="fa fa-sign-in"></i> Entrar</a>
                            </li>
                            <!-- User Account Menu -->

                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->


                    <!-- /.search form -->


                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <br/>

                        <li>
                            <a href="../LoginProfissional/LoginProfissional.jsp">
                                <i class="fa fa-briefcase"></i> <span>Área do Profissional</span>
                                <span class="pull-right-container">

                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="../cadastro.jsp">
                                <i class="fa fa-user-plus"></i> <span>Cadastre-se</span>
                                <span class="pull-right-container">

                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="../LoginCliente/LoginCliente.jsp">
                                <i class="fa fa-sign-in"></i> <span>Entrar</span>
                                <span class="pull-right-container">

                                </span>
                            </a>
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
                        Cadastrar
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> ISERVICES</a></li>
                        <li class="active">Cadastrar</li>
                    </ol>
                </section>
                <section class="content">

                    <form action="../cadastrarProfissional" method="post" > 
                        <div class="container-fluid">
                            <br/>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome"><b style="color:red">*</b> Email:</label>
                                    <input type="email" class="form-control" id="email" name="txtEmail" placeholder="seuemail@email.com" required>
                                </div>
                                <div class="col-sm-6">
                                    <label for="pwd"><b style="color:red">*</b> Senha:</label>
                                    <input type="password" class="form-control" id="senha" minlength="6" maxlength="18" name="txtSenha" placeholder="Senha" required>
                                </div> 

                            </div>

                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="nome"><b style="color:red">*</b> Nome: </label>
                                    <input type="name" class="form-control" id="nome" name="txtNome" placeholder="Nome" required>
                                </div>
                                <div class="col-sm-6">
                                    <label><b style="color:red">*</b> Sobrenome: </label>
                                    <input type="name" class="form-control" id="sobrenome" name="txtSobrenome" placeholder="Sobrenome" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label for="nome"><b style="color:red">*</b> Data de Nascimento:</label>
                                    <input type="date" class="form-control" id="dataNascimento" name="txtDataNascimento" required>
                                </div>
                                <div class="col-sm-4">
                                    <label for="telefone"><b style="color:red">*</b> Telefone:</label>
                                    <input type="text" class="form-control" id="telefone" name="txtTelefone" maxlength=12 placeholder="Ex: 00-0000-0000" onKeyPress="formataTelefone(this, '##-####-####')" required>

                                </div>
                                <div class="col-sm-4">
                                    <label for="nome">Celular:</label>
                                    <input type="name" class="form-control" id="celular" name="txtCelular" maxlength=13  placeholder="Ex: 00-00000-0000" onKeyPress="formataCelular(this, '##-#####-####')">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form name="form1">
                                                <label for="nome"><b style="color:red">*</b> CPF:</label>
                                                <input type="text" class="form-control" id="txtCPF" name="txtCPF" maxlength="14" maxlength="13" placeholder="Ex: 000.000.000-00" onKeyPress="formataCPF(this, '###.###.###-##')" required></b>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <label for="nome"><b style="color:red">*</b> RG:</label>
                                    <input type="name" class="form-control" id="rg" name="txtRG" maxlength="13" minlength="13" placeholder="Ex: 00.000.000-0"  onKeyPress="formataRG(this, '##.###.###-##')" required>
                                </div>
                                <div class="col-sm-4" >
                                    <br>
                                    <label for="nome"><b style="color:red">*</b> Sexo:</label>
                                    <select name="txtSexo" id="sexo" class="btn btn-primary dropdown-toggle" required>
                                        <option value="">Selecione</option>
                                        <option value="FEMININO" label="Feminino">
                                            Feminino
                                        </option>
                                        <option value="MASCULINO" label="Masculino">
                                            Masculino
                                        </option>
                                    </select> 
                                </div>
                            </div>
                            
                            <hr> 
                            <p>Endereço</p>
                            <div class="row"> 
                                <div class="col-sm-4">
                                    <label for="nome"><b style="color:red">*</b> CEP</label> <input type="text" name="txtCEP" class="form-control" max="8" id="cep" required/>  
                                </div>
                                <div class="col-sm-2">
                                    <br/>
                                    <input  type="button" class="btn btn-warning btn-md" id="btnBuscar" value="Buscar">   
                                </div>
                                <div class="col-sm-6">

                                    <label forl="nome"><b style="color:red">*</b> Endereço</label>
                                    <input type="text" class="form-control" name="txtRua" id="endereco" required /> 
                                </div>
                            </div>
                            <div class="row"><br/>                                                          
                                <div class="col-sm-2">
                                    <label for="nome"><a style="color: #000;" data-toggle="tooltip" title="Preencha com S/N se sua residência não possui número."><b style="color:red">*</b> Número </a></label> <input type="text" name="txtNumero" class="form-control" id="numero" required/>
                                </div>
                                <div class="col-sm-4">
                                    <label for="nome">Complemento</label> <input type="text" name="txtComplemento" class="form-control" id="complemento"/>
                                </div>
                                <div class="col-sm-6">
                                    <label forl="nome"><b style="color:red">*</b> Bairro</label> <input type="text" name="txtBairro" class="form-control" id="bairro" required/>
                                </div>
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-6">
                                    <label forl="nome"><b style="color:red">*</b> Cidade</label> <input type="text" name="txtCidade" class="form-control" id="cidade" required />
                                </div>
                                <div class="col-sm-4">
                                    <label forl="nome"><b style="color:red">*</b> Estado</label> <input type="text" name="txtEstado" class="form-control" id="estado" required/>
                                </div>
                                <div class="col-sm-2">           
                                    <label forl="nome">País</label> <input type="text" name="txtPais" class="form-control" id="rg" name="txtPais" readonly="true" value="Brasil"/>
                                </div>
                            </div>
                            <br/>

                            <hr>
                            <p>Dados Profissionais</p>
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
                                    <input type="text" class="form-control" id="formacao" name="txtFormacao" placeholder="Formacao">
                                </div>
                                <div class="col-sm-6">
                                    <label for="nome">Instituição:</label>
                                    <input type="name" class="form-control" id="instituicao" name="txtInstituicao" placeholder="Instituição">
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label for="nome"><b style="color:red">*</b> Descrição de Serviços:</label>
                                    <textarea class="form-control" rows="3" id="descricaoServicos" name="txtDescricaoServicos" placeholder="Descrição de Serviços" required></textarea>
                                </div>
                                <div class="col-sm-6">
                                    <label><a style="color: #000;" data-toggle="tooltip" title="Horário em que você está disponível para atendimento!"><i class="fa fa-info-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><b style="color:red">*</b> Horário de Atendimento:</a></label>
                                    <!--<label>Date:</label>-->
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-clock-o"></i>
                                                </div>
                                                <input type="time" class="ss-q-time form-control pull-left required" dir="auto" id="txtHorarioDeAtendimentoInicio" required=""  name="txtHorarioDeAtendimentoInicio"> 
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <h5>até</h5>
                                        </div>
                                        <!-- /.col -->
                                        <div class="col-sm-5">
                                            <div class="input-group">

                                                <div class="input-group-addon">
                                                    <i class="fa fa-clock-o"></i>
                                                </div>
                                                <input type="time" class="ss-q-time form-control pull-left required" dir="auto" id="txtHorarioDeAtendimentoFim" required=""  name="txtHorarioDeAtendimentoFim"> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/><hr>
                            <p><label for="nome"> Região de Atendimento:</label>

                                <a href="#" title="" data-toggle="popover" data-trigger="hover" data-content="Selecione a região que você atende."><i class="fa fa-info-circle"></i></a></p>
                            <script>
                                $(document).ready(function () {
                                    $('[data-toggle="popover"]').popover();
                                });
                            </script>
                            <div class="row">
                                <div class="col-sm-6"><br/>
                                    <label for="nome"><b style="color:red">*</b> Estado de Atendimento:</label>
                                    <br/>
                                    <input type="text" name="txtEstadoAtendimento" class="form-control" id="estado" required/>

                                </div>
                                <div class="col-sm-6"><br/>
                                    <label for="nome"><b style="color:red">*</b> Cidade de Atendimento:</label>
                                    <br/>
                                    <input type="text" name="txtCidadeAtendimento" class="form-control" id="cidade" required/>
                                </div>

                            </div>
                            <hr>
                            <div class="row">
                                <br/>
                                <div class="col-sm-4">

                                </div>
                                <div class="col-sm-4">

                                </div>
                                <div class="col-sm-4">
                                    <input  type="submit" class="btn btn-success center-block" id="btnSalvar"  value="Salvar">                        
                                </div>
                            </div>
                            <br/>
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


            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
            immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="../admtle/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../admtle/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../admtle/adminlte.min.js"></script>

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->
    </body>
</html>