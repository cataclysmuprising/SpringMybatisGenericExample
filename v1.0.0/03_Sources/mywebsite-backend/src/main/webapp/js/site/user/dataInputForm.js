var imagePath;
var url = window.location.href;
function init() {
    $('.image-editor').cropit({
	exportZoom : 1.25,
	imageBackground : true,
	imageBackgroundBorderWidth : 20,
    });

    $('.rotate-cw').click(function() {
	$('.image-editor').cropit('rotateCW');
    });
    $('.rotate-ccw').click(function() {
	$('.image-editor').cropit('rotateCCW');
    });

    $('.export').click(function() {
	uploadProfilePicture();
	// var imageData = $('.image-editor').cropit('export');
	// window.open(imageData);
    });
}

function bind() {
    $('[type="password"]').tooltip({
	title : "Password must be minimum of 8 characters with (at least 1 uppercase alphabet, 1 lowercase alphabet, 1 number and 1 special character)",
	trigger : "focus",
	placement : "top"
    });

    $("#dobInputGroup").datetimepicker({
	format : 'DD/MM/YYYY',
	showClear : true
    });

}

function uploadProfilePicture() {
    var data = new FormData();
    data.append('file-1', $('.image-editor').cropit('export'));
    data.append("type", "profilePicture");
    jQuery.ajax({
	url : getContextPath() + '/ajax/upload',
	data : data,
	cache : false,
	contentType : false,
	processData : false,
	type : 'POST',
	success : function(data) {
	    if (data.status == "200") {
		$.each(data.uploadFiles, function(index, fileItem) {
		    $('#profile_fileName').val(fileItem.originalFileName);
		    $('#profile_filePath').val(fileItem.filePath);
		    $('#profile_fileSize').val(fileItem.fileSize);
		    $('#profile_fileType').val(fileItem.fileType);
		});
	    } else {
		alert(data.result.errorMessage);
	    }
	}
    });
}
