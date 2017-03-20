function login() {
	var username = $("#username").val();
	var password = $("#password").val();
	
	$.post(JsUtils.getRootPath()+'/login/login.do', {
		username : username,
		password : password
	}, function(result) {
		if(result.status==0){
			swal("错误提示", result.msg, "error");
		}else{
			document.location.href = JsUtils.getRootPath()+"/login/index.do"
		}
	});
}