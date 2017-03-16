function login() {
	var username = $("#username").val();
	var password = $("#password").val();
	
	$.post(JsUtils.getRootPath()+'/login/login.do', {
		username : username,
		password : password
	}, function(result) {
		alert(result);
			//document.location.href = JsUtils.getRootPath()+"/admin/adminIndex.do";
	});
}