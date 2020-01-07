<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<form id="entityAddForm" class="customForm" method="post">
		<table class="table-width">
			<tr>
				<td class="t"><span class="bitian">*</span>账号：</td>
				<td class="ts"><input data-options="required:true"
					name="EMAILADDRESS_ADD" id="EMAILADDRESS_ADD"
					class="easyui-textbox form-control"
					style="width: 200px; height: 28px" /></td>
				<td class="t"><span class="bitian">*</span>用户名：</td>
				<td class="ts"><input data-options="required:true"
					name="NAME_ADD" id="NAME_ADD" class="easyui-textbox form-control"
					style="width: 200px; height: 28px" /></td>

			</tr>
			<tr>
				<td class="t"><span class="bitian">*</span>密码</td>
				<td class="ts"><input data-options="required:true"
					name="PASSWORD_ADD" id="PASSWORD_ADD" type="password"
					class="easyui-textbox form-control"
					style="width: 200px; height: 28px" /></td>
				<td class="t"><span class="bitian">*</span>所属部门：</td>
				<td class="ts"><input data-options="required:true"
					name="PARENTID_ADD" id="PARENTID_ADD"
					class="easyui-combotree form-control"
					style="width: 200px; height: 28px" /></td>
			</tr>
			<tr>
				<td class="t">岗位：</td>
				<td class="ts"><select class="easyui-combobox" id=JOBTITLE_ADD
					name="JOBTITLE_ADD" style="width: 200px"
					data-options="panelHeight:'auto'">
				</select></td>
				<td class="t"><span class="bitian">*</span>入职时间：</td>
				<td class="ts"><input data-options="required:true"
					name="JOINDATE_ADD" id="JOINDATE_ADD"
					data-options="formatter:dataShortformatter,parser:dataShortparser"
					class="easyui-datebox form-control"
					style="width: 200px; height: 28px" /></td>
			</tr>
		</table>
	</form>
	<script>
	$('#JOBTITLE_ADD').combobox({
	    url : '../../systemData/getSystemDataEntityListWithSelect.shtml',
	    queryParams : {
		"dataCode" : SystemData.USERTYPE,
		"showAll" : 0
	    },
	    valueField : 'id',
	    textField : 'text',
	    onLoadSuccess : function() { //加载完成后,val[0]写死设置选中第一项
		var val = $(this).combobox("getData");
		for ( var item in val[0]) {
		    if (item == "id") {
			$(this).combobox("select", val[0][item]);
		    }
		}
	    }

	});
	$(function() {
	    $('#PARENTID_ADD')
		    .combotree(
			    {
				url : '../../organization/getOrganizationEntityTreeWithSelect.shtml',
			    });
	});
	function entitySave() {
	    //有效性验证

	    if (!$('#entityAddForm').form('validate')) {

		$.messager.alert('提示', '表单还未填写完成!');
		return;
	    } else {
		var orgValue = $('#PARENTID_ADD').combotree("getValue");
		if (orgValue == '0') {
		    $.messager.alert('提示', '请选择所属部门!');
		    return;
		}
	    }
	    var data = {
		"userName" : $('#NAME_ADD').val(),
		"emailaddress" : $('#EMAILADDRESS_ADD').val(),
		"password" : $('#PASSWORD_ADD').val(),
		"organizationId" : $('#PARENTID_ADD').combotree("getValue"),
		"userType" : $('#JOBTITLE_ADD').combotree("getValue"),
		"entryDate" : $('#JOINDATE_ADD').val()
	    };
	    $.post("../../user/saveUserEntity.shtml", data, function(result) {
		if (result && result.result == 1) {
		    $("#userList").datagrid("reload");
		} else {
		    $.messager.alert('提示', result.message);
		}
	    }, "json");

	}
    </script>
</div>
<script src="../../js/easyuiLocal.js"></script>
