package com.itheima45.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itheima45.application.utils.LogUtils;
import com.itheima45.bean.SubjectInfo;

public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>> {

	@Override
	protected String getKey() {
		return "subject";
	}

	@Override
	protected List<SubjectInfo> parseFromJson(String json) {
		try {
			JSONArray array = new JSONArray(json);
			List<SubjectInfo> list = new ArrayList<SubjectInfo>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.optJSONObject(i);
				SubjectInfo info = new SubjectInfo();
				info.setDes(obj.optString("des"));
				info.setUrl(obj.optString("url"));
				list.add(info);
			}
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}
}
