$(function() {
	$("#roleList").datagrid({
        title:'',
        singleSelect:false,
        collapsible:false, 
        pagination:false, 
        rownumbers:true, 
        method:'post',
		nowrap:true,
		fit:true,
		url:'datagrid_data1.json',
		method:'get', 
		loadMsg:'数据加载中......',
		fitColumns:true
    });
}) 
/*
function formatLinkOp(value, row, index){ 
	return '<div style="float: left;"><a href="#" name="settingMenu" class="easyui-linkbutton" plain="true"  data-options=\"iconCls:\'icon-utils-s-edit\'" onclick="custom_roleSet()">'+"权限设置"+"</a> </div><div class=\"datagrid-btn-separator\"></div><div style=\"float: left;\"><a href=\"#\"  name=\"settingUser\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-add\" onclick=\"custom_add()\">"+"人员管理"+"</a> </div><div class=\"datagrid-btn-separator\"></div><div style=\"float: left;\"><a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-add\" name=\"settingDelete\" onclick=\"custom_add()\">"+"删除"+"</a> </div>";
}
*/ 
function onLoadSuccess(data){  
	$("a[name='settingMenu']").linkbutton({text:'权限设置',plain:true,iconCls:'icon-utils-s-group-service'});
	$("a[name='settingUser']").linkbutton({text:'人员管理',plain:true,iconCls:'icon-utils-s-group-add'});
	$("a[name='settingDelete']").linkbutton({text:'删除',plain:true,iconCls:'icon-utils-s-delete'});
} 
