package com.itheima45.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.itheima45.application.utils.LogUtils;
import com.itheima45.bean.UserInfo;

public class UserProtocol extends BaseProtocol<List<UserInfo>> {

	@Override
	protected String getKey() {
		return "user";
	}

	@Override
	protected List<UserInfo> parseFromJson(String json) {
		try {
			List<UserInfo> list = new ArrayList<UserInfo>();
			JSONObject obj = new JSONObject(json);
			UserInfo info = new UserInfo();
			info.setName(obj.getString("name"));
			info.setEmail(obj.getString("email"));
			info.setUrl(obj.getString("url"));
			list.add(info);
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}
}
