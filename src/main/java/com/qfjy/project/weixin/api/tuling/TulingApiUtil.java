package com.qfjy.project.weixin.api.tuling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class TulingApiUtil {
	
	public String sendMessage(String inputMsg) {
		//接口地址
				String requestUrl = "http://openapi.tuling123.com/openapi/api/v2";
				
				TulingBean tulingbean = new TulingBean();
				//输入类型 0是文本信息
				tulingbean.setReqType(0);
				Perception perception = new Perception(); 
				InputText inputText = new InputText();
				
				tulingbean.setPerception(perception);
				perception.setInputText(inputText);
				//客户端发来的信息
				inputText.setText(inputMsg);
				UserInfo userInfo = new UserInfo();
				
				String key1 = "04007f7af95248b8abc0552d955028d5";
				String key2 = "a188a83c50fb4c24b7f178b9830f74b4";
				String key3 = "1f86ee59e659436e80ca6cf6b5e76d98";
				/**
				 * while循环 先让它一直循环 最后会做个判断 判断返回的值,如果返回的值是次数限制了,
				 * 让num+1,换一个密钥 继续
				 */
				List<String>list = new ArrayList<>();
				int num = 0;
					list.add(key1);
					list.add(key2);
					list.add(key3);
					while(num<list.size()) {
						userInfo.setApiKey(list.get(num));
					
						
				
				userInfo.setUserId("java1901");
				tulingbean.setUserInfo(userInfo);
				//转换为json对象
				JSONObject jsonObject = JSONObject.fromObject(tulingbean);
				
				JSONObject jObject = WeixinUtil.httpRequest(requestUrl, "POST", jsonObject.toString());
				//获取json里的值
				//System.out.println(jsonObject.toString());
				JSONArray json = (JSONArray) jObject.get("results");
				JSONObject j1 = (JSONObject) json.get(0);
				JSONObject j2 = (JSONObject) j1.get("values");
				
				String results = j2.getString("text");
					if("请求次数超限制!".equals(results)) {
						num++;
						continue;
					}
		
				return results;
		}
				return "次数超限制";
	}
	public static void main(String[] args) {
		
		//接口地址
		String requestUrl = "http://openapi.tuling123.com/openapi/api/v2";
		
		TulingBean t = new TulingBean();
		//输入类型 0是文本信息
		t.setReqType(0);
		Perception p = new Perception(); 
		InputText iText = new InputText();
		//客户端发来的信息
		iText.setText("你好");
		p.setInputText(iText);
		t.setPerception(p);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setApiKey("cd6f7bfdecba4d56a08a6956ea32c0f1");
		userInfo.setUserId("java1901");
		t.setUserInfo(userInfo);
		//转换为json对象
		JSONObject jsonObject = JSONObject.fromObject(t);
		
		JSONObject jObject = WeixinUtil.httpRequest(requestUrl,"POST", jsonObject.toString());
		//获取json里的值
		System.out.println(jObject.toString());
		JSONArray json = (JSONArray) jObject.get("results");
		JSONObject j1 = (JSONObject) json.get(0);
		JSONObject j2 = (JSONObject) j1.get("values");
		
		String results = j2.getString("text");
	}

}
