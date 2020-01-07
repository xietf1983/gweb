<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="icon" type="image/x-icon" href="img/favicon.ico" />
<link href="../../css/font-awesome.min.css" rel="stylesheet">
<link href="../../css/formeasyui.css" rel="stylesheet">
<link href="../../ui/easyui/themes/gray/easyui.css" rel="stylesheet">
<link href="../../css/easyui-main.css" rel="stylesheet">
<link href="../../css/icon.css" rel="stylesheet">
<link href="../../css/index.css" rel="stylesheet" type="text/css" />
<script src="../../js/util.js"></script>
<script src="../../js/constant.js"></script>
<script src="../../ui/easyui/jquery.min.js"></script>
<script src="../../ui/easyui/jquery.easyui.min.js"></script>
<script src="../../ui/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="../../js/easyuiLocal.js"></script>
</head>
<body id="mainLayout" style="width: 100%;" class="easyui-layout">
	<div
		data-options="region:'north',split:false,border:true,collapsible:false,title:''"
		id="west" style="width: 100%; height: 66px;">

		<form id="searchForm">
			<table class="table-width" style="width: 100%;">
				<tr>
					<td class="t" style="width: 80px;">操作内容：</td>
					<td class="ts" style="width: 230px;"><select
						class="easyui-combobox" id="content_search" ,name="content_search"
						data-options="panelHeight:'auto'" style="width: 220px;">
					</select></td>

					<td class="t" style="width: 80px;">操作结果：</td>
					<td class="ts" style="width: 310px;"><select
						class="easyui-combobox" id="status_search" ,name="status_search"
						data-options="panelHeight:'auto'" style="width: 120px;">
					</select></td>
					<td style="text-align: left;" rowspan="2"><a
						href="javascript:void(0);" onclick="dorestForm();"
						;
					class="easyui-linkbutton"
						data-options="iconCls:'icon-system-refresh'">重置</a></td>
				</tr>
				<tr>
					<td class="t" style="width: 60px;">用户：</td>
					<td class="ts" style="width: 230px;"><input
						class="easyui-textbox" id="userselect"
						data-options="buttonText:'人员选择',
					buttonIcon:'icon-menubutton-user',editable:false,panelHeight:'auto',onClickButton:'openuserselect'"
						style="width: 220px; height: 28px;"></td>

					<td class="t" style="width: 60px;">时间：</td>
					<td class="ts" style="width: 310px;"><input type="text"
						id="startDate" class="easyui-datebox" required="required"
						style="width: 120px; height: 28px" />- <input type="text"
						id="endDate" class="easyui-datebox" required="required"
						style="width: 120px; height: 28px" /></td>

				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center',border:false">
		<table class="easyui-datagrid" id="systemloggrid"
			data-options="rownumbers:true, fit:true,idField: 'id',method:'post',toolbar:'#toolbar'">
			<thead>
				<tr>
					<th
						data-options="field:'id',width:80,align:'center',formatter:formatLinkOp">详细</th>
					<th data-options="field:'userAccount',width:100,align:'center'">用户</th>
					<th data-options="field:'userIP',width:150,align:'center'">IP</th>
					<th data-options="field:'description',width:210,align:'center'">操作内容</th>
					<th
						data-options="field:'operTime',width:110,align:'center',formatter:datetoLocal">操作时间</th>
					<th data-options="field:'exeTime',width:100,align:'center'">耗时(ms)</th>
					<th
						data-options="field:'sucess',width:80,align:'center',formatter:formatLinkStaus">结果</th>

				</tr>
			</thead>
		</table>

	</div>
	<div id="toolbar"
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
	<div id="viewWindow" class="easyui-window" title="日志详细"
		data-options="closed:true,
			    closable:true,
			    top:10,
			    width:710,
			    title:'',
				modal:true,
		href:'systemlog_view.jsp'"
		style="width: 710px; height: 400; padding: 0px"></div>
	<div id="userselectdialog" class="easyui-dialog" title="人员选择"
		style="width: 710px; height: 100%; padding: 0px"
		data-options="
			    closed:true,
			    closable:true,
			    top:10,
				modal:true,
				buttons: [{
					text:'确定',
					iconCls:'icon-system-view',
					handler:function(){
	                    userselect();
					}
				},{
					text:'关闭',
					iconCls:'icon-utils-s-close',
					handler:function(){
					$('#userselectdialog').dialog('close');
					}
				}]
			">
		<div style="width: 100%; height: 100%;">
			<iframe scrolling="auto" frameborder="0" id="userframe"
				src="../user/user_singleselect.jsp"
				style="width: 100%; height: 100%;"></iframe>
		</div>
	</div>
	<div class="loading-container">
		<div class="loader"></div>
	</div>
	<input type="hidden" name="id" id="id" value="">
	<input type="hidden" name="userId" id="userId" value="">
</body>
<script>
	$(document).ready(function() {

		$('#startDate').datebox('setValue', formatterDate(new Date()));
		$('#endDate').datebox('setValue', formatterDate(new Date()));
		$('.loading-container').fadeOut("normal", function() {
			$(this).remove();
		});
		$('#mainLayout').css('display', '');

		systemloggrid = $('#systemloggrid').datagrid({
			url : getRootPath() + '/log/getLogEventEntityList.shtml',
			collapsible : false,
			pagination : true,
			rownumbers : true,
			singleSelect : true,
			checkOnSelect : true,
			selectOnCheck : true,
			onSelect:function(index, row){
				viewInfo(row.id, index);
			},
			border : false,
			pageSize : 20,
			pageList : [ 20, 50, 100 ],
			//toolbar : '#remaintbuser',
			method : 'post',
			nowrap : true,
			fit : true,
			// toolbar : '#tbuser',
			loadMsg : '数据加载中......',
			fitColumns : true,
			onBeforeLoad : function(param) {
				param.STARTTIME = $('#startDate').val();
				param.ENDTIME = $('#endDate').val();
				param.STATUS = $('#status_search').val();
				param.USER = $('#userId').val();
				param.CONTENT = $('#content_search').val();
				//param.USERID = $('#status_search').val();
			},
			queryParams : {

			},
			onLoadSuccess : onLoadSuccess
		});

		$("#mainLayout").layout("resize", {
			width : "100%"
		});
		$("#userselect").textbox({
			onClickButton : function() {
				$("#userselectdialog").dialog("open");
			}
		});
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
		$('#status_search').combobox({
			url : getRootPath() + '/systemData/getSystemDataEntityListWithSelect.shtml',
			queryParams : {
				"dataCode" : SystemData.OPTSTATUS,
				"showAll" : 1
			},
			valueField : 'id',
			textField : 'text'
		});

	});
	function  domoveUp(){
		moveupRow($('#systemloggrid'));
	}
	function domovedown(){
		movedownRow($('#systemloggrid'));
		
	}
	
	
	function userselect() {
		var obj = window.frames["userframe"].contentWindow;
		var node = obj.userList.datagrid("getSelected");
		if (node != null) {
			$('#userId').val(node.userId);
			$('#userselect').textbox('setValue', node.userName)
		} else {
			//$('#userId').val('');
			//$('#userselect').textbox('setValue', '')
		}
		$('#userselectdialog').dialog('close');
		//alert(window.frames["userframe"].contentWindow);
	}
	function dosearch() {
		$("#systemloggrid").datagrid("reload");
	}
	function unuserselect() {
		var obj = window.frames["userframe"].contentWindow;
		obj.userList.datagrid('clearSelections');
		$('#userId').val('');
		$('#userselect').textbox('setValue', '')
		$('#userselectdialog').dialog('close');
		//alert(window.frames["userframe"].contentWindow);
	}
	function onLoadSuccess(data) {
		var trs = $(this).prev().find('div.datagrid-body').find('tr');
		$("a[name='viewdetail']").linkbutton({
			text : '详细',
			plain : true,
			iconCls : 'icon-system-view'
		});
		var timeunits = 's';
		//if (p) {
		var extclass = "progressbar-text-orange";
		data.exeTime = data.exeTime / 1000;

		$('#exeTime').html("<span>响应时间:</span><span style='margin-top:20px;' class='"+extclass+ "' >&nbsp;" + data.exeTime + timeunits + "</span>")
		/*
		if (data.rows.length > 0) {
			for (var i = 0; i < trs.length; i++) {
				// 行内单元格
				try {
					var row_html = trs[i].cells[5];
					var cell_field = $(row_html).attr('field');
					var cell_value = $(row_html).find('div').html();
					//cell_value = cell_value.replace("%", "");

					if (cell_value == '失败') {
						trs[i].cells[5].style.cssText = 'background:#EA0000;';
					} else {
						//trs[i].cells[5].style.cssText = 'background:#00EC00;';
					}
				} catch (ex) {

				}

			}
		}
		 */

	}
	function closeview(){
		 $('#viewWindow').dialog('close');
	}
	
	function moveup(){
		
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
				var row = $("#systemloggrid").datagrid('getSelected');
				//$("#exeTime_view").html('11');
				//document.getElementById("exeTime_view").innerHTML = "1111";
				$("#entityviewForm span").each(function(i, item) {
					//alert(222);
					var id = item.id;
					//document.getElementById("exeTime_view").innerHTML = "1111";
					var id2 = id.replace("_view", "");
					if (row['operTime'] != null) {
						row['operTimeStr'] = datetoLocal(row['operTime']);
					}

					if (row['sucess'] != null && row['sucess'] == '1') {
						row['sucessStr'] = '成功';
					} else {
						row['sucessStr'] = '失败';
					}
					if (row[id2] != null) {
						$("#" + id).html(row[id2]);
						//item.innerHTML(row[id2]);
					} else {
						$("#" + id).html('');
					}

				});
			}

		}).dialog("open");

	}
	function dorestForm() {
		$('#startDate').datebox('setValue', formatterDate(new Date()));
		$('#endDate').datebox('setValue', formatterDate(new Date()));
		$('#userId').val('');
		$('#userselect').textbox('setValue', '');
		var data = $('#status_search').combobox('getData');
		//alert(data[0].id)
		$('#status_search').combobox('select', data[0].id)
		dosearch();
		//$('#searchForm')[0].reset();
	}
	function formatLinkOp(value, row, index) {
		var result = '<div style="float: left;"><a href="#" name="viewdetail" class="easyui-linkbutton" plain="true"  data-options="iconCls:\'icon-utils-s-group-service\'" onclick="viewInfo(\'' + value + '\',\'' + index + '\')">详细</a></div>';
		return result;
	}
	function formatLinkStaus(value, row, index) {
		if (value == '0') {
			return '失败';
		} else {
			return '成功';
		}

	}
</script>
</html>

