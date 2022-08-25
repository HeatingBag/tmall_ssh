<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<script>
	// 	表示被 disabled 的超链点击之后没有反应
	$(function() {
		$("ul.pagination li.disabled a").click(function() {
			return false;
		});
	});
</script>

<%-- &laquo;为«，首先${page.param}为字符串的拼接，page.param为鼠标放在分页上显示的page.start=5信息  --%>
<%-- 所以<c:if test="${!page.hasPreviouse}是和isHasPreviouse()关联，判断是否有前一页面，然后决定是否禁用(disabled)  --%>
<!-- 当page.hasPreviouse为false的时候，为首页连接套用Bootstrap样式 disabled  -->
<!-- 首页必然是page.start=0  -->
<nav>
	<ul class="pagination">
		<li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>><a
			href="?page.start=0${page.param}" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>

		<li <c:if test="${!page.hasPreviouse}"> class="disabled" </c:if>>
			<a href="?page.start=${page.start-page.count}${page.param}"
			aria-label="Previous"> <span aria-hidden="true">&lsaquo;</span>
		</a>
		</li>

		<c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
			<c:if
				test="${status.count*page.count-page.start<=20&&status.count*page.count-page.start>=-10}">
				<li
					<c:if test="${status.index*page.count==page.start}">class="disabled" </c:if>>
					<a href="?page.start=${status.index*page.count}${page.param}"
					<c:if test="${status.index*page.count==page.start}">class="current"</c:if>>${status.count}</a>
				</li>
			</c:if>
		</c:forEach>

		<li <c:if test="${!page.hasNext}"> class="disabled" </c:if>><a
			href="?page.start=${page.start+page.count}${page.param}"
			aria-label="Next"> <span aria-hidden="true">&rsaquo;</span>
		</a></li>

		<li <c:if test="${!page.hasNext}"> class="disabled" </c:if>><a
			href="?page.start=${page.last}${page.param}" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
