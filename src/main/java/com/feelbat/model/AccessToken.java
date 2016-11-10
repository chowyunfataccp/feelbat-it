package com.feelbat.model;

import java.util.Date;



@ReqType("access_token")
public class AccessToken extends WeixinReqParam{

	/**
	 * 获取access_token填写client_credential
	 */
	private String grant_type="client_credential";
	
	/**
	 * 第三方用户唯一凭证
	 * feelbat :wx3594a2b351f5ff24
	 * test :wxcaabbd2b0f981821
	 */
	private String appid="wxcaabbd2b0f981821";
	
	/**
	 * 第三方用户唯一凭证密钥，即appsecret
	 * feelbat:2e4102822538ecaa44669d529aabcfbc
	 * test:657255f27ae5a55f440e9a27948c763d
	 */
	private String secret="657255f27ae5a55f440e9a27948c763d";
	
	private int expires_in;//凭证有效时间
	private Date addTime;//添加时间

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	 
}
