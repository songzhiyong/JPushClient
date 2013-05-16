package com.example.client;

import java.io.Serializable;
import java.util.HashMap;

//推送通知
public class PushNotification implements Serializable {

	private static final long serialVersionUID = -3549241657974454343L;
	private int nBuildId;
	private String nTitle;
	private String nContent;
	private HashMap<String, Object> nExtras;

	public PushNotification(int nBuildId, String nTitle, String nContent,
			HashMap<String, Object> nExtras) {
		super();
		this.nBuildId = nBuildId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nExtras = nExtras;
	}

	public int getnBuildId() {
		return nBuildId;
	}

	public void setnBuildId(int nBuildId) {
		this.nBuildId = nBuildId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public HashMap<String, Object> getnExtras() {
		return nExtras;
	}

	public void setnExtras(HashMap<String, Object> nExtras) {
		this.nExtras = nExtras;
	}

}
