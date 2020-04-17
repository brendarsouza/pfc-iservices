$("document").ready(function (){
//apos o load da pagina
	carregaSelecaoDeCategoria();
	
	function carregaSelecaoDeCategoria(){
		 $.ajax({
		        // url o recurso no servidor
		        url : "CategoriaProfissaoControle",
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
		        url : "CategoriaProfissaoControle",
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
