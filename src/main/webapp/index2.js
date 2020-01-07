function openSideMenu(id, name, url, icon, parentId) {
	alert(id)
}

function indexMenu() {
	// alert(777)
	$.post("index.shtml", null, function(json) {
		var sideMenu = "<li class=\"active\"><a href=\"javascript:void(0)\" onClick=openSideMenu(\"" + json.extend.HOMEPAGE.id + "\",\"" + json.extend.HOMEPAGE.name + "\",\"" + json.extend.HOMEPAGE.url + "\",\"" + json.extend.HOMEPAGE.menuIcon + "\",\"" + json.extend.HOMEPAGE.parentId + "\")><i class=\"" + json.extend.HOMEPAGE.menuIcon + "\"></i><span class=\"menu-text\">" + json.extend.HOMEPAGE.name + "</span></a></li>";
		var menuList = json.extend.MENU;
		// alert(menuList)
		var menu = getSideMenu(menuList, "", "");
		// alert(menu)
		var sideMenu = sideMenu + menu;
		$("#sidebarnav").html(sideMenu);
	});
	// var $box = $('#menuheader').children();
	// Sidebar Collapse
	var b = $("#sidebar").hasClass("menu-compact");

	$("#menuheader").on('click', function() {
		if (!$('#sidebar').is(':visible'))
			$("#sidebar").toggleClass("hide");
		$("#sidebar").toggleClass("menu-compact");
		$(".sidebar-collapse").toggleClass("active");
		b = $("#sidebar").hasClass("menu-compact");

		if ($(".sidebar-menu").closest("div").hasClass("slimScrollDiv")) {
			$(".sidebar-menu").slimScroll({
				destroy : true
			});
			$(".sidebar-menu").attr('style', '');
			$box.removeClass('west-collapse').animate({
				width : width + 'px'
			}, 100).siblings().remove();
		}
		if (b) {
			$(".open > .submenu").removeClass("open");
			$("#menuheader").html("<a class=\"icon west-collapse\"></a>");
			
			
		} else {
			$("#menuheader").html("<a class=\"icon icon-menu\"></a>");
		}
	});

}
function getSideMenu(menuList, submenu, sideMenu) {
	// var sideMenu = "";
	if (submenu != null && submenu != '') {
		sideMenu = sideMenu + "<ul class=\"submenu\">";
	}
	if (menuList != null && menuList.length > 0) {
		for (var k = 0; k < menuList.length; k++) {
			var menu = menuList[k];
			if (menu.menuItemList != null && menu.menuItemList.length > 0) {
				sideMenu = sideMenu + "<li><a href=\"javascript:void(0)\" class=\"menu-dropdown\"><i class=\"" + menu.menuIcon + "\"></i><span class=\"menu-text\">" + menu.name + "</span><i class=\"menu-expand\"></i></a>";
				sideMenu = getSideMenu(menu.menuItemList, "submenu", sideMenu);
			} else {
				sideMenu = sideMenu + "<li><a href=\"javascript:void(0)\" onClick=openSideMenu(\"" + menu.id + "\",\"" + menu.name + "\",\"" + menu.url + "\",\"" + menu.menuIcon + "\",\"" + menu.parentId + "\")><i class=\"" + menu.menuIcon + "\"></i><span class=\"menu-text\">" + menu.name + "</span></a></li>";

			}
		}
	}
	if (submenu != null & submenu != '') {
		sideMenu = sideMenu + "</ul></li>";
	}
	// alert(sideMenu)
	return sideMenu;
}

function getLeafCountTree(json) {
	if (!json.children) {
		return 1;
	} else {
		var leafCount = 0;
		for (var i = 0; i < json.children.length; i++) {
			leafCount = leafCount + getLeafCountTree(json.children[i]);
		}
		return leafCount;
	}
}
