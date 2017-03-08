package com.mycom.products.mywebsite.backend.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.products.mywebsite.backend.EntryPoint;
import com.mycom.products.mywebsite.core.bean.master.StaticContentBean.FileType;

@Controller
@RequestMapping(value = "/ajax/upload")
public class UploadHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
	    throws ServletException, IOException {
	ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
	PrintWriter writer = response.getWriter();
	// checks if the request actually contains upload file
	if (!ServletFileUpload.isMultipartContent(request)) {
	    // if not, we stop here
	    writer.println("Error: Form must has enctype=multipart/form-data.");
	    writer.flush();
	    return;
	}
	JSONObject json = new JSONObject();
	SimpleDateFormat fmtYMD = new SimpleDateFormat("/" + "yyyyMMdd");
	Date today = new Date();
	String uploadPath = EntryPoint.getUploadPath() + "/";

	try {
	    List<FileItem> items = uploadHandler.parseRequest(request);
	    if (items != null && items.size() > 0) {
		String saveDir = "", fileCategory = "";
		for (FileItem item : items) {
		    if (item.isFormField()) {
			fileCategory = item.getString();
			saveDir = fileCategory + fmtYMD.format(today);
			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath + saveDir);
			if (!uploadDir.exists()) {
			    uploadDir.mkdirs();
			}
		    }
		}
		List<HashMap<String, String>> uploadFiles = new ArrayList<>();
		for (FileItem item : items) {
		    // processes only fields that are not form fields
		    if (!item.isFormField()) {
			if (saveDir.length() == 0) {
			    json.put("messageCode", "V1001");
			    json.put("messageParams", "File upload type");
			    json.put("status", HttpStatus.BAD_REQUEST);
			    response.setContentType("application/json");
			    writer.write(json.toString());
			    writer.flush();
			}
			String originalFileName = "", saveFileName = "", format = "", fileSize = "";
			try {
			    UUID uuid = UUID.randomUUID();
			    originalFileName = item.getName().substring(0, item.getName().lastIndexOf("."));
			    format = item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());
			    fileSize = getReadableFileSize(item.getSize());
			    saveFileName = new File(uuid.toString() + format).getName();
			} catch (Exception e) {
			    // Nothing to do. Skip
			}
			String filePath = uploadPath + saveDir + "/" + saveFileName;
			saveProfileImage(item, filePath);
			HashMap<String, String> fileItem = new HashMap<>();
			fileItem.put("originalFileName", originalFileName + format);
			fileItem.put("saveFileName", saveFileName);
			fileItem.put("filePath", filePath);
			fileItem.put("fileSize", fileSize);
			fileItem.put("fileType", getFileType(format));
			System.err.println("Reach Here ............");
			uploadFiles.add(fileItem);

		    }
		}
		json.put("uploadFiles", uploadFiles);
		json.put("status", HttpStatus.OK);
		response.setContentType("application/json");
		writer.write(json.toString());
		writer.flush();
	    }
	} catch (FileUploadException e) {
	    throw new RuntimeException("File upload Error !", e);
	} catch (Exception e) {
	    throw new RuntimeException("File upload Error !", e);
	} finally {
	    writer.close();
	}
    }

    private void saveProfileImage(FileItem item, String filePath) throws Exception {
	File storeFile = new File(filePath);
	// saves the file on disk
	item.write(storeFile);
    }

    private String getReadableFileSize(long size) {
	if (size <= 0)
	    return "0";
	final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
	return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private String getFileType(String fileExtension) {
	String format = fileExtension.toLowerCase().substring(1, fileExtension.length());
	if ("gif,jpg,jpeg,png".contains(format)) {
	    return FileType.IMAGE.name();
	} else if ("xls,xlsx".contains(format)) {
	    return FileType.EXCEL.name();
	} else if ("pdf".contains(format)) {
	    return FileType.PDF.name();
	} else if ("txt".contains(format)) {
	    return FileType.TEXT.name();
	} else if ("zip,tar,tar.gz".contains(format)) {
	    return FileType.ZIP.name();
	} else {
	    return FileType.UNKNOWN.name();
	}
    }
}
