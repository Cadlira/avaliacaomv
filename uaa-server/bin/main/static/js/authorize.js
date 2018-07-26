$( document ).ready(function() {
	var url = new URL(window.location.href);
    $("#sistema").html(url.searchParams.get("client_id"));
});

$("#autorizar").click(function(){
	$("#user_oauth_approval").val(true);
	$("#confirmationForm").submit();
});

$("#negar").click(function(){
	$("#user_oauth_approval").val(false);
	$("#confirmationForm").submit();
});