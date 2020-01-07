<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<form id="entityAddForm" class="customForm" method="post">
		<table class="table-width">
			<tr>
				<td class="t"><span class="bitian">*</span>账号：</td>
				<td class="ts"><input data-options="required:true"
					name="EMAILADDRESS_ADD" id="EMAILADDRESS_ADD" class="easyui-textbox form-control"
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
					class="easyui-textbox form-control" style="width: 200px; height: 28px" /></td>
				<td class="t"><span class="bitian">*</span>所属部门：</td>
				<td class="ts"><input data-options="required:true"
					name="PARENTID_ADD" id="PARENTID_ADD" class="form-control"
					style="width: 200px; height: 28px" /></td>
			</tr>
			<tr>
				<td class="t">岗位：</td>
				<td class="ts"><select class="easyui-combobox" id=JOBTITLE_ADD
					name="JOBTITLE_ADD" style="width: 200px"
					data-options="panelHeight:'auto'">
						<option value='0'>普通员工</option>
						<option value='1'>部门负责人</option>
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
	$(function() {
	    $('#PARENTID_ADD')
		    .combotree(
			    {
				url : '../../organization/getOrganizationEntityTreeWithSelect.shtml',
			    });
	});
	function  entitySave(){
		//有效性验证
		if(!$('#entityAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
	}
    </script>
</div>
<script src="../../js/easyuiLocal.js"></script>
