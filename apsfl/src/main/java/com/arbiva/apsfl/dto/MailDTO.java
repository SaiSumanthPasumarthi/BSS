package com.arbiva.apsfl.dto;

import java.io.Serializable;

public class MailDTO implements Serializable {
	
	private static final long serialVersionUID = -239999342270059166L;
	
	
	private String from;
	private String to;
	private byte[] file;
	private String subject;
	private String msg;
	private String fileName;
	
	
	

	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
