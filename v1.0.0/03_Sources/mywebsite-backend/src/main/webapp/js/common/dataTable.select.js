var chosenItems = "";
function bindTableSelect(tableId, beanAttributeName) {
    $("." + tableId + " .select_all_header").click(function(e) {
	if ($("." + tableId + " tbody tr").first().children("td").length == 1) {
	    e.preventDefault();
	    return;
	}
	var checkBox = $("." + tableId + " input.select_all");
	if ($(checkBox).prop('checked')) {
	    $("." + tableId + " tbody input[type=checkbox]").prop('checked', false);
	    $("." + tableId + " tbody tr").removeClass("selected_row");
	    $(checkBox).prop('checked', false);
	    var selectedItems = $("#" + tableId + "_selectedItems").val().split(",");
	    $.each($("." + tableId + " tbody input[type=checkbox]"), function(index, checkBox) {
		selectedItems = $.grep(selectedItems, function(value) {
		    return value != $(checkBox).attr("data-id");
		});
	    });
	    $("#" + tableId + "_selectedItems").val(selectedItems);
	    if ($(".switch input").is(':checked')) {
		$("." + tableId + ":not(.fixedColumnsTable)").DataTable().draw();
	    }
	} else {
	    $("." + tableId + " tbody input[type=checkbox]").prop('checked', true);
	    $("." + tableId + ":first-child tbody tr").addClass("selected_row");
	    $(checkBox).prop('checked', true);
	    var selectedItems = $("#" + tableId + "_selectedItems").val().split(",");
	    $.each($("." + tableId + " tbody input[type=checkbox]"), function(index, checkBox) {
		// selectedItems.push($(checkBox).attr("data-id"));
		addSelectedId(selectedItems, $(checkBox).attr("data-id"));
	    });
	    $("#" + tableId + "_selectedItems").val(selectedItems);
	}
    });

    $("." + tableId + " input.select_all").click(function(e) {
	if ($("." + tableId + " tbody tr").first().children("td").length == 1) {
	    e.preventDefault();
	    return;
	}
	if ($(this).prop('checked')) {
	    $("." + tableId + " tbody input[type=checkbox]").prop('checked', false);
	    $("." + tableId + " tbody tr").removeClass("selected_row");
	    $(this).prop('checked', false);
	} else {
	    $("." + tableId + " tbody input[type=checkbox]").prop('checked', true);
	    $("." + tableId + " tbody tr").addClass("selected_row");
	    $(this).prop('checked', true);
	}
    });
    $("." + tableId + " tbody").on('click', 'tr', function(e) {
	if ($(this).children("td").length == 1) {
	    return;
	}
	$(this).toggleClass("selected_row");
	var checkbox = $(this).find('input[type=checkbox]').prop('checked', function(_, checked) {
	    return !checked;
	});

	// for save selectedItems
	var selectedItems = $("#" + tableId + "_selectedItems").val().split(",");
	if ($(checkbox).prop('checked') == true) {
	    // selectedItems.push($(checkbox).attr("data-id"));
	    addSelectedId(selectedItems, $(checkbox).attr("data-id"));
	    $("#" + tableId + "_selectedItems").val(selectedItems);
	} else {
	    selectedItems = $.grep(selectedItems, function(value) {
		return value != $(checkbox).attr("data-id");
	    });
	    $("#" + tableId + "_selectedItems").val(selectedItems);
	}
	markSelectAllCheckBox(tableId);
    });
    addHiddenIdList(tableId, beanAttributeName);
}
function removeRow(tableId, dataId) {
    $("." + tableId + " .select_row:checked").trigger("click");
    var selectedItems = $("#" + tableId + "_selectedItems").val().split(",");
    selectedItems = $.grep(selectedItems, function(value) {
	return value != dataId;
    });
    $("#" + tableId + "_selectedItems").val(selectedItems);
}
function callBackAfterDataLoad(tableId, itemCounts) {
    if (itemCounts == 0) {
	$("." + tableId + " .select_all").hide();
	return;
    }
    $("." + tableId + " .select_all").show();
    $("." + tableId + " input.select_all").prop('checked', false);
    $("." + tableId + ".select_all").show();

    selectDefaultRows(tableId, $("#" + tableId + "_selectedItems").val());

    $("." + tableId + " input.select_row").on('click', function(e) {
	markSelectAllCheckBox(tableId);
	$(this).prop('checked', function(_, checked) {
	    return !checked;
	});
    });
}
function markSelectAllCheckBox(tableId) {
    var totalCheckBoxes = $("." + tableId + " input.select_row").length;
    var selectedCheckBoxes = $("." + tableId + " input.select_row:checked").length;
    if (totalCheckBoxes == selectedCheckBoxes) {
	$("." + tableId + " input.select_all").prop('checked', true);
    } else {
	$("." + tableId + " input.select_all").prop('checked', false);
    }
}

function selectDefaultRows(tableId, defaultSelections) {
    $("." + tableId + " input.select_all").prop('checked', false);
    $("." + tableId + " tbody input[type=checkbox]").prop('checked', false);
    $("." + tableId + " tbody tr").removeClass("selected_row");
    if (defaultSelections) {
	defaultSelections = defaultSelections.split(",");
	$.each(defaultSelections, function(index, item) {
	    var checkBox = $("." + tableId).find('input.select_row[data-id="' + item.trim() + '"]');
	    checkBox.prop('checked', true);
	    $(checkBox).closest("tr").addClass("selected_row");
	});
    }
    markSelectAllCheckBox(tableId);
}

function addHiddenIdList(tableId, beanAttributeName) {
    $("." + tableId).after('<input type="hidden" id="' + tableId + '_selectedItems" class="selected_items" autocomplete="off"/>');
    // fixed for firefox refresh bug
    if (beanAttributeName && $('#' + beanAttributeName).val() != "") {
	$("#" + tableId + '_selectedItems').val($('#' + beanAttributeName).val());
    } else {
	$("#" + tableId + '_selectedItems .selected_items').val("");
    }
}

function saveSelectedItems(sourceTable, destinationTable, beanAttributeName) {
    if ($("#" + sourceTable + "_selectedItems") && $("#" + destinationTable + "_selectedItems")) {
	$("#" + destinationTable + "_selectedItems").val($("#" + sourceTable + "_selectedItems").val());
	var selectedItems = [];
	var str = $("#" + destinationTable + "_selectedItems").val();
	if (str.match("^,")) {
	    // do this if begins with ','
	    selectedItems = str.substring(1, str.length).split(',');
	} else {
	    selectedItems = str.split(',');
	}

	$("#" + beanAttributeName).val(selectedItems);
    }
}

function getSelectedItems(tableId) {
    if (!$("#" + tableId + "_selectedItems").val()) {
	return "-1";
    }
    var str = $("#" + tableId + "_selectedItems").val();
    if (str.match("^,")) {
	// do this if begins with ','
	str = str.substring(1, str.length);
    }
    return str;
}

function clearSelectedItems(tableId) {
    $("#" + tableId + "_selectedItems").val('');
}

function addSelectedId(idList, id) {
    if ($.inArray(id, idList) == -1) {
	idList.push(id);
    }
}