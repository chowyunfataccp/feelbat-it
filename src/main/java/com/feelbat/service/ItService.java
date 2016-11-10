package com.feelbat.service;

import java.util.List;

import com.feelbat.model.WxQrcode;

public interface ItService {
	/*
	public GroupCreate createGroup(String groupName); 
	public List<Group> getAllGroup();
	public String removeGroup(String id);
	*/
	WxQrcode createQrcode(int scene_id);

}
