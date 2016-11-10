package com.feelbat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.feelbat.model.AccessToken;
import com.feelbat.service.WeiXinReqService;


/**
 * 微信--token信息
 * 
 * @author lizr
 * 
 */
public class JwTokenAPI {
	private static AccessToken accessToken;
	public static void main(String[] args){
		try {
			while(true){
				Thread.sleep(3*1000);
				accessToken = JwTokenAPI.getAccessToken();
				String k = accessToken.getAccess_token();
				Date d = accessToken.getAddTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				int e = accessToken.getExpires_in();
				
				System.out.println(accessToken.getAccess_token() +k+ "\t" +sdf.format(d) + "\t" + e );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AccessToken getAccessToken() throws WexinReqException{
		boolean test = false;
		if(test){
			accessToken = new AccessToken();
			accessToken.setAccess_token("7baFxE9eBK1CSLgvMwOyTnxIvIYLoevb_lFMv6O8dg0tFLz-msOdoidBbKzlEpM5s0IUUaAdN6Y9AVNfieFHns44wgfk-E7n6SDbKmmhq7xErtSxdfdzVvR1ejsoptZyLKOjAIASRV");
			accessToken.setAddTime(new Date());
			return accessToken;
		}
		boolean refresh = false;
		if(accessToken == null){
			accessToken = new AccessToken();
			refresh = true;
		}else{
    		java.util.Date end = new java.util.Date();
    		java.util.Date start = accessToken.getAddTime();
        	if(end.getTime()-start.getTime()>=accessToken.getExpires_in()*1000){
//    		if(end.getTime()-start.getTime()>=10*1000){ //fortest
        		refresh = true;
        	}
		}
		if(refresh){
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJsonWithoutToken(accessToken);
			// 正常返回
			Object error = result.get("errcode");
			if (error == null) {
				String tokenstr = result.getString("access_token");
				int ex = result.getInt("expires_in");
				accessToken = new AccessToken();
				accessToken.setAccess_token(tokenstr);
				accessToken.setExpires_in(ex);
				accessToken.setAddTime(new Date());
			}
		}
		return accessToken;
	}
	
}
