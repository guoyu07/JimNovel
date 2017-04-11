package com.jim.novel.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Response {
	/* 服务器错误 */
	public static final int ERROR_SERVER_500 = 500;
	/* 功能执行错误 */
	public static final int ERROR_SERVER_505 = 505;
	/* 请求参数错误 */
	public static final int ERROR_REQ_PARAM_406 = 406;
	/* 手机号已被注册 */
	public static final int ERROR_TELE_EXIST_407 = 407;
	/* 手机号未注册 */
	public static final int ERROR_TELE_UNEXIST_408 = 408;
	/* 会话过期 */
	public static final int ERROR_SESSION_DELINE_412 = 412;
	/* 登录受限 */
	public static final int ERROR_LOGIN_LIMIT_401 = 401;
	/* 资源定位失败 */
	public static final int ERROR_SOURCE_FAILED_404 = 404;
	/* 禁止访问 */
	public static final int ERROR_FORBIDDEN_400 = 400;
	/* 其他错误 */
	public static final int ERROR_UNKNOW = 999;
	/* 结果处理中，需要等待 */
	public static final int OK_201 = 201;
	/* 响应成功 */
	public static final int OK_200 = 200;
	
	
	/* 消息类型 */
	public static final int MSG_TYPE_NORMAL = 0;         // 普通类型
	public static final int MSG_TYPE_TRADE_STRATEGY = 1; // 操盘策略

	public int code;
	public String msg = "";
	public int msgType = MSG_TYPE_NORMAL;
	public String errparam = "";
	public Object data;

	private static Gson seriaGson = null;

	private static GsonBuilder gsonBuilder = new GsonBuilder();

	static{
		 gsonBuilder.setPrettyPrinting()
	    .disableHtmlEscaping()
	    .serializeNulls()
	    .setDateFormat("yyyy-MM-dd HH:mm:ss");
		 seriaGson = gsonBuilder.create();
	}


	/**
	 *
	 * @Description: 生成成功的response,并转出json字符串
	 * @Author lee
	 * @Created 16/10/9 下午3:22
	 * @Param  请求成分返回的数据模型
	 * @Return
	 *
	 */
	public static String successResponseJson (Object data){
		Response response = new Response(Response.OK_200,"操作成功",data);
		return response.toJsonString();
	}

	public Response() {
		super();
	}

	public Response(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Response(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toJsonString() {
		return seriaGson.toJson(this);
	}
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String toJsonString(String[] excludeNames){
		GsonBuilder gb = new GsonBuilder(); 
		gb.serializeNulls();
		gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gb.setExclusionStrategies(new JSONKit(excludeNames)).create();
		return gson.toJson(this);
	}

	public String getErrparam() {
		return errparam;
	}

	public void setErrparam(String errparam) {
		this.errparam = errparam;
	}

	/**
	 * Gson序列化对象排除属性 
	 * 调用方法： String[] keys = { "id" }; 
	 * Gson gson = new GsonBuilder().setExclusionStrategies(new JsonKit(keys)).create();
	 */
	private static class JSONKit implements ExclusionStrategy {
		String[] keys;

		public JSONKit(String[] keys) {
			this.keys = keys;
		}

		@Override
		public boolean shouldSkipClass(Class<?> arg0) {
			return false;
		}

		@Override
		public boolean shouldSkipField(FieldAttributes arg0) {
			for (String key : keys) {
				if (key.equals(arg0.getName())) {
					return true;
				}
			}
			return false;
		}
	}

}
