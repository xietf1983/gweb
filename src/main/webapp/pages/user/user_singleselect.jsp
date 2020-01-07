<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="icon" type="image/x-icon" href="img/favicon.ico" />
<link href="../../css/font-awesome.min.css" rel="stylesheet">
<link href="../../css/formeasyui.css" rel="stylesheet">
<link href="../../ui/easyui/themes/gray/easyui.css" rel="stylesheet">
<link href="../../css/easyui-main.css" rel="stylesheet">
<link href="../../css/icon.css" rel="stylesheet">
<script src="../../js/constant.js"></script>
<script src="../../js/util.js"></script>
<script src="../../ui/easyui/jquery.min.js"></script>
<script src="../../ui/easyui/jquery.easyui.min.js"></script>
<script src="../../ui/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body id="mainLayout" style="display: none; width: 100%; height: 100%;"
	class="easyui-layout" data-options="fit:true">
	<div
		data-options="region:'north',split:false,border:true,collapsible:false,title:''"
		id="west" style="position: relative; border-right: #e2e2e2 1px solid;">
		<table class="table-width">
			<tr>
				<td class="t" style="width: 80px;">部门：</td>
				<td class="ts" style="width: 210px;"><input name="parentId"
					id="parentId" class="form-control"
					style="width: 200px; height: 28px" /></td>
				<td class="t" style="width: 80px;">用户名称：</td>
				<td class="ts" style="width: 210px;"><input
					name="userName_search" id="userName_search" class="form-control"
					style="width: 200px; height: 28px" /></td>
				<td><div style="float: left;">
						<a href="#" class="easyui-linkbutton" icon="icon-utils-s-search"
							onclick="dosearch()">查询</a>
					</div></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:false" style="height: 100%">
		<table class="easyui-datagrid" id="userList" style="height: 100%"
			data-options="rownumbers:true,idField: 'userId',fit:true">
			<thead>
				<tr>
					<th data-options="field:'emailaddress',width:100,align:'center'">账号</th>
					<th data-options="field:'userName',width:120,align:'center'">用户名称</th>
					<th
						data-options="field:'organizationFullName',width:280,align:'center'">所属部门</th>
				</tr>
			</thead>
		</table>

	</div>


	<input type="hidden" name="id" id="id" value="0">
</body>
<script>
	$(document).ready(function() {
		$('#mainLayout').css('display', 'block');
	});
	$(function() {
		$('#parentId').combotree({
			url : getRootPath() + '/organization/getOrganizationEntityTreeWithSelect.shtml',
			onLoadSuccess : function(node, data) {
				if ($("#parentId").combotree("getValue") == '') {
					$('#parentId').combotree('setValue', data[0].id);
				}
			}
		});

		userList = $('#userList').datagrid({
			url : getRootPath() + '/user/getUsersEntityList.shtml',
			collapsible : false,
			pagination : true,
			rownumbers : true,
			singleSelect : true,
			checkOnSelect : true,
			selectOnCheck : true,
			border : false,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			//toolbar : '#remaintbuser',
			method : 'post',
			nowrap : true,
			fit : true,
			// toolbar : '#tbuser',
			loadMsg : '数据加载中......',
			fitColumns : false,
			onBeforeLoad : function(param) {
				// param.ORGANIZATIONID = $("#parentId").tree("getValue");
				param.KEYNAME = $('#userName_search').val();
				param.ORGANIZATIONID = $("#parentId").combotree("getValue");

			},
			queryParams : {

			}
		});
		
		$('#mainLayout').layout('resize');

	});
	function dosearch() {
		$("#userList").datagrid("reload");
	}
</script>
</html>