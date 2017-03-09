var imagePath;
var url = window.location.href;
var cropBoxData;
var canvasData;
var $image;
function init() {
    // loadImage();
    $("#profile-img").attr("src", getContextPath() + "/images/avatar/guest.jpg");
    $('#profileImageCropper').on('shown.bs.modal', function() {
	$("#selectedImage").removeAttr("style");
	$("#selectedImage").attr("src", getContextPath() + "/images/avatar/guest.jpg");
    }).on('hidden.bs.modal', function() {
	cropBoxData = $image.cropper('getCropBoxData');
	canvasData = $image.cropper('getCanvasData');
	$image.cropper('destroy');
    });
    $('#editImage').on('click', function(e) {
	$('#profileImageCropper').modal('show');
    });
    $("#btnSetGravatorImage").on('click', function(e) {
	var count = 0;
	var email = new Date().getTime() + "@gmail.com";
	imagePath = 'http://www.gravatar.com/avatar/' + md5(email);
	var gravatarUrl = imagePath + "?s=128&d=identicon&r=PG&f=1";
	$('#selectedImage').attr('src', gravatarUrl);
	$('#selectedImage').error(function() {
	    if (count < 3) {
		count++;
		loadImage();
	    } else {
		alert("Can't retrieve avator image.Check your internet connection.");
		$("#selectedImage").attr("src", getContextPath() + "/images/avatar/guest.jpg");
	    }
	});
    });
    $("#btnConfirmImage").on('click', function(e) {
	var croppedCanvas;
	var roundedCanvas;
	croppedCanvas = $image.cropper('getCroppedCanvas');
	roundedCanvas = getRoundedCanvas(croppedCanvas);
	$("#profile-img").attr("src", roundedCanvas.toDataURL());
	$('#profileImageCropper').modal('hide');
    });
    $("#profilePhotoFileInput").change(function(e) {
	var fileReader = new FileReader();
	fileReader.onload = function(e) {
	    var img = new Image();
	    img.onload = function() {
		var MAX_WIDTH = 800;
		var MAX_HEIGHT = 600;
		var width = img.width;
		var height = img.height;

		if (width > height) {
		    if (width > MAX_WIDTH) {
			height *= MAX_WIDTH / width;
			width = MAX_WIDTH;
		    }
		} else {
		    if (height > MAX_HEIGHT) {
			width *= MAX_HEIGHT / height;
			height = MAX_HEIGHT;
		    }
		}

		var canvas = document.createElement("canvas");
		canvas.width = width;
		canvas.height = height;
		canvas.getContext("2d").drawImage(this, 0, 0, width, height);
		this.src = canvas.toDataURL();
		this.id = "imageHolder";
		console.log(img);
		// document.body.appendChild(this);// remove this if you don't
		// want
		// to show it
	    }
	    img.src = e.target.result;
	}
	fileReader.readAsDataURL(e.target.files[0]);
	fileReader.onloadend = function() {
	    initCropper();
	};
	// readURL(this);
    });
    $("#btnUploadImage").on('click', function(e) {
	$("#profilePhotoFileInput").trigger("click");
    });
}
function readURL(input) {
    var imgPath = $(input)[0].value;
    var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
    var image_holder = $("#imageHolder");
    image_holder.removeAttr("src");
    if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
	if (typeof (FileReader) != "undefined") {
	    // loop for each file selected for uploaded.
	    var reader = new FileReader();
	    reader.onload = function(e) {
		var img = $("<img />", {
		    "id" : "imageHolder",
		    "src" : e.target.result,
		    "width" : "600px",
		    "height" : "300px",
		    "style" : "display:none"
		});
		$(image_holder).replaceWith(img);
	    }
	    // image_holder.show();
	    reader.readAsDataURL($(input)[0].files[0]);
	    reader.onloadend = function() {
		initCropper();
	    };
	} else {
	    alert("Your browser seems very old. Please update your browser version to support.");
	}
    } else {
	alert("Please select only images");
    }
}
function initCropper() {
    $image = $('#imageHolder');
    var $button = $('#uploadProfilePhoto');
    var $previews = $('.selected_image_panel');
    $image.cropper({
	aspectRatio : 1,
	viewMode : 1,
	autoCropArea : 0.5,
	ready : function(e) {
	    $image.cropper('setCanvasData', canvasData);
	    $image.cropper('setCropBoxData', cropBoxData);
	    var $clone = $(this).clone().removeClass('cropper-hidden').addClass("img-responsive img-thumbnail img-circle").attr("id", "selectedImage");
	    $clone.css({
		display : 'block',
		width : '100%',
		minWidth : 0,
		minHeight : 0,
		maxWidth : 'none',
		maxHeight : 'none'
	    });

	    $previews.html($clone);
	},

	crop : function(e) {
	    var imageData = $(this).cropper('getImageData');
	    var previewAspectRatio = e.width / e.height;

	    $previews.each(function() {
		var $preview = $(this);
		var previewWidth = $preview.width();
		var previewHeight = previewWidth / previewAspectRatio;
		var imageScaledRatio = e.width / previewWidth;

		$preview.height(previewHeight).find('img').css({
		    width : imageData.naturalWidth / imageScaledRatio,
		    height : imageData.naturalHeight / imageScaledRatio,
		    marginLeft : -e.x / imageScaledRatio,
		    marginTop : -e.y / imageScaledRatio
		});
	    });
	}
    });

    $button.on('click', function(e) {
	var croppedCanvas;
	var roundedCanvas;
	croppedCanvas = $image.cropper('getCroppedCanvas');
	roundedCanvas = getRoundedCanvas(croppedCanvas);
	// $result.html('<img src="' + roundedCanvas.toDataURL() + '">');
	uploadProfilePicture(roundedCanvas.toDataURL());
	e.preventDefault();
	return false;
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

function uploadProfilePicture(imageData) {
    var data = new FormData();
    var blob = dataURItoBlob(imageData);
    data.append("profileImage", blob);
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

function dataURItoBlob(dataURI) {
    // convert base64 to raw binary data held in a string
    // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that
    // does this
    var byteString = atob(dataURI.split(',')[1]);

    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]

    // write the bytes of the string to an ArrayBuffer
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++) {
	ia[i] = byteString.charCodeAt(i);
    }

    // write the ArrayBuffer to a blob, and you're done
    var blob = new Blob([ ab ], {
	type : mimeString
    });
    return blob;
}

function loadImage() {
    window.setTimeout(function() {
	var imagePath = $("#profile-img").attr("src");
	$("#profile-img").attr("src", imagePath);
    }, 500);
}

function getRoundedCanvas(sourceCanvas) {
    var canvas = document.createElement('canvas');
    var context = canvas.getContext('2d');
    var width = sourceCanvas.width;
    var height = sourceCanvas.height;

    canvas.width = width;
    canvas.height = height;
    context.beginPath();
    context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI);
    context.strokeStyle = 'rgba(0,0,0,0)';
    context.stroke();
    context.clip();
    context.drawImage(sourceCanvas, 0, 0, width, height);

    return canvas;
}