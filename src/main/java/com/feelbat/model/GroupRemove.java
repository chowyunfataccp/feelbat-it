package com.feelbat.model;


/**
 * 取多媒体文件
 * 
 * @author sfli.sir
 * 
 */
@ReqType("groupRemove")
public class GroupRemove extends WeixinReqParam {

	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
