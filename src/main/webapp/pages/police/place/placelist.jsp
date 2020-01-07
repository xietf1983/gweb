<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title></title>
<title></title>
<link rel="icon" type="image/x-icon" href="img/favicon.ico" />
<link href="../../../css/font-awesome.min.css" rel="stylesheet">
<link href="../../../css/formeasyui.css" rel="stylesheet">
<link href="../../../ui/easyui/themes/gray/easyui.css" rel="stylesheet">
<link href="../../../css/easyui-main.css" rel="stylesheet">
<link href="../../../css/icon.css" rel="stylesheet">
<script src="../../../js/util.js"></script>
<script src="../../../js/constant.js"></script>
<script src="../../../ui/easyui/jquery.min.js"></script>
<script src="../../../ui/easyui/jquery.easyui.min.js"></script>
<script src="../../../ui/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body id="mainLayout" style="display: none" class="easyui-layout"
	data-options="fit:true">
	<div
		data-options="region:'north',split:false,border:true,collapsible:false,title:''"
		id="west" style="width: 100%; height: 66px;">

		<form id="searchForm">
			<table class="table-width" style="width: 100%;">
				<tr>
					<td class="t" style="width: 80px;">名称：</td>
					<td class="ts" style="width: 220px;"><input name="name_search"
						id="name_search" class="form-control" style="width: 200px;" /></td>
					<td class="t" style="width: 80px;">辖区：</td>
					<td class="ts" style="width: 220px;"><input name="parentId"
						id="parentId" class="form-control" style="width: 200px;" /></td>
					<td style="text-align: left;" rowspan="2"></td>
				</tr>
				<tr>
					<td class="t" style="width: 80px;">场所类型：</td>
					<td class="ts" style="width: 220px;"><select
						class="easyui-combobox" id="placeType_search"
						name="'placeType_search" style="width: 200px;">
					</select>
					<td class="t" style="width: 80px;">功能类型：</td>
					<td class="ts" style="width: 220px;"><select
						class="easyui-combobox" id="functiontype_search"
						name="'functiontype_search" style="width: 200px;">
					</select>
				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center',border:false" style="width: 100%;">
		<table class="easyui-datagrid" id="userList"
			data-options="idField:'id',rownumbers:true,toolbar:'#toolbar_custom', fit:true,fitColumns:true"
			style="width: 100%;">
			<thead>
				<tr>
					<!-- <th data-options="field:'id',checkbox:true,fixed:true"></th> -->
					<th
						data-options="field:'id',width:60,align:'center',formatter:formatLinkOp">操作</th>
					<th data-options="field:'eqpName',width:200,align:'center'">辖区</th>
					<th data-options="field:'name',width:280,align:'center'">名称</th>
					<th data-options="field:'code',width:180,align:'center',fixed:true">编号</th>
					<th
						data-options="field:'placeTypeShowValue',width:80,align:'center',fixed:true">场所类型</th>
					<th
						data-options="field:'videoCount',width:80,align:'center',fixed:true">摄像机数量</th>
					<th
						data-options="field:'createDateStr',width:80,align:'center',fixed:true">创建日期</th>

				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar_custom"
		style="height: 36px; width: 100%; padding: 3px 11px; background: #fafafa;">

		<shiro:hasPermission name="org:create">
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-utils-s-search'" onclick="dosearch()">查询</a>

			</div>
			<div class="datagrid-btn-separator"></div>
		</shiro:hasPermission>

		<shiro:hasPermission name="org:create">
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-utils-s-excel'" onclick="remove()">导出</a>
			</div>

		</shiro:hasPermission>

		<div id="exeTime"
			style="font-size: 10px; float: right; margin-top: 10px;"></div>
	</div>

	<!-- viewWindow -->
	<div id="viewWindow" class="easyui-window" title=""
		data-options="closed:true,
			    closable:false,
			    top:0,
			    width:710,
			    doSize:true,
			    title:'',
				modal:true,
		href:'place_view.jsp'"
		style="width: 710px; height: 400; padding: 0px"></div>

	<div id="adddialog" class="easyui-dialog" title="新增用户"
		style="width: 600px; height: 150px; padding: 0px"
		data-options="
			    closed:true,
			    closable:false,
                top:50,
                href:'user_add.jsp',
				modal:true,
				draggable:false,
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
     function closeview(){
    	 $('#viewWindow').dialog('close');
     }
	function add() {
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
	function formatLinkOp(value, row, index) {
		var result = '<div style="float: left;"><a href="#" name="viewdetail" class="easyui-linkbutton" plain="true"  data-options="iconCls:\'icon-utils-s-group-service\'" onclick="viewInfo(\'' + value + '\',\'' + index + '\')">详细</a></div>';
		return result;
	}
	function viewInfo(id, index) {
		//alert(index)

		$("#viewWindow").dialog({
			left : 280,
			height : $("body[class='easyui-layout layout panel-noscroll']").css('height'),
			top : 0,
			width : document.documentElement.clientWidth - 275,
			border : false,
			animate : true,
			onLoad : function() {

			}

		}).dialog("open");

	}
	$(function() {
		$('#placeType_search').combobox({
			url : getRootPath() + '/systemData/getSystemDataEntityListWithSelect.shtml',
			queryParams : {
				"dataCode" : SystemData.PLACETYPE,
				"showAll" : 1
			},
			showItemIcon : true,
			panelHeight : 'auto',
			valueField : 'id',
			textField : 'text'
		});

		$('#functiontype_search').combobox({
			url : getRootPath() + '/systemData/getSystemDataEntityListWithSelect.shtml',
			queryParams : {
				"dataCode" : SystemData.FUNCTIONTYPE,
				"showAll" : 1
			},
			panelHeight : 'auto',
			valueField : 'id',
			textField : 'text'
		});

		$('#parentId').combotree({
			url : getRootPath() + '/organization/getOrganizationEntityTreeWithSelect.shtml',
			panelHeight : 'auto'
		});
		userList = $('#userList').datagrid({
			url : getRootPath() + '/place/getPlaceEntityList.shtml',
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
			fitColumns : true,
			onBeforeLoad : function(param) {
				param.ORGANIZATIONID = $("#parentId").combotree("getValue");
				param.NAME = $('#userName_search').val();
				param.TYPE = $('#placeType_search').val();
				param.FUNCTIONTYPE = $('#functiontype_search').val();
			},
			queryParams : {
				ORGANIZATIONID : $("#parentId").combotree("getValue"),
				NAME : $('#userName_search').val(),
				TYPE : $('#placeType_search').val(),
				FUNCTIONTYPE : $('#functiontype_search').val()
			},
			onLoadSuccess : function onLoadSuccess(data) {
				var trs = $(this).prev().find('div.datagrid-body').find('tr');
				$("a[name='viewdetail']").linkbutton({
					text : '详细',
					plain : true,
					iconCls : 'icon-system-view'
				});
				var timeunits = 's';
				data.exeTime = data.exeTime / 1000;
				var extclass = "progressbar-text-orange"

				$('#exeTime').html("<span>响应时间:</span><span style='margin-top:20px;' class='"+extclass+ "' >&nbsp;" + data.exeTime + timeunits + "</span>")

			}
		});
		$('#mainLayout').layout("resize");
		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				if ($("#viewWindow").parent().is(":hidden")) {

				} else {
					$('#viewWindow').panel('resize', {
						left : 280,
						height : $("body[class='easyui-layout layout panel-noscroll']").css('height'),
						top : 0,
						width : document.documentElement.clientWidth - 275,
					});

				}
			}
		});
	});
</script>
</html>