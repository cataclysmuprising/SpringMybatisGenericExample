/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.springMybatisGenericExample.core.bean.master;

import com.mycom.products.springMybatisGenericExample.core.bean.BaseBean;

public class StaticContentBean extends BaseBean {
	private static final long serialVersionUID = -5890442627766482224L;
	private String fileName;
	private String filePath;
	private String fileSize;
	private FileType fileType;

	public enum FileType {
		IMAGE, TEXT, PDF, EXCEL, ZIP, UNKNOWN;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return String.format("StaticContentBean {fileName=%s, filePath=%s, fileSize=%s, fileType=%s, ID=%s}", fileName, filePath, fileSize, fileType, getId());
	}

}
