jQuery(document).ready(function() {
	var height = window.innerHeight;
	if (height == undefined) {
		var B = document.body, D = document.documentElement;
		height = Math.min(D.clientHeight, B.clientHeight);

	}
	var yy = height - 90;
	$("#iframe-center").height(yy);

	initmenu();
	

	// Add class nav-hover to mene. Useful for viewing sub-menu
	jQuery('.leftpanel .nav li').hover(function() {
		$(this).addClass('nav-hover');
	}, function() {
		$(this).removeClass('nav-hover');
	});

	// For Media Queries
	jQuery(window).resize(function() {
		hideMenu();
	});

	hideMenu(); // for loading/refreshing the page
	function hideMenu() {

		if ($('.header-right').css('position') == 'relative') {
			$('body').addClass('hidden-left');
			$('.headerwrapper, .mainwrapper').removeClass('collapsed');
		} else {
			$('body').removeClass('hidden-left');
		}
	}

	collapsedMenu(); // for loading/refreshing the page
	function collapsedMenu() {

		if ($('.logo').css('position') == 'relative') {
			$('.headerwrapper, .mainwrapper').addClass('collapsed');
		} else {
			$('.headerwrapper, .mainwrapper').removeClass('collapsed');
		}
	}

});

function menucollapse() {
	if ($('.mainwrapper').hasClass('collapsed')) {
		$('.mainwrapper').removeClass('collapsed');
		$('#menuToggle').removeClass("fa-indent").addClass("fa-dedent");
	} else {
		$('#menuToggle').removeClass("fa-dedent").addClass("fa-indent");
		$('.mainwrapper').addClass('collapsed');
		$('.children').hide();
	}
	return false;
}
function loadmenu(url, id, parent, name) {
	jQuery('.leftpanel .nav .parent').each(function() {
		// jQuery(this).find('.children').slideUp('fast');
		jQuery(this).removeClass('active');
		jQuery(this).removeClass('parent-focus');
	});
	jQuery('.leftpanel .nav .active').each(function() {
		// jQuery(this).find('.children').slideUp('fast');
		jQuery(this).removeClass('active');
	});
	if (parent) {
		document.getElementById(id).className = "parent active";

	} else {
		document.getElementById(id).parentNode.parentNode.className = "parent active";
		document.getElementById(id).className = "active";
	}
	$("#iframe-center").attr("src", url);
	document.getElementById("crenntbreadcrumb").innerHTML = name;
}
function initmenu() {
	var menuList = eval(menuData);
	var topmenucontent = document.getElementById("leftpanel-nal");
	for (i = 0; i < menuList.length; i++) {
		var li = document.createElement("li");
		var href = document.createElement("a");
		if (i == 0) {
			firsturl = menuList[i].url;
			li.className = "parent active";
		}
		li.id = menuList[i].id;
		href.innerHTML = "<i class=\"fa " + menuList[i].className + "\"></i> <span>" + menuList[i].name + "</span>"
		if (menuList[i].url != null && menuList[i].url != '') {
			href.href = "javascript:loadmenu(\"" + menuList[i].url + "\",\"" + menuList[i].id + "\"," + true + ",'" + menuList[i].name + "')";
		} else {
			href.href = "#";
		}
		li.appendChild(href);
		if (menuList[i].child != null && menuList[i].child.length > 0) {
			if (i == 0) {
				li.className = "parent active";
			} else {
				li.className = "parent";
			}
			var ul = document.createElement("ul");
			ul.className = "children";
			for (var j = 0; j < menuList[i].child.length; j++) {
				var child = menuList[i].child[j];
				var subli = document.createElement("li");
				subli.id = child.id;
				var subhref = document.createElement("a");
				subhref.innerHTML = child.name;
				subhref.href = "javascript:loadmenu(\"" + child.url + "\",\"" + child.id + "\"," + false + ",'" + child.name + "')";
				subli.appendChild(subhref);
				ul.appendChild(subli);
			}
			li.appendChild(ul);
		}
		topmenucontent.appendChild(li);
	}
}
