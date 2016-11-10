package com.feelbat.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.feelbat.model.Group;
import com.feelbat.model.GroupCreate;
import com.feelbat.model.GroupGet;
import com.feelbat.model.GroupRemove;
import com.feelbat.model.QrcodeActionInfo;
import com.feelbat.model.QrcodeCreate;
import com.feelbat.model.QrcodeScene;
import com.feelbat.model.WxQrcode;
import com.feelbat.util.WeiXinConstant;
import com.feelbat.util.WexinReqException;

@Service("itService")
public class ItServiceImpl implements ItService {

private Logger logger = Logger.getLogger(ItServiceImpl.class);
	/*
	@Override
	public List<Group> getAllGroup() {
		List<Group> lstGroup = null;
		try {
			GroupGet c = new GroupGet();
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(c);
			Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			if(error == null){
				JSONArray infoArray = result.getJSONArray("groups");
				lstGroup = new ArrayList<Group>(infoArray.size());
				for(int i=0;i<infoArray.size();i++){
					lstGroup.add((Group)JSONObject.toBean(infoArray.getJSONObject(i), Group.class));
				}
			}
		} catch (WexinReqException e) {
			logger.error(e.getMessage());
		}
		return lstGroup;
	}

	@Override
	public GroupCreate createGroup(String groupName) {
		JSONObject result = null;
		try {
			GroupCreate c = new GroupCreate();
			Group g = new Group();
			g.setName(groupName);
			c.setGroup(g);
			
			result = WeiXinReqService.getInstance().doWeinxinReqJson(c);
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
		GroupCreate groupCreate = null;
		if(error == null){
			groupCreate = (GroupCreate) JSONObject.toBean(result, GroupCreate.class);
		}
		return groupCreate;
	}
	
	@Override
	public String removeGroup(String id) {
		JSONObject result = null;
		try{
			GroupRemove c = new GroupRemove();
			Group g = new Group();
			g.setId(id);
			c.setGroup(g);
			
			result = WeiXinReqService.getInstance().doWeinxinReqJson(c);
		}catch(WexinReqException e){
			logger.error(e.getMessage());
		}
		return result.getString("errmsg");
	}
	
	*/

/**
 * 微信--生成二维码
 * 
 * 通过https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET可以换图片，那么返回的URL到底有什么作用
 * 
 * 对于永久二维码接口：
 * 无需传入过期时间，也没有返回过期时间（因为是永久有效）
 * 
 * 对于临时二维码接口：
 * 返回的过期时间与传入的一样，但最少60秒，若少于60秒，将忽略，并按照60秒输出
 * 若不传过期时间，也是按照60秒输出
 * 即便临时二维码过期，同样的参数均可以重新生产多次二维码，对应不同的图片
 * @author Administrator
 *
 */
	@Override
	public WxQrcode createQrcode(int scene_id) {
		WxQrcode wxQrcode = null;
		try{
			QrcodeCreate qrcodeCreate = new QrcodeCreate();
			QrcodeActionInfo q = new QrcodeActionInfo();
			QrcodeScene ss = new QrcodeScene();
			ss.setScene_id(String.valueOf(scene_id));
			q.setScene(ss);
			qrcodeCreate.setAction_info(q);
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeCreate);
			Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			if (error == null) {
				wxQrcode = (WxQrcode) JSONObject.toBean(result, WxQrcode.class);
			}
		}catch(WexinReqException e){
			logger.error(e);
		}
		return wxQrcode;
	}
	
}
