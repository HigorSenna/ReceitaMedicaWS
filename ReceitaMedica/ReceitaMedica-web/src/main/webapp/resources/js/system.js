window.i = 0;
$("#botaoAdicionarParagrafo").click(function(){
	i++;
	var id = "id = textAreaConteudo"+i;
	var tagH5 = "<h5 id=paragrafo" + i + "> Paragrafo:"+ i +"</h5>";
	var aberturaTagTextArea = "<textarea class='form-control' rows='20' style='height: 39%; width: 52%;'";
	var fechamentoTagTextArea = "/>";
	
	var textAreaFinal = tagH5 + aberturaTagTextArea + id + fechamentoTagTextArea;
	$("#divCadastroMaterial").append(textAreaFinal);	
});

function prepararInsercao() {
	var textoCompleto ="<p>" + $("#textAreaConteudo").val() + "</p>";
	for(j = 1;j <= window.i;j++){		
		var idCompleto = "#textAreaConteudo"+j;
		textoCompleto += "<p> " +  $(idCompleto).val() + " </p>";
	}
	alert(textoCompleto);	
  if(isCampoValido(textoCompleto)){
	  $("#textoCompleto").val(textoCompleto); 
	  
	  $("#botaoCadastroMaterialHidden").click();
  }
  
}

function removerParagrafo(){
	if(i > 0){
		var idTextoCompleto = "#textAreaConteudo"+(i);
		$(idTextoCompleto).remove();
		$("#paragrafo"+i).remove();
		$("#espacoText"+i).remove();
		window.i = i-1;
	}
}

function isCampoValido(campo){
	if(trim(campo) == "<p></p>"){
		$("#divCadastroMaterial").prepend("" +
				"<div class='ui-messages-error ui-corner-all'>" +
					"<a href='#' class='ui-messages-close' onclick='$(this).parent().slideUp();return false;'><span class='ui-icon ui-icon-close'></span></a>" 
				   +"<span class='ui-messages-error-icon'></span>" 
				   +"<ul>"
				   		+"<li>" 
				   			+"<span class='ui-messages-error-summary'></span>"
				   			+ "<span class='ui-messages-error-detail'>Campo obrigatório: Paragrafo</span>" 
				   		+"</li>" 
				    +"</ul>" 
				 +"</div>");
		return false;
	}
	return true;
}

function trim(str) {
	return str.replace(/^\s+|\s+$/g,"");
}