var ROW_PER_PAGE = 25;
var SECONDARY_ROW_PER_PAGE = 10;
var FILE_SIZE_UNITS = new Array('Bytes', 'KB', 'MB', 'GB');

$(window).on('load', function() {
    $('#status').fadeOut(0);
    $('#preloader').fadeOut(0);
    $('body').css({
	'overflow' : 'visible'
    });
    $('.disabled').attr('tabindex', '-1');
    $('.disabled input').attr('tabindex', '-1');
});

$(document).ready(function() {
    baseInit();
    init();
    baseBind();
    bind();
});

$(document).ajaxComplete(function(event, xhr, settings) {
    if (xhr.status == 403) {
	alert("This page contains unauthorized contents for you.Please contact administrators !");
	window.location.href = getContextPath() + "/accessDenied";
    } else if (xhr.status == 208) {
	document.write(xhr.responseText);
    } else if (xhr.status == 226) {
	document.write(xhr.responseText);
    }
});

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
    alert("Error occured while loading page informations! Check your internet connection.");
});

function baseInit() {

    // load profile logo
    loadProfileLogo();

    var count = 0;
    $('.img-circle.profile-image').error(function() {
	if (count < 3) {
	    count++;
	    loadProfileLogo();
	} else {
	    $(this).attr("src", getContextPath() + "/images/avatar/guest.jpg");
	}
    });

    $('form').attr('autocomplete', 'off');

    $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
	if ($(".table").length) {
	    $.fn.dataTable.tables({
		visible : true,
		api : true
	    }).columns.adjust();
	}
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
    });

}

function baseBind() {
    $(window).scroll(function() {
	if ($(this).scrollTop() > 150) {
	    $('.scrollToTop').fadeIn();
	} else {
	    $('.scrollToTop').fadeOut();
	}
    });

    $('.scrollToTop').click(function() {
	$('html, body').animate({
	    scrollTop : 0
	}, 800);
	return false;
    });

    $('table.dataTable').on('processing.dt', function(e, settings, processing) {
	if (processing) {
	    Pace.start();
	} else {
	    Pace.stop();
	}
    });
}

function loadProfileLogo() {
    window.setTimeout(function() {
	var imagePath = $(".img-circle.profile-image").attr("src");
	$(".img-circle.profile-image").attr("src", imagePath);
    }, 500);
}

function fixedFunctionColumn(tableSelector) {
    // check is it functionColumn ?
    if (!$(tableSelector + ":first-child thead tr th:last-child").hasClass("functionColumn")) {
	return false;
    }
    var tableWidth = $(tableSelector + ":first-child thead tr th:last-child").outerWidth() + 1;
    // remove existing fixedColumnsTable
    $(tableSelector).parent().siblings(".fixedColumnsTable").remove();
    // create table
    var table = $('<table class="fixedColumnsTable"></table>');
    $(table).addClass($(tableSelector).attr("class"));
    // create table header
    var thead = $("<thead></thead>");
    var tr_head = $('<tr><th class="functionColumn sorting_disabled">Function</th></tr>');
    thead.append(tr_head);
    table.append(thead);
    // create table body
    var tbody = $("<tbody></tbody>");
    $.each($(tableSelector + ":last-child tbody tr td:last-child"), function(index, lastChild) {
	var tr = $('<tr></tr>');
	var td = $(lastChild).clone();
	// make sure it is not an empty table
	if ($(td).hasClass("dataTables_empty")) {
	    return false;
	}
	// need to reduce first row's padding amount 1 for mis-align
	if (index == 0) {
	    $(td).css("padding-bottom", "2px");
	}
	// centering the content
	$(td).addClass("text-center");
	tr.append(td);
	tbody.append(tr);
    });
    table.append(tbody);
    $(table).attr("style", "width:" + tableWidth + "px !important;");
    // $(table).attr("style", "width:100px !important;");
    $(table).css({
	"left" : $(tableSelector).closest(":last-child .dataTables_scrollBody").width() - tableWidth - 2,
	"position" : "absolute",
	"top" : 0,
	"background-color" : "#F5F5F5",
    });
    // add the table
    $(tableSelector).closest(":last-child .dataTables_scroll").append(table);
    // bind the scroll event on scroll panel
    $(tableSelector).closest(":last-child .dataTables_scrollBody").on("scroll", function() {
	// don't move. Stay at your position
	var fixedColumnsTable = $(this).parent().find('.fixedColumnsTable');
	var leftOffset = $(this).width() - tableWidth - 1;
	$(fixedColumnsTable).css({
	    'left' : leftOffset
	});
    });
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
// *** This code is copyright 2002-2016 by Gavin Kistner, !@phrogz.net
// *** It is covered under the license viewable at
// http://phrogz.net/JS/_ReuseLicense.txt
Date.prototype.customFormat = function(formatString) {
    var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhhh, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
    YY = ((YYYY = this.getFullYear()) + "").slice(-2);
    MM = (M = this.getMonth() + 1) < 10 ? ('0' + M) : M;
    MMM = (MMMM = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ][M - 1]).substring(0, 3);
    DD = (D = this.getDate()) < 10 ? ('0' + D) : D;
    DDD = (DDDD = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ][this.getDay()]).substring(0, 3);
    th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st' : (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
    formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY).replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM).replace("#M#", M).replace("#DDDD#", DDDD).replace(
	    "#DDD#", DDD).replace("#DD#", DD).replace("#D#", D).replace("#th#", th);
    h = (hhh = this.getHours());
    if (h == 0)
	h = 24;
    if (h > 12)
	h -= 12;
    hh = h < 10 ? ('0' + h) : h;
    hhhh = hhh < 10 ? ('0' + hhh) : hhh;
    AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
    mm = (m = this.getMinutes()) < 10 ? ('0' + m) : m;
    ss = (s = this.getSeconds()) < 10 ? ('0' + s) : s;
    return formatString.replace("#hhhh#", hhhh).replace("#hhh#", hhh).replace("#hh#", hh).replace("#h#", h).replace("#mm#", mm).replace("#m#", m).replace("#ss#", ss).replace("#s#", s).replace(
	    "#ampm#", ampm).replace("#AMPM#", AMPM);
};

function generateAuthorizedButtonGroup(buttons) {
    var html = [];
    var availableButtons = [];
    for (i = 0; i < buttons.length; i++) {
	var button = buttons[i];
	if (button != undefined && hasAuthority(button.authorityName)) {
	    availableButtons.push(button);
	}
    }
    if (availableButtons.length == 1) {
	var button = availableButtons[0];
	html.push('<a href="' + availableButtons[0].url + '" data-id="' + button.data_id + '" class="btn btn-default btn-xs ' + button.styleClass + '">' + availableButtons[0].label + '</a>');
    } else if (availableButtons.length > 1) {
	var button = availableButtons[0];
	html.push('<div class="btn-group">');
	html.push('<a href="' + button.url + '" data-id="' + button.data_id + '" class="btn btn-default btn-xs ' + button.styleClass + '">' + button.label + '</a>');
	html.push('<button type="button" class="btn btn-default dropdown-toggle btn-xs" data-toggle="dropdown">');
	html.push('<span class="caret"></span>');
	html.push('</button>');
	html.push('<ul class="dropdown-menu fixed" role="menu">');
	for (i = 1; i < availableButtons.length; i++) {
	    var button = availableButtons[i];
	    html.push('<li><a href="' + button.url + '" data-id="' + button.data_id + '" class="' + button.styleClass + '">' + button.label + '</a></li>');
	}
	html.push("</ul>");
	html.push('<div>');
    }
    return html.join('');
}

function hasAuthority(actionName) {
    return $("#" + actionName).val() == "true";
}