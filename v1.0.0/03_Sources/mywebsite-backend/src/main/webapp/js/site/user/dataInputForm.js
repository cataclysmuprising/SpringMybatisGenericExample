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
    // data.append('profileImage',
    // dataURItoBlob($('.image-editor').cropit('export')));
    var blob = dataURLToBlob($('.image-editor').cropit('export'));
    // $.each(jQuery('.cropit-image-input')[0].files, function(i, file) {
    // data.append('file-' + i, file);
    // });
    data.append("file-1", blob);
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

function dataURLToBlob(dataURL) {
    var BASE64_MARKER = ';base64,';
    if (dataURL.indexOf(BASE64_MARKER) == -1) {
	var parts = dataURL.split(',');
	var contentType = parts[0].split(':')[1];
	var raw = parts[1];

	return new Blob([ raw ], {
	    type : contentType
	});
    } else {
	var parts = dataURL.split(BASE64_MARKER);
	var contentType = parts[0].split(':')[1];
	var raw = window.atob(parts[1]);
	var rawLength = raw.length;

	var uInt8Array = new Uint8Array(rawLength);

	for (var i = 0; i < rawLength; ++i) {
	    uInt8Array[i] = raw.charCodeAt(i);
	}

	return new Blob([ uInt8Array ], {
	    type : contentType
	});
    }
}

function dataURItoBlob2(dataURI) {
    // convert base64 to raw binary data held in a string
    // doesn't handle URLEncoded DataURIs
    var byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
	byteString = atob(dataURI.split(',')[1]);
    else
	byteString = unescape(dataURI.split(',')[1]);
    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

    // write the bytes of the string to an ArrayBuffer
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++) {
	ia[i] = byteString.charCodeAt(i);
    }

    // write the ArrayBuffer to a blob, and you're done
    return new Blob([ ab ], {
	type : mimeString
    });
}
