package com.feelbat.service;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.jdom.JDOMException;

import com.feelbat.model.AccessToken;
import com.feelbat.model.ReqType;
import com.feelbat.model.WeiXinReqHandler;
import com.feelbat.model.WeixinReqConfig;
import com.feelbat.model.WeixinReqParam;
import com.feelbat.util.JwTokenAPI;
import com.feelbat.util.WeiXinReqUtil;
import com.feelbat.util.WexinReqException;

/**
 * 获取微信接口的信息
 * 
 * @author liguo
 * 
 */
public class WeiXinReqService {

	private static WeiXinReqService weiXinReqUtil = null;


	private WeiXinReqService() {
		try {
			WeiXinReqUtil.initReqConfig("weixin-reqcongfig.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 获取公共的调用处理
	 * @return
	 */
	public static WeiXinReqService getInstance() {
		if (weiXinReqUtil == null) {
			// 同步块，线程安全的创建实例
			synchronized (WeiXinReqService.class) {
				// 再次检查实例是否存在，如果不存在才真正的创建实例
				if (weiXinReqUtil == null) {
					weiXinReqUtil = new WeiXinReqService();
				}
			}
		}
		return weiXinReqUtil;
	}

	/**
	 * 传入请求的参数，获取对应额接口返回信息
	 * @param weixinReqParam
	 * @return
	 * @throws WexinReqException
	 */
	public String doWeinxinReq(WeixinReqParam weixinReqParam)
			throws WexinReqException {
		String strReturnInfo = "";
		if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
			ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			WeiXinReqHandler handler = WeiXinReqUtil.getMappingHander(objConfig.getMappingHandler());
			strReturnInfo = handler.doRequest(weixinReqParam);
		}
		return strReturnInfo;
	}
	
	/**
	 * 返回json对象
	 * @param weixinReqParam
	 * @return
	 * @throws WexinReqException 
	 */
	public JSONObject doWeinxinReqJson(WeixinReqParam weixinReqParam) throws WexinReqException{
		AccessToken accessToken = JwTokenAPI.getAccessToken();
		weixinReqParam.setAccess_token(accessToken.getAccess_token());
		String strResult = this.doWeinxinReq(weixinReqParam);
		JSONObject result = JSONObject.fromObject(strResult);
		return result;
	}
	
	/**
	 * 返回json对象
	 * @param weixinReqParam
	 * @return
	 * @throws WexinReqException 
	 */
	public JSONObject doWeinxinReqJsonWithoutToken(WeixinReqParam weixinReqParam) throws WexinReqException{
		String strResult = this.doWeinxinReq(weixinReqParam);
		JSONObject result = JSONObject.fromObject(strResult);
		return result;
	}

	
}
