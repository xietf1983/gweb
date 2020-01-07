function dataShortformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function dataShortparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
$.extend({
	loading : function() {
		$("<div class=\"datagrid-mask\"></div>").css({
			display : "block",
			width : "100%",
			height : $(document.body).height()
		}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>").html("正在提交数据...").appendTo("body").css({
			display : "block",
			left : ($(window).outerWidth(true) - 190) / 2,
			top : ($(window).scrollTop() + 350)
		});
	},
	removeload : function(isParentWindow) {
		var t = $;
		if (typeof isParentWindow != "undefined")
			t = top.$;
		t(".datagrid-mask,.datagrid-mask-msg").remove();
	}
});
/**
 * 向上移一行
 * 
 * @param datagrid
 */
function moveupRow(grid) {
	var selected = grid.datagrid('getSelected');
	var page = 1;
	var pageSize = 0;
	var pager = grid.datagrid('getPager');
	if (pager != null) {
		pageSize = pager.data("pagination").options.pageSize;
		page = pager.data("pagination").options.pageNumber;
	}
	if (selected) {
		var index = grid.datagrid('getRowIndex', selected);
		if (index == 0 && page == 1) {
			$.messager.show({
				title : '提示信息',
				msg : '已经是第一条！',
				timeout : 2000,
				height : 100,
				showType : 'slide'
			});
			return;
		} else {
			if (index == 0) {
				grid.datagrid('gotoPage', {
					page : page - 1,
					callback : function(page) {
						grid.datagrid('selectRow', pageSize - 1);
					}
				})
				return;
			}
		}
		grid.datagrid('selectRow', index - 1);
	} else {
		var rows = grid.datagrid('getRows');
		if (rows > 0) {
			grid.datagrid('selectRow', 0);
		}
	}

}
/**
 * 下一移一行
 * 
 * @param dg
 * @param row
 */
function movedownRow(grid) {
	var selected = grid.datagrid('getSelected');
	var page = 1;
	var pageSize = 0;
	var pager = grid.datagrid('getPager');
	if (pager != null) {
		pageSize = pager.data("pagination").options.pageSize;
		page = pager.data("pagination").options.pageNumber;
	}
	if (selected) {
		var index = grid.datagrid('getRowIndex', selected);
		var data = grid.datagrid('getData');
		var rowNum = data.total;
		// alert(rowNum)
		if (index + (page - 1) * pageSize == (rowNum - 1)) {
			$.messager.show({
				title : '提示信息',
				msg : '已经是最后一条！',
				timeout : 2000,
				height : 100,
				showType : 'slide'
			});
			return;
		}
		// alert(222)
		var rowNum = grid.datagrid("getRows").length;
		// alert((index + 1) >= rowNum)
		if ((index + 1) >= rowNum) {
			// var pager = grid.datagrid('getPager');
			grid.datagrid('gotoPage', {
				page : page + 1,
				callback : function(page) {
					grid.datagrid('selectRow', 0);
				}
			})

			return;
		}
		grid.datagrid('selectRow', index + 1);
	} else {
		var data = grid.datagrid('getData');
		var rows = data.total;
		// alert(rows)
		if (rows > 0) {
			grid.datagrid('selectRow', 0);
		}
	}

}