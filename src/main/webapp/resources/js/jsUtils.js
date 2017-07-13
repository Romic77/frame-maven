JsUtils = {};

//时间戳转换为日期格式
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
};


/* 获取项目路径 */
JsUtils.getRootPath = function(){
	/*var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	return localObj.protocol+"//"+localObj.host+"/"+contextPath;
	//var server_context = basePath;*/
	return webcontext;
};

//datebox时间戳 转换
JsUtils.formatDate=function(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();
    if(localeLanguage=='zh_CN'){
    	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    }else{
    	return (d<10?('0'+d):d)+'-'+(m<10?('0'+m):m)+'-'+y;
    }
} ;


/**
 * 秒转化为时长
 * 例如    300 ------> '00:06:00'
 * @param miao
 * @returns {String}
 */
function convertTime(miao){
	var s = parseInt(miao).toFixed(0);
	var shi = parseInt(s / 3600).toFixed(0);
	s = s % 3600;
	var fen = parseInt(s / 60).toFixed(0);
	s = s % 60;
	return (shi > 9 ? shi : ("0" + shi)) + ":"
			+ (fen > 9 ? fen : ("0" + fen)) + ":"
			+ (s > 9 ? s : ("0" + s));
}


JsUtils.html_encode = function (str)   {   
  var s = "";   
  if (str.length == 0) return "";   
  s = str.replace(/&/g, "&gt;");   
  s = s.replace(/</g, "&lt;");   
  s = s.replace(/>/g, "&gt;");   
  s = s.replace(/ /g, "&nbsp;");   
  s = s.replace(/\'/g, "&#39;");   
  s = s.replace(/\"/g, "&quot;");   
  s = s.replace(/\n/g, "<br>");   
  return s;   
};

JsUtils.html_decode =function (str)   {   
  var s = "";   
  if (str.length == 0) return "";   
  s = str.replace(/&gt;/g, "&");   
  s = s.replace(/&lt;/g, "<");   
  s = s.replace(/&gt;/g, ">");   
  s = s.replace(/&nbsp;/g, " ");   
  s = s.replace(/&#39;/g, "\'");   
  s = s.replace(/&quot;/g, "\"");   
  s = s.replace(/<br>/g, "\n");   
  return s;   
};

/*
 * 说明：此处是调用ajax方法时，判断session是否过期
 * Java代码写在：com.merchant.crm.controller.LoginFilter 中
 *
 * 注：如果不想让ajax方法受这个影响，可以在ajax方法中写： global:false
 * 如下：
 * $.ajax({
 *    url:"test.html",
 *    global:false    //不触发全局ajax事件
 * })
 *
 * **/

$(document).ajaxComplete(function(event, xhr, settings) {
    if(xhr.getResponseHeader("sessionstatus")=="timeOut"){
        if(xhr.getResponseHeader("loginPath")){
            alert("登录过期，请重新登录...");
            window.location.replace(xhr.getResponseHeader("loginPath"));
        }else{
            //alert("请求超时请重新登陆 !");
        }
    }
});