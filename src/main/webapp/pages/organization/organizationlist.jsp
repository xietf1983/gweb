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
<script src="../../ui/easyui/jquery.min.js"></script>
<script src="../../ui/easyui/jquery.easyui.min.js"></script>
<script src="../../ui/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body id="bodybody" style="display: none">
	<table class="easyui-treegrid" title="" id="roleList"
		data-options="singleSelect:true,idField: 'id',treeField:'name',collapsible:false,method:'post',toolbar:'#toolbar'">
		<thead>
			<tr>
				<th data-options="field:'name',width:400,align:'left'">部门名称</th>
				<th data-options="field:'countValue',width:100,align:'center'">部门人数</th>
				<th
					data-options="field:'id',width:400,align:'center',formatter:formatLinkOp">操作</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<table>
			<tr>
				<shiro:hasPermission name="org:create">
					<td><a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-utils-s-add'" onclick="add()">新增</a></td>
				</shiro:hasPermission>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
				<shiro:hasPermission name="org:delete">
					<td><a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-utils-s-delete'" onclick="remove()">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</table>
	</div>
	<div id="adddialog" class="easyui-dialog" title="新增部门"
		style="width: 400px; height: 100px; padding: 0px"
		data-options="
			    closed:true,
			    closable:false,
                top:50,
				modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-circle-save',
					handler:function(){
	                    roleSave();
					}
				},{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
						$('#adddialog').dialog('close');
					}
				}]
			">
		<div>
			<table class="table-width">
				<tr>
					<td class="t">上级部门：</td>
					<td class="ts"><input name="parentId" id="parentId"
						class="form-control" style="width: 260px; height: 28px" /></td>
				</tr>
				<tr>
					<td class="t"><span class="bitian">*</span>部门名称：</td>
					<td class="ts"><input name="name_add" id="name_add"
						class="form-control" style="width: 260px; height: 28px" /></td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" name="id" id="id" value="">
</body>
<script>
    $(document)
	    .ready(
		    function() {
			$('#bodybody').css('display', 'block');

			$('#parentId').combotree(
					{
					    url : '../../organization/getOrganizationEntityTreeWithSelect.shtml',
					});
		    });
    $(function() {
	$("#roleList").treegrid({
	    singleSelect : true,
	    collapsible : false,
	    method : 'post',
	    idField : 'id',
	    treeField : 'name',
	    nowrap : true,
	    fit : true,
	    url : '../../organization/getOrganizationEntityTree.shtml',
	    loadMsg : '数据加载中......',
	    fitColumns : true,
	    onLoadSuccess : onLoadSuccess
	});
    });
    function remove() {
	var node = $("#roleList").treegrid("getSelected");
	if (node == null) {
	    $.messager.alert('提示', '请选择需要删除的记录');
	    return;
	}
	var rows = $("#roleList").treegrid("getChildren", node.id);
	if (rows.length > 0) {
	    $.messager.alert('提示', '只能删除子节点');
	    return;
	}
	$.messager
		.confirm(
			'确认',
			'您确认想要删除记录吗？',
			function(r) {
			    if (r) {
				var rows = $("#roleList").treegrid(
					"getChildren", node.id);
				if (rows.length == 0) {
				    $
					    .ajax({
						url : "../../organization/deleteOrganizationEntity.shtml",
						type : "POST",
						data : {
						    "id" : node.id
						},
						success : function(result) {
						    $.messager.alert('提示',
							    result.message);
						    $("#roleList").treegrid(
							    "reload");
						}
					    });
				}
			    }
			});

    }
    function add() {
	$('#parentId').combotree("reload")
	$("#adddialog").dialog("open");
    }

    function roleSave() {
	if ($('#name_add').val() == '') {
	    $.messager.alert('提示', '请填写部门名称');
	    return;
	}
	var data = {
	    parentId : $("#parentId").combotree("getValue"),
	    name : $('#name_add').val()
	};
	$.post("../../organization/saveOrganizationEntity.shtml", data,
		function(result) {
		    if (result && result.result == 1) {
			$("#roleList").treegrid("reload");
			$('#adddialog').dialog('close');
		    } else {
			$.messager.alert('提示', result.message);
			//$('#roleAdddialog').dialog('close');
		    }
		}, "json");
    }

    function formatLinkOp(value, row, index) {
	var result = '<shiro:hasPermission name="org:create"><div style="float: left;"><a href="#" name="settingMenu" class="easyui-linkbutton" plain="true"  data-options="iconCls:\'icon-utils-s-up\'" onclick="updateSort(\''
		+ value + '\',1)">上移</a> </div></shiro:hasPermission>';
	result = result
		+ '<shiro:hasPermission name="org:create"><div class="datagrid-btn-separator"></div><div style="float: left;"><a href="#"  name="settingUser" class="easyui-linkbutton" plain="true" data-options="iconCls:\'icon-utils-s-down\'" onclick="updateSort(\''
		+ value + '\',-1)">下移</a> </div></shiro:hasPermission>';

	return result;

    }

    function onLoadSuccess(data) {
	$("a[name='settingMenu']").linkbutton({
	    text : '上移',
	    plain : true,
	    iconCls : 'icon-utils-s-up'
	});
	$("a[name='settingUser']").linkbutton({
	    text : '下移',
	    plain : true,
	    iconCls : 'icon-utils-s-down'
	});
	$("a[name='settingDelete']").linkbutton({
	    text : '删除',
	    plain : true,
	    iconCls : 'icon-utils-s-delete'
	});
    }

    function updateSort(orgId, sortId) {
	$.ajax({
	    url : "../../organization/updateOrganizationEntitySort.shtml",
	    type : "POST",
	    traditional : "true",
	    data : {
		"id" : orgId,
		"sortId" : sortId,
		"time" : new Date().getTime()
	    },
	    success : function(data) {
		$("#roleList").treegrid("reload");
	    }
	});
    }
</script>
</html>