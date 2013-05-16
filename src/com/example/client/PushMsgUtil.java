package com.example.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.jpush.api.StringUtils;

/**
 * 调用远程API实现推送
 * 
 * 
 */
public class PushMsgUtil {

	public static final String PUSH_URL = "http://api.jpush.cn:8800/sendmsg/sendmsg";
	// 用户名
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	// 发送编号 标志一次发送请求
	public static final String SEND_NO = "sendno";
	// 应用程序(appKey)
	public static final String APPKEYS = "appkeys";
	/*
	 * 接受者类型 1.指定IMEI设备发送 2.指定标签发送 3.指定别名发送 4.向该应用的全部用户推送。
	 */
	public static final String RECEIVER_TYPE = "receiver_type";
	public static final int RECEIVER_TYPE_IMEI = 1;
	public static final int RECEIVER_TYPE_TAG = 2;
	public static final int RECEIVER_TYPE_ALIAS = 3;
	public static final int RECEIVER_TYPE_DEFAULT = 4;
	// 验证串，用于校验发送的合法性。
	//
	// 由 sendno, receiver_type, receiver_value, master_secret
	// 等几个值拼接起来（直接拼接字符串）后，进行一次MD5生成。
	public static final String VERIFICATION_CODE = "verification_code";
	// 发送消息的类型：1 通知 2 自定义
	public static final String MSG_TYPE = "msg_type";
	public static final int MSG_TYPE_NOTI = 1;
	public static final int MSG_TYPE_CUSTOM = 2;
	// msg_content
	public static final String MSG_CONTENT = "msg_content";

	public static void pushMsg(String msg) {
		String username = null;
		String password = null;
		int sendNo = 0;
		String appKey = null;
		String msgType = null;
		try {
			InputStream is = new FileInputStream("conf.properties");
			Properties p = new Properties();
			p.load(is);
			is.close();
			username = p.getProperty(USER_NAME);
			password = p.getProperty(PASSWORD);
			sendNo = Integer.parseInt(p.getProperty(SEND_NO));
			appKey = p.getProperty(APPKEYS);
			msgType = p.getProperty(MSG_TYPE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BasicNameValuePair name = new BasicNameValuePair(USER_NAME, username);
		BasicNameValuePair sendno = new BasicNameValuePair(SEND_NO,
				String.valueOf(sendNo));
		BasicNameValuePair appkeys = new BasicNameValuePair(APPKEYS, appKey);
		BasicNameValuePair receiver_type = new BasicNameValuePair(
				RECEIVER_TYPE, String.valueOf(RECEIVER_TYPE_DEFAULT));
		BasicNameValuePair verification_code = new BasicNameValuePair(
				VERIFICATION_CODE, getVerificationCode(username, password,
						sendNo, RECEIVER_TYPE_DEFAULT));
		BasicNameValuePair msg_type = new BasicNameValuePair(MSG_TYPE, msgType);
		BasicNameValuePair msg_content = new BasicNameValuePair(MSG_CONTENT,
				msg);
		BasicNameValuePair platform = new BasicNameValuePair("platform",
				"android");
		List<BasicNameValuePair> datas = new ArrayList<BasicNameValuePair>();
		datas.add(name);
		datas.add(sendno);
		datas.add(appkeys);
		datas.add(receiver_type);
		datas.add(verification_code);
		datas.add(msg_type);
		datas.add(msg_content);
		datas.add(platform);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(datas, "utf-8");
			HttpPost post = new HttpPost(PUSH_URL);
			post.setEntity(entity);
			HttpClient client = ClientUtil.getNewHttpClient();
			HttpResponse reponse = client.execute(post);
			HttpEntity resEntity = reponse.getEntity();
			System.out.println(EntityUtils.toString(resEntity));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static String getVerificationCode(String username, String password,
			int sendno, int receiverType) {
		String md5Password = StringUtils.toMD5(password);
		String input = username + sendno + receiverType + md5Password;
		String verificationCode = StringUtils.toMD5(input);
		return verificationCode;
	}

	public static void main(String[] args) {
		// SendUI sendUI = new SendUI();
		String msg = "{\"n_builder_id\":2,\"n_title\":\"来点外卖\",\"n_content\":\"你好\"}";
		// String msgCustom = "{\"message\":\"hello\",\"title\":\"Hello\"}";
		System.out.println(msg);
		PushMsgUtil.pushMsg(msg);
	}
}