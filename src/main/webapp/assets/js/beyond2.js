/*Loading*/
$(window).load(function() {
	//alert(1)
	$('body').css('display','block'); 
	//alert(22)
	$('.loading-container').fadeOut("normal", function() {
		$(this).remove();
	});
	//$('.loading-container').addClass('loading-inactive');
	InitiateSideMenu();
	InitiateWidgets();
});
/* Toggle FullScreen */
$('#fullscreen-toggler').on('click', function(e) {
	var element = document.documentElement;
	if (!$('body').hasClass("full-screen")) {

		$('body').addClass("full-screen");
		$('#fullscreen-toggler').addClass("active");
		if (element.requestFullscreen) {
			element.requestFullscreen();
		} else if (element.mozRequestFullScreen) {
			element.mozRequestFullScreen();
		} else if (element.webkitRequestFullscreen) {
			element.webkitRequestFullscreen();
		} else if (element.msRequestFullscreen) {
			element.msRequestFullscreen();
		}

	} else {

		$('body').removeClass("full-screen");
		$('#fullscreen-toggler').removeClass("active");

		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		}

	}
});

function InitiateSideMenu() {

	// Sidebar Toggler
	$(".sidebar-toggler").on('click', function() {
		$("#sidebar").toggleClass("hide");
		$(".sidebar-toggler").toggleClass("active");
		return false;
	});
	// End Sidebar Toggler

	// Sidebar Collapse
	var b = $("#sidebar").hasClass("menu-compact");
	$("#sidebar-collapse").on('click', function() {
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
		}
		if (b) {
			$(".open > .submenu").removeClass("open");
		} else {
			if ($('.page-sidebar').hasClass('sidebar-fixed')) {
				var position = (readCookie("rtl-support") || location.pathname == "/index-rtl-fa.html" || location.pathname == "/index-rtl-ar.html") ? 'right' : 'left';
				$('.sidebar-menu').slimscroll({
					height : 'auto',
					position : position,
					size : '3px',
					color : themeprimary
				});
			}
		}
		// Slim Scroll Handle

	});
	// End Sidebar Collapse

	// Sidebar Menu Handle
	$(".sidebar-menu").on('click', function(e) {
		var menuLink = $(e.target).closest("a");
		if (!menuLink || menuLink.length == 0)
			return;
		if (!menuLink.hasClass("menu-dropdown")) {
			if (b && menuLink.get(0).parentNode.parentNode == this) {
				var menuText = menuLink.find(".menu-text").get(0);
				if (e.target != menuText && !$.contains(menuText, e.target)) {
					return false;
				}
			}
			return;
		}
		var submenu = menuLink.next().get(0);
		if (!$(submenu).is(":visible")) {
			var c = $(submenu.parentNode).closest("ul");
			if (b && c.hasClass("sidebar-menu"))
				return;
			c.find("> .open > .submenu").each(function() {
				if (this != submenu && !$(this.parentNode).hasClass("active"))
					$(this).slideUp(200).parent().removeClass("open");
			});
		}
		if (b && $(submenu.parentNode.parentNode).hasClass("sidebar-menu"))
			return false;
		$(submenu).slideToggle(200).parent().toggleClass("open");
		return false;
	});
	// End Sidebar Menu Handle
}

function InitiateWidgets() {
	$('.widget-buttons *[data-toggle="maximize"]').on("click", function(event) {
		event.preventDefault();
		var widget = $(this).parents(".widget").eq(0);
		var button = $(this).find("i").eq(0);
		var compress = "fa-compress";
		var expand = "fa-expand";
		if (widget.hasClass("maximized")) {
			if (button) {
				button.addClass(expand).removeClass(compress);
			}
			widget.removeClass("maximized");
			widget.find(".widget-body").css("height", "auto");
		} else {
			if (button) {
				button.addClass(compress).removeClass(expand);
			}
			widget.addClass("maximized");
			maximize(widget);
		}
	});

	$('.widget-buttons *[data-toggle="collapse"]').on("click", function(event) {
		event.preventDefault();
		var widget = $(this).parents(".widget").eq(0);
		var body = widget.find(".widget-body");
		var button = $(this).find("i");
		var down = "fa-plus";
		var up = "fa-minus";
		var slidedowninterval = 300;
		var slideupinterval = 200;
		if (widget.hasClass("collapsed")) {
			if (button) {
				button.addClass(up).removeClass(down);
			}
			widget.removeClass("collapsed");
			body.slideUp(0, function() {
				body.slideDown(slidedowninterval);
			});
		} else {
			if (button) {
				button.addClass(down).removeClass(up);
			}
			body.slideUp(slideupinterval, function() {
				widget.addClass("collapsed");
			});
		}
	});

	$('.widget-buttons *[data-toggle="dispose"]').on("click", function(event) {
		event.preventDefault();
		var toolbarLink = $(this);
		var widget = toolbarLink.parents(".widget").eq(0);
		var disposeinterval = 300;
		widget.hide(disposeinterval, function() {
			widget.remove();
		});
	});
}