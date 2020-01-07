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
<style type="text/css">
</style>
<script src="../../js/constant .js"></script>
<script src="../../ui/easyui/jquery.min.js"></script>
<script src="../../ui/easyui/jquery.easyui.min.js"></script>
<script src="../../ui/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body id="mainLayout" style="display: none" class="easyui-layout"
	data-options="fit:true">
	<div
		data-options="region:'north',split:false,border:false,collapsible:false,title:''"
		id="west" style="position: relative; border-right: #e2e2e2 1px solid;">
		<table class="table-width">
			<tr>
				<td class="t" style="width: 80px;">用户名：</td>
				<td class="ts" style="width: 210px;"><input
					name="userName_search" id="userName_search" class="form-control"
					style="width: 200px; height: 28px" /></td>
				<td class="t" style="width: 80px;">部门：</td>
				<td class="ts" style="width: 210px;"><input name="parentId"
					id="parentId" class="form-control"
					style="width: 200px; height: 28px" /></td>
				<td class="t" style="width: 80px;">状态：</td>
				<td class="ts" colspan="2"><select class="easyui-combobox"
					id="status_search" ,name="status_search"
					data-options="panelHeight:'auto'" style="width: 210px;">

				</select></td>

			</tr>
		</table>
	</div>

	<div data-options="region:'center',border:false">
		<table class="easyui-datagrid" id="userList"
			data-options="rownumbers:true,toolbar:'#tbuser', fit:true">
			<thead>
				<tr>
					<th data-options="field:'userId',checkbox:true"></th>
					<th
						data-options="field:'organizationFullName',width:280,align:'center'">所属部门</th>
					<th data-options="field:'emailaddress',width:80,align:'center'">账号</th>
					<th data-options="field:'userName',width:80,align:'center'">用户名</th>
					<th data-options="field:'entryDate',width:80,align:'center'">入职时间</th>
					<th data-options="field:'userTypeName',width:80,align:'center'">岗位</th>
					<th data-options="field:'statusName',width:40,align:'center'">状态</th>
				</tr>
			</thead>
		</table>
		<div id="tbuser" style="height: 30px; background: #fafafa;">
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton" plain="true"
					icon="icon-utils-s-search" onclick="dosearch()">查询</a>
			</div>
			<div class="datagrid-btn-separator"></div>
			<shiro:hasPermission name="user:create">
				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true"
						icon="icon-utils-s-add" onclick="add()">新增</a>
				</div>
				<div class="datagrid-btn-separator"></div>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:update">
				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true"
						icon="icon-utils-s-edit" onclick="edit()">修改</a>
				</div>
				<div class="datagrid-btn-separator"></div>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:delete">
				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true"
						icon="icon-utils-s-delete" onclick="delete()">禁用</a>
				</div>
				<div class="datagrid-btn-separator"></div>
			</shiro:hasPermission>
		</div>
	</div>
	<div id="adddialog" class="easyui-dialog" title="新增用户"
		style="width: 600px; height: 150px; padding: 0px"
		data-options="
			    closed:true,
			    closable:false,
                top:50,
                href:'user_add.jsp',
				modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-circle-save',
					handler:function(){
	                    entitySave();
					}
				},{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
						$('#adddialog').dialog('close');
					}
				}]
			">
	</div>


	<input type="hidden" name="id" id="id" value="0">
</body>
<script>
function add(){
     $("#adddialog").dialog("open");
     $('#entityAddForm').form('reset')
    }
    function dosearch() {
	$("#userList").datagrid("reload");
    }
    var roleusrdialog;
    $(document).ready(function() {
	$('#mainLayout').css('display', 'block');
    });
    $(function() {
	$('#status_search').combobox({    
	    url:'../../systemData/getSystemDataEntityListWithSelect.shtml',
	    queryParams: {
		"dataCode" : SystemData.USERSTATUS,
		"showAll" : 1
	   },
	    valueField:'id',    
	    textField:'text'   
	});  
	
	$('#parentId').combotree(
			{
			    url : '../../organization/getOrganizationEntityTreeWithSelect.shtml',
			});
	userList = $('#userList').datagrid({
	    url : '../../user/getUsersEntityList.shtml',
	    collapsible : false,
	    pagination : true,
	    rownumbers : true,
	    singleSelect : false,
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
		param.ORGANIZATIONID = $("#parentId").combotree("getValue");
		param.KEYNAME = $('#userName_search').val();
		param.STATUS = $('#status_search').val();
	    },
	    queryParams : {
		ORGANIZATIONID : $("#parentId").combotree("getValue"),
		KEYNAME : $('#userName_search').val(),
		STATUS : $('#status_search').val()
	    }
	});
	$('#mainLayout').layout("resize");
    });
</script>
</html>