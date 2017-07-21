package com.barrage.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class webUtil {
	 
	 public static final int FLAG_SUCCESS = 1;
	 public static final int FLAG__FAILED = 0;
	 public static final int LOGIN_FAILED = -1;
	 //error_code
	 public static final String ERROR_CODE_SUCCESS = "0000";//成功
	 public static final String ERROR_CODE_LOGINFAILED = "0001";//未登入的错误
	 public static final String ERROR_CODE_PARAMWRONG = "0002";//参数错误
	 public static final String ERROR_CODE_VALIDATEWRONG = "0003";//验证错误
	 public static final String ERROR_CODE_ADDFAILED = "0010";//添加失败
	 public static final String ERROR_CODE_UPDATEFAILED = "0030";//修改失败
	 public static final String ERROR_CODE_DATAWRONG = "0040";//数据错误
	 public static final String ERROR_CODE_ILLEGAL = "0080";//非法操作
	 public static final String ERROR_CODE_BAN = "0090";//封禁
	 public static final String ERROR_CODE_PSDERROR = "0100";//封禁
	 public static final String ERROR_CODE_NODATA = "0010";//无数据
	 public static final String ERROR_CODE_NOENOUGHMONEY = "0010";//无数据
	 /**
	  * 运行时异常
	  */
	 public static final String ERROR_CODE_RUNTIME_EXCEPTION = "0011";
	 
     private static final Object visit = new Object();
	
     /**
      * @param flag 返回的状态1为成功 0为失败
      * @param code 返回信息的代码
      * @param message 返回信息提示
      * @param obj     返回数据对象
      * @return
      */
     public static String results(int flag,String code,String message,Object obj){
    	 //静态方法不用new.直接通过.调用
    	 Map<String,Object> map=new HashMap<String, Object>();
    	 map.put("flag", flag);
    	 map.put("code", code);
    	 map.put("message", message);
    	 map.put("objectList", obj);
    	 return JsonUtil.toJSON(map).toString();
     }
     /**
      * @param flag 返回的状态1为成功 0为失败
      * @param code 返回信息的代码
      * @param message 返回信息提示
      * @param obj     返回数据对象
      * @return
      */
     public static String result(int flag,String code,String message,Object obj){
    	 Map<String,Object> map=new HashMap<String, Object>();
    	 map.put("flag", flag);
    	 map.put("code", code);
    	 map.put("message", message);
    	 map.put("objectList", obj);
    	 return JsonUtil.toJson(map,DateUtils.yyyyMMddHHmmss);
     }
     
     public static String resultTotal(int flag,String code,String message,Object obj ,long l){
    	 Map<String,Object> map=new HashMap<String, Object>();
    	 map.put("flag", flag);
    	 map.put("code", code);
    	 map.put("message", message);
    	 map.put("objectList", obj);
    	 map.put("total", l);
    	 return JsonUtil.toJson(map,DateUtils.yyyyMMddHHmmss);
     }
     /**
      * @param flag 返回的状态1为成功 0为失败
      * @param code 返回信息的代码
      * @param message 返回信息提示
      * @param obj     返回数据对象
      * @return
      */
     public static String resultYMD(int flag,String code,String message,Object obj){
    	 Map<String,Object> map=new HashMap<String, Object>();
    	 map.put("flag", flag);
    	 map.put("code", code);
    	 map.put("message", message);
    	 map.put("objectList", obj);
    	 return JsonUtil.toJson(map,DateUtils.yyyyMMdd);
     }
     /**
      * 返回两个json对象的数据
      * @param flag
      * @param code
      * @param message
      * @param obj
      * @param obj1
      * @return
      */
     public static String resultTwoObject(int flag,String code,String message,Object obj,Object obj1){
    	 Map<String,Object> map=new HashMap<String, Object>();
    	 map.put("flag", flag);
    	 map.put("code", code);
    	 map.put("message", message);
    	 map.put("objectList", obj);
    	 map.put("objectList1", obj1);
    	 return JsonUtil.toJson(map,DateUtils.yyyyMMdd);
     }
     //成功返回
     public static Map<String,Object> successRes(Object obj){
    	 return resMsg(webUtil.FLAG_SUCCESS,webUtil.ERROR_CODE_SUCCESS,"成功",obj);
     }
     //失败返回
     public static Map<String,Object> failedRes(String errorCode,String errorMsg,Object obj){
    	 return resMsg(webUtil.FLAG__FAILED,errorCode,errorMsg,obj);
     }
     //未登入
     public static Map<String,Object> loginFailedRes(Object obj){
    	 return resMsg(webUtil.LOGIN_FAILED,webUtil.ERROR_CODE_LOGINFAILED,"未登入",obj);
     }
     public static void failedView(ModelAndView view,String errorCode,String errorMsg){
    	 if(view!=null){
    		 view.addObject("errorCode", errorCode);
    		 view.addObject("errorMsg", errorMsg);
    	 }
     }
     /*
      * @param flag 0:失败，1:成功，-1:未登录
      * @param 成功：0000
      */
	public static Map<String,Object> resMsg(int flag,String errorCode,String errorMsg,Object obj){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("flag", flag);
		result.put("errorCode", errorCode);
		result.put("errorMsg", errorMsg);
		result.put("obj", obj);
	    return result;
	}
	
	
	public static String encodeTodecode(String value) throws UnsupportedEncodingException{
		return URLDecoder.decode(value, "UTF-8");
	}
}
