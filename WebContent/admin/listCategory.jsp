<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<!--   其中contentType="text/html; charset=UTF-8"的作用是告诉浏览器提交数据的时候，使用UTF-8编码 -->
<!--   在form里method="post" 才能正确提交中文 -->
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>

$(function(){
	
// 	对分类名称和分类图片做了为空判断，当为空的时候，不能提交
// 	其中用到的函数checkEmpty，在adminHeader.jsp 中定义
	$("#addForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;
		if(!checkEmpty("categoryPic","分类上传图片"))
			return false;
		
		return true;
	});
});

</script>

<title>分类管理</title>

<div class="workingArea">
<h1 class="label label-info">分类管理</h1>
<br>
<br>

<div class="listDataTableDiv">
<table class="table table-striped table-bordered table-hover table-condensed">
<thead>
<tr class="success">
<th>ID</th>
<th>图片</th>
<th>分类管理</th>
<th>属性管理</th>
<th>产品管理</th>
<th>编辑</th>
<th>删除</th>
</tr>
</thead>
<tbody>
<!-- 借助JSTL的c:forEach标签遍历从CategoryAction的list() 的传递过来的集合。 -->
<c:forEach items="${categorys}" var="c">

<tr>
<td>${c.id}</td>
<td><img height="40px" src="img/category/${c.id}.jpg"></td>
<td>${c.name}</td>
<td><a href="admin_property_list?category.id=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
<td><a href="admin_product_list?category.id=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
<td><a href="admin_category_edit?category.id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>

<!-- 用于删除的超链，指向地址admin_category_delete,并且会传递当前分类对象的id过去。 -->
<td><a deleteLink="true" href="admin_category_delete?category.id=${c.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>

<div class="pageDiv">
<%@include file="../include/admin/adminPage.jsp" %>
</div>

<!-- 上传图片的页面： -->

<div class="panel panel-warning addDiv">
<div class="panel-heading">新增分类</div>
<div class="panel-body">

<!--  form的action="admin_category_add"，会导致访问CategoryAction的add()方法 -->
<!-- method="post" 用于保证中文的正确提交 -->
<!-- 必须有enctype="multipart/form-data"，这样才能上传文件 -->
<form method="post" id="addForm" action="admin_category_add" enctype="multipart/form-data">
<table class="addTable">
<tr>
<td>分类名称</td>
<td><input id="name" name="category.name" type="text" class="form-control"></td>
</tr>
<tr>
<td>分类圖片</td>
<td><input id="categoryPic" type="file" name="img"/>
</td>
</tr>
<tr class="submitTR">
<td colspan="2" align="center">
<button type="submit" class="btn btn-success">提 交</button>
</td>
</tr>
</table>
</form>
</div>
</div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>