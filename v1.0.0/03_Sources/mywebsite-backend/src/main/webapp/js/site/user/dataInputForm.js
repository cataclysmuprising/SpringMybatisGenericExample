var imagePath;
var url = window.location.href;
var cropBoxData;
var canvasData;
var $image;
function init() {
    /* Handle image resized events */
    $(document).on("imageResized", function(event) {
	if (event.url) {
	    var image = new Image();
	    $("#imageHolder").attr("src", event.url);
	    $(".cropArea,.docs-toolbar").show();
	    initCropper();
	}
    });
    $(".docs-toolbar").on("click", "[data-method]", function() {
	var data = $(this).data();

	if (data.method) {
	    $image.cropper(data.method, data.option);
	}
    });
    $(".docs-toolbar .download").click(function() {
	if ($image) {
	    croppedCanvas = $image.cropper('getCroppedCanvas');
	    window.open(croppedCanvas.toDataURL());
	}
    });
    // loadImage();
    $("#profile-img").attr("src", getContextPath() + "/images/avatar/guest.jpg");
    $('#profileImageCropper').on('shown.bs.modal', function() {
	$("#selectedImage").addClass("img-circle");
	$(".cropArea,.docs-toolbar").hide();
	$("#selectedImage").removeAttr("style");
	$("#selectedImage").attr("src", getContextPath() + "/images/avatar/guest.jpg");
    }).on('hidden.bs.modal', function() {
	if ($image) {
	    cropBoxData = $image.cropper('getCropBoxData');
	    canvasData = $image.cropper('getCanvasData');
	    $image.cropper('destroy');
	    $image = undefined;
	}
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
	    if (count < 2) {
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
	if ($image) {
	    croppedCanvas = $image.cropper('getCroppedCanvas');
	    $("#profile-img").attr("src", croppedCanvas.toDataURL());
	} else {
	    $("#profile-img").attr("src", $('#selectedImage').attr('src'));
	}
	$('#profileImageCropper').modal('hide');
    });
    $("#profilePhotoFileInput").change(function(event) {
	var file = event.target.files[0];
	// Ensure it's an image
	if (file.type.match(/image.*/)) {
	    // Load the image
	    if (typeof (FileReader) != "undefined") {
		var reader = new FileReader();
		reader.onload = function(readerEvent) {
		    var image = new Image();
		    image.onload = function(imageEvent) {
			// Resize the image
			var canvas = document.createElement('canvas'), max_size = 544, width = image.width, height = image.height;
			if (width > height) {
			    if (width > max_size) {
				height *= max_size / width;
				width = max_size;
			    }
			} else {
			    if (height > max_size) {
				width *= max_size / height;
				height = max_size;
			    }
			}
			canvas.width = width;
			canvas.height = height;
			canvas.getContext('2d').drawImage(image, 0, 0, width, height);
			var dataUrl = canvas.toDataURL('image/jpeg');
			$.event.trigger({
			    type : "imageResized",
			    url : dataUrl
			});
		    }
		    image.src = readerEvent.target.result;
		}
		reader.readAsDataURL(file);
	    } else {
		alert("Your browser seems very old. Please update your browser version to support.");
	    }
	} else {
	    alert("Please select only Images.");
	}
    });
    $("#btnUploadImage").on('click', function(e) {
	$("#profilePhotoFileInput").trigger("click");
    });
}
function initCropper() {
    $image = $('#imageHolder');
    var $button = $('#uploadProfilePhoto');
    var $previews = $('.preview');
    $image.cropper({
	aspectRatio : 1,
	viewMode : 1,
	autoCropArea : 0.5,
	ready : function(e) {
	    $image.cropper('setCanvasData', canvasData);
	    $image.cropper('setCropBoxData', cropBoxData);
	    var $clone = $(this).clone().removeClass('cropper-hidden').addClass("img-responsive img-thumbnail ").attr("id", "selectedImage");
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
	uploadProfilePicture(croppedCanvas.toDataURL());
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
    data.append("imageFile", blob);
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