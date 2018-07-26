$( document ).ready(function() {
	//alterando o referrer para não causar problemas no redirecionamento na volta do registro
	Object.defineProperty(document, "referrer", {get : function(){ return ""; }});
	console.log(window.location.search);
	if (window.location.search && window.location.search.indexOf("error") != -1) {
		showError('Usuário e/ou senha inválido(s).');
	}
	if (window.location.href && window.location.href.indexOf("login") == -1) {
		showSuccess('Usuario logado com sucesso.');
	}
	if (window.location.search && window.location.search.indexOf("registred") != -1) {
		showSuccess('Usuário cadastrado com sucesso.');
	}
	if (GetURLParameter("error")) {
		console.log("error");
	}
	
});


function showError(msg){
	hideSuccess();
	$("#errorDiv").text(msg);
	$("#errorDiv").removeClass("hide");
}

function hideError(){
	$("#errorDiv").text("");
	$("#errorDiv").addClass("hide");
}

function showSuccess(msg){
	hideError();
	$("#successDiv").text(msg);
	$("#successDiv").removeClass("hide");
}

function hideSuccess(){
	$("#successDiv").text("");
	$("#successDiv").addClass("hide");
}

$("#username").keypress(function(){
	$("#errorDiv").addClass("hide");
});
$("#username").keypress(function(){
	$("#errorDiv").addClass("hide");
});