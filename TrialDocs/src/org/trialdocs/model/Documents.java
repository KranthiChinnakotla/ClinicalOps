package org.trialdocs.model;

import java.io.InputStream;
import java.sql.Date;

public class Documents {

	private String email;
	private InputStream inputStream;
	private String fileName;
	private String contenttype;

	public Documents() {
		super();
	}

	public Documents(String email,InputStream inputStream, String fileName,String contenttype) {
		super();
		this.email = email;
		this.inputStream = inputStream;
		this.fileName = fileName;
		this.contenttype = contenttype;
	}

	

	

	@Override
	public String toString() {
		return "Documents [email=" + email + ", inputStream=" + inputStream + ", fileName=" + fileName + ", contentType="
				+ contenttype + "]";
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
