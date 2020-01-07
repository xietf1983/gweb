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
<script src="../../js/util.js"></script>
</head>
<body id="bodybody" style="display: none">
	<table class="easyui-datagrid" title="" id="roleList"
		data-options="singleSelect:true,fitColumns:true,collapsible:false,method:'post',toolbar:'#toolbar'">
		<thead>
			<tr>
				<th data-options="field:'name',width:200,align:'center'">角色列表</th>
				<th
					data-options="field:'roleId',width:400,align:'center',formatter:formatLinkOp">操作</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px 5px;">
		<div>
			<shiro:hasPermission name="role:create">
				<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-utils-s-add'" onclick="roleadd()">新增</a>
			</shiro:hasPermission>
		</div>
	</div>
	<div id="roleAdddialog" class="easyui-dialog" title="新增角色"
		style="width: 400px; height: 48px; padding: 0px"
		data-options="
			    closed:true,
			    closable:false,
				modal:true,
                top:50,
				buttons: [{
					text:'保存',
					iconCls:'icon-circle-save',
					handler:function(){
						var roleId ='';
						var roleName =$('#rolename_add').val();
						var data = {
		                 roleId : roleId,
		                 name : roleName
	                    };
	                    roleSave(data);
					}
				},{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
						$('#roleAdddialog').dialog('close');
					}
				}]
			">
		<div>
			<table class="table-width">
				<tr>
					<td class="t"><span class="bitian">*</span>权限组名称：</td>
					<td class="ts"><input name="rolename_add" id="rolename_add"
						class="form-control" style="width: 260px; height: 28px" /></td>
				</tr>
			</table>
		</div>
	</div>

	<div id="userSetdialog" class="easyui-dialog" title="人员管理"
		style="width: 550px; height: 400px; padding: 0px"
		data-options="
			    closed:true,
			    closable:true,
			    top:10,
				modal:true,
				buttons: [{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
					$('#userSetdialog').dialog('close');
					}
				}]
			">
		<div>
			<div id="tt" class="easyui-tabs" style="height: 385px;"
				data-options="border:false">
				<div title="当前">
					<table class="easyui-datagrid" id="roleuserList">
						<thead>
							<tr>
								<th data-options="field:'userId',checkbox:true"></th>
								<th data-options="field:'emailaddress',width:160,align:'center'">账号</th>
								<th data-options="field:'userName',width:160,align:'center'">用户名</th>
						</thead>
					</table>
					<div id="tbuser">
						<div style="float: left; padding-left: 20px;">
							<input id="userKey" class="easyui-searchbox"
								searcher="doUserSearch" prompt=""
								data-options="events:{blur:doUserSearch}"
								style="width: 130px; vertical-align: middle;"></input>
						</div>
						<div class="datagrid-btn-separator"></div>
						<div>
							<a href="#" class="easyui-linkbutton" plain="true"
								icon="icon-utils-s-delete" onclick="deleteroleuser()">删除</a>
						</div>
					</div>
				</div>
				<div title="可用" data-options="closable:false"
					style="overflow: auto;">
					<table class="easyui-datagrid" id="remainroleuserList">
						<thead>
							<tr>
								<th data-options="field:'userId',checkbox:true"></th>
								<th data-options="field:'emailaddress',width:160,align:'center'">账号</th>
								<th data-options="field:'userName',width:160,align:'center'">用户名</th>
							</tr>
						</thead>
					</table>
					<div id="remaintbuser">
						<div style="float: left; padding-left: 20px;">
							<input id="remainuserKey" class="easyui-searchbox"
								searcher="doRemainUserSearch" prompt=""
								data-options="events:{blur:doRemainUserSearch}"
								style="width: 130px; vertical-align: middle;"></input>
						</div>
						<div class="datagrid-btn-separator"></div>
						<div>
							<a href="#" class="easyui-linkbutton" plain="true"
								icon="icon-utils-s-add" onclick="addroleuser()">保存</a>
						</div>
					</div>


				</div>

			</div>

		</div>
	</div>
	<div id="menuRoledialog" class="easyui-dialog" title="权限管理"
		style="width: 510px; height: 400px; padding: 0px"
		data-options="
			    closed:true,
			    closable:true,
			    top:10,
				modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-circle-save',
					handler:function(){
	                    saveRoleMenu();
					}
				},{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
					colsemenuRoledialog();
					}
				}]
			">
		<div>
			<div class='easyui-layout' style="height: 380px; padding: 0px">
				<div style="border-right: #e2e2e2 1px solid;"
					data-options="width: 230,region:'center',split:false,border:true,collapsible:false,title:'',border:false">
					<ul id="menutree" data-options="split:false,border:false,title:''">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="roleId" id="roleId" value="">
</body>
<script>
	var roleusrdialog;
	$(document).ready(function() {
		$('#bodybody').css('display', 'block');
	});

	function aaa() {
		alert(222)
	}
	$(function() {
		$("#roleList").datagrid({
			title : '',
			singleSelect : true,
			collapsible : false,
			pagination : false,
			rownumbers : true,
			method : 'post',
			checkOnSelect : true,
			nowrap : true,
			fit : true,
			url : getRootPath() + '/role/getRoleEntityList.shtml',
			loadMsg : '数据加载中......',
			fitColumns : true,
			onLoadSuccess : onLoadSuccess
		});
	});
	function saveRoleMenu() {
		var menuIds = new Array();
		var roleId = $('#roleId').val();
		var rows = $('#menutree').tree('getChecked');
		if (rows != null) {
			for (var i = 0; i < rows.length; i++) {
				// var row=rows[i];
				menuIds.push(rows[i].id);
				// alert(rows[i].userId);
			}
		}
		$.ajax({
			url : getRootPath() + "/role/saveRoleMenuEntity.shtml",
			type : "POST",
			traditional : "true",
			data : {
				"menuIds" : menuIds,
				"roleId" : roleId,
				"time" : new Date().getTime()
			},
			success : function(data) {
				$.messager.show({
					title : '提示信息',
					msg : '保存成功',
					timeout : 5000,
					height : 100,
					showType : 'slide'
				});

			}
		});
	}
	function reload() {
		$("#roleList").datagrid({
			url : getRootPath() + '/role/getRoleEntityList.shtml',
			loadMsg : '数据加载中......',
			method : 'post'
		});
	}
	function deleteroleuser() {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				var userIds = new Array();
				var roleId = $('#roleId').val();
				var rows = $('#roleuserList').datagrid('getChecked');
				if (rows != null) {
					for (var i = 0; i < rows.length; i++) {
						// var row=rows[i];
						userIds.push(rows[i].userId);
						// alert(rows[i].userId);
					}
				}
				$.ajax({
					url : getRootPath() + "/role/removeUserRoles.shtml",
					type : "POST",
					traditional : "true",
					data : {
						"userIds" : userIds,
						"roleId" : roleId,
						"time" : new Date().getTime()
					},
					success : function(data) {
						doUserSearch();
						doRemainUserSearch();
					}
				});
			}
		});

	}
	function addroleuser() {
		var userIds = new Array();
		var roleId = $('#roleId').val();
		var rows = $('#remainroleuserList').datagrid('getChecked');
		if (rows != null) {
			for (var i = 0; i < rows.length; i++) {
				userIds.push(rows[i].userId);
			}
		}
		$.ajax({
			url : getRootPath() + "/role/saveRoleUsersEntity.shtml",
			type : "POST",
			traditional : "true",
			data : {
				"userIds" : userIds,
				"roleId" : roleId,
				"time" : new Date().getTime()
			},
			success : function(data) {
				doUserSearch();
				doRemainUserSearch();
			}
		});

	}
	function roleSave(data) {
		$.post(getRootPath() + "/role/saveRoleEntity.shtml", data, function(result) {
			if (result && result.result == 1) {
				reload();
				$('#roleAdddialog').dialog('close');
			} else {
				$.messager.alert('提示', result.message);
				//$('#roleAdddialog').dialog('close');
			}
		}, "json");
	}
	function roleadd() {
		$("#roleAdddialog").dialog("open");
	}
	function formatLinkOp(value, row, index) {
		var result = '<shiro:hasPermission name="role:set"><div style="float: left;"><a href="#" name="settingMenu" class="easyui-linkbutton" plain="true"  data-options="iconCls:\'icon-utils-s-group-service\'" onclick="roleSet(\'' + value + '\')">权限设置</a> </div></shiro:hasPermission>';
		result = result + '<shiro:hasPermission name="role:user"><div class="datagrid-btn-separator"></div><div style="float: left;"><a href="#"  name="settingUser" class="easyui-linkbutton" plain="true" icon="icon-utils-s-group-add" onclick="roleUser(\'' + value + '\')">人员管理</a> </div></shiro:hasPermission>';
		result = result + '<shiro:hasPermission name="role:delete"><div class="datagrid-btn-separator"></div><div style="float: left;"><a href="#" class="easyui-linkbutton" plain="true" icon="icon-utils-s-delete" name="settingDelete" onclick="roleDel(\'' + value + '\')">删除</a> </div></shiro:hasPermission>';
		return result;

	}
	function onLoadSuccess(data) {
		$("a[name='settingMenu']").linkbutton({
			text : '权限设置',
			plain : true,
			iconCls : 'icon-utils-s-group-service'
		});
		$("a[name='settingUser']").linkbutton({
			text : '人员管理',
			plain : true,
			iconCls : 'icon-utils-s-group-add'
		});
		$("a[name='settingDelete']").linkbutton({
			text : '删除',
			plain : true,
			iconCls : 'icon-utils-s-delete'
		});
	}
	function roleSet(roleId) {
		$("#roleId").val(roleId);
		if (roleusrdialog == null) {
			$('#rolemenulayout').layout();
			$('#menutree').tree({
				url : getRootPath() + '/role/getRoleMenuEntityTree.shtml',
				checkbox : true,
				queryParams : {
					roleId : $('#roleId').val()
				}
			});
		} else {
			$("#menutree").datagrid("reload", {
				roleId : $('#roleId').val()
			});
		}
		$("#menuRoledialog").dialog("open");

	}
	function colsemenuRoledialog() {
		$("#menuRoledialog").dialog("close");
	}
	var roleusergird;
	function roleUser(roleId) {
		$("#roleId").val(roleId);
		$("#userKey").val('');
		if (roleusergird == null) {
			roleusergird = $('#roleuserList').datagrid({
				url : getRootPath() + '/role/getUsersRoleEntityList.shtml',
				collapsible : false,
				pagination : true,
				rownumbers : true,
				singleSelect : false,
				checkOnSelect : true,
				selectOnCheck : true,
				border : false,
				pageSize : 10,
				pageList : [ 10, 20, 50 ],
				toolbar : '#tbuser',
				method : 'post',
				nowrap : true,
				fit : true,
				loadMsg : '数据加载中......',
				fitColumns : true,
				onBeforeLoad : function(param) {
					param.roleId = $('#roleId').val();
					param.name = $('#userKey').searchbox('textbox').val();
				},
				queryParams : {
					roleId : $('#roleId').val(),
					name : $('#userKey').searchbox('textbox').val()
				}
			});

			remainroleuserList = $('#remainroleuserList').datagrid({
				url : getRootPath() + '/role/getRemainUsersRoleEntityList.shtml',
				collapsible : false,
				pagination : true,
				rownumbers : true,
				singleSelect : false,
				checkOnSelect : true,
				selectOnCheck : true,
				border : false,
				pageSize : 10,
				pageList : [ 10, 20, 50 ],
				toolbar : '#remaintbuser',
				method : 'post',
				nowrap : true,
				fit : true,
				loadMsg : '数据加载中......',
				fitColumns : true,
				onBeforeLoad : function(param) {
					param.roleId = $('#roleId').val();
					param.name = $('#remainuserKey').searchbox('textbox').val();
				},
				queryParams : {
					roleId : $('#roleId').val(),
					name : $('#remainuserKey').searchbox('textbox').val()
				}
			});

		} else {
			doUserSearch();
		}
		$("#userSetdialog").dialog("open");

	}

	function doUserSearch() {
		//alert($('#userKey').searchbox('textbox').val())
		//alert($('#userKey').$('#ss').searchbox())
		//alert($('#userKey').searchbox('textbox').val())
		$("#roleuserList").datagrid("reload", {
			roleId : $('#roleId').val(),
			name : $('#userKey').searchbox('textbox').val()
		});
	}
	function doRemainUserSearch() {
		//alert( $('#remainuserKey').searchbox('textbox').val())
		$("#remainroleuserList").datagrid("load");
	}
	function roleDel(roleId) {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				var data = {
					roleId : roleId,
					name : ''
				};
				$.post(getRootPath() + "/role/deleteRoleEntity.shtml", data, function(result) {
					if (result && result.result == 1) {
						reload();
					} else {
						$.messager.alert('提示', result.message);

					}
				}, "json");
			}
		});

	}
</script>
</html>