/** 获取项目IP */
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

function datetoLocal(value){
    var date = new Date(value);
    var year = date.getFullYear();
    var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
    var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
    var hours = (date.getHours()+1) > 9 ? (date.getHours() + 1) : "0" + (date.getHours() + 1);
    var minutes = (date.getMinutes()+1)> 9 ? (date.getMinutes() + 1) : "0" + (date.getMinutes() + 1);
    var seconde = (date.getSeconds()+1)> 9 ? (date.getSeconds() + 1) : "0" + (date.getSeconds() + 1);
    var result = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconde;
    return result;
}