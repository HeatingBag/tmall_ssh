<!-- 表示本页面会使用html5的技术 -->
<!DOCTYPE html>

<!--  jsp指令:contentType="text/html; charset=UTF-8" 告诉浏览器使用UTF-8进行中文编码识别 -->
<!--  pageEncoding="UTF-8" 本jsp上的中文文字，使用UTF-8进行编码 -->
<!--  isELIgnored="false" 本jsp上会使用EL表达式 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<!--     引入JSTL 使用c(核心)和fmt(格式化)两种标准标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<html>
  
<head>

<!-- 引入js和css -->
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/back/style.css" rel="stylesheet">


<script>

// 预先定义一些判断输入框的函数，方便后面使用，分别是不能为空，必须是数字和必须是整数
function checkEmpty(id,name){
	var value=$("#"+id).val();
	if(value.length==0){
		alert(name+"不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	
	return true;
}

function checkNumber(id,name){
	var value=$("#"+id).val();
	if(value.length==0){
		alert(name+"不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(isNaN(value)){
		alert(name+"必须是数字");
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}

function checkInt(id,name){
		var value=$("#"+id).val();
	if(value.length==0){
		alert(name+"不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(parseInt(value)!=value){
		alert(name+"必须是整数");
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}

//对于删除超链，都需要进行确认操作
$(function(){
	$("a").click(function(){
		var deleteLink = $(this).attr("deleteLink");
		console.log(deleteLink);
		if("true"==deleteLink){
			var confirmDelete = confirm("确认要删除");
			if(confirmDelete)
				return true;
			return false;
		}
	});
})


</script>
</head>
<body>