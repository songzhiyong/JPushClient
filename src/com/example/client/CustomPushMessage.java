package com.example.client;

import java.io.Serializable;
import java.util.HashMap;

public class CustomPushMessage implements Serializable {
	private static final long serialVersionUID = -8227704155785369974L;
	private String message;
	private int contentType;
	private String title;
	private HashMap<String, Object> extras;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HashMap<String, Object> getExtras() {
		return extras;
	}

	public void setExtras(HashMap<String, Object> extras) {
		this.extras = extras;
	}

}
