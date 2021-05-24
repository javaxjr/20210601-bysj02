package com.tjetc.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebMapPageResult extends JSONObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -966968655302122442L;
	
	public static final String COUNT_KEY = "count";
	public static final String PAGE_KEY = "page";
	public static final String TOTAL_PAGE_KEY = "totalpage";
	public static final String TOTAL_KEY = "total";
	
	/**
     * 成功响应编码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 登陆错误编码
     */
    public static final String LOGIN_ERROR_CODE = "1001";
    
    /**
     * 其他错误编码
     */
    public static final String OTHER_ERROR_CODE = "1000";
    
    /**
     * 权限错误编码
     */
    public static final String RIGHT_ERROR_CODE = "1002";

    /**
     * 响应码Key
     */
    public static final String RSP_CODE_KEY = "code";

    /**
     * 响应描述Key
     */
    public static final String RSP_DESC_KEY = "msg";

    /**
     * 数据集Key
     */
    public static final String DATA_KEY = "data";


    /**
     * 构造函数
     */
    public WebMapPageResult()
    {
        super();
    }
    
    public static WebMapPageResult instance() {
    	return new WebMapPageResult();
    }
    
    public static WebMapPageResult error() {
    	return new WebMapPageResult().fail();
    }
    
    public static WebMapPageResult error(String errorCode) {
    	return new WebMapPageResult().fail().setError(errorCode);
    }
    
    public static WebMapPageResult success() {
        return new WebMapPageResult().ok();
    }

    /**
     * 获取错误编码
     *
     * @return 错误编码
     */
    public String getErrorCode()
    {
        return super.get(RSP_CODE_KEY).toString();
    }

    /**
     * 获取错误描述
     *
     * @return 错误描述
     */
    public String getMessage()
    {
        return super.get(RSP_DESC_KEY).toString();
    }

    /**
     * 设置错误编码
     *
     * @param error 错误编码
     */
    public WebMapPageResult setError(String error)
    {
        super.put(RSP_CODE_KEY, error);
        return this;
    }

    /**
     * 设置错误描述
     *
     * @param message 错误描述
     */
    public WebMapPageResult setMessage(String message)
    {
        super.put(RSP_DESC_KEY, message);
        return this;
    }

    /**
     * 成功响应
     *
     * @return WebMapPageResult
     */
    public WebMapPageResult ok()
    {
        super.put(RSP_CODE_KEY, SUCCESS_CODE);
        return this;
    }

    /**
     * 成功响应
     *
     * @return WebMapPageResult
     */
    public WebMapPageResult ok(String message)
    {
        super.put(RSP_CODE_KEY, SUCCESS_CODE);
        super.put(RSP_DESC_KEY, message);
        return this;
    }

    /**
     * 设置数据集
     *
     * @param data
     * @return
     */
    public WebMapPageResult setData(Object data)
    {
        super.put(DATA_KEY, data);
        return this;
    }

    /**
     * * 失败响应
     *
     * @param errorCode 错误响应编码
     * @param errorMsg  错误响应信息
     * @return WebMapPageResult
     */
    public WebMapPageResult fail(String errorCode, String errorMsg)
    {
        if (StringUtils.isEmpty(errorCode))
        {
            super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
            super.put(RSP_DESC_KEY, "unknown error");
        }
        else
        {
            super.put(RSP_CODE_KEY, errorCode);
            super.put(RSP_DESC_KEY, errorMsg);
        }
        return this;
    }

    /**
     * 失败响应
     *
     * @return WebMapPageResult
     */
    public WebMapPageResult fail()
    {
        super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
        super.put(RSP_DESC_KEY, "unknown error");
        return this;
    }

    /**
     * 失败响应
     *
     * @return WebMapPageResult
     */
    public WebMapPageResult fail(String message)
    {
        super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
        super.put(RSP_DESC_KEY, message);
        return this;
    }
    
    public WebMapPageResult loginFail(String message) {
    	super.put(RSP_CODE_KEY, LOGIN_ERROR_CODE);
        super.put(RSP_DESC_KEY, message);
        return this;
    }
    
    public WebMapPageResult loginFail() {
    	super.put(RSP_CODE_KEY, LOGIN_ERROR_CODE);
        super.put(RSP_DESC_KEY, "登陆错误！");
        return this;
    }
    

    /**
     * 设置 key-value
     *
     * @param key   key
     * @param value value
     * @return WebMapPageResult
     */
    public WebMapPageResult set(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 返回Map对象,Comframe比较搓,适配修改
     *
     * @return
     */
    public Map<String,Object> deepClone()
    {
        Map<String,Object> cloneMap = new HashMap<String,Object>();
        Iterator<Entry<String,Object>> iterator = this.entrySet().iterator();
        while (iterator.hasNext())
        {
            Entry<String,Object> entry = iterator.next();
            cloneMap.put(entry.getKey(),entry.getValue());
        }
        return cloneMap;
    }

    //接口中返回信息结点
    public final static String INTERFACE_RESP_INFO = "RSP_INFO";

    /**
     * 是否是成功
     *
     * @return
     */
    public boolean isSuccess()
    {
        if (super.getString(RSP_CODE_KEY) != null && super.getString(RSP_CODE_KEY).equals(SUCCESS_CODE))
        {
            return true;
        }
        return false;
    }

    /**
     * 设置接口中RSP_INFO结点
     *
     * @param respInfo
     */
    public void putRespInfo(Object respInfo)
    {
        super.put(INTERFACE_RESP_INFO, respInfo);
    }
    
    public WebMapPageResult putIntoData(String key, Object value) {
    	if(null == this.get(DATA_KEY)) {
    		JSONObject jso = new JSONObject();
    		jso.put(key, value);
    		this.set(DATA_KEY, jso);
    	}else {
    		if(this.get(DATA_KEY) instanceof Map) {
    			((Map<String,Object>)this.get(DATA_KEY)).put(key, value);
    		}
    	}
    	return this;
    }
	
	public WebMapPageResult setCount(long count) {
		super.put(COUNT_KEY, count);
		return this;
	}
	
	public long getCount() {
		return super.getLongValue(COUNT_KEY);
	}
	
	public WebMapPageResult setPage(long page) {
		super.put(PAGE_KEY, page);
		return this;
	}
	
	public long getPage() {
		return super.getLongValue(PAGE_KEY);
	}
	
	public WebMapPageResult setTotalPage(long totalpage) {
		super.put(TOTAL_PAGE_KEY, totalpage);
		return this;
	}
	
	public long getTotalPage() {
		return super.getLongValue(TOTAL_PAGE_KEY);
	}
	
	public WebMapPageResult setTotal(long total) {
		super.put(TOTAL_KEY, total);
		return this;
	}
	
	public long getTotal() {
		return super.getLongValue(TOTAL_KEY);
	}

    /**
     * 设置数据集
     *
     * @param data
     * @return
     */
    public WebMapPageResult setPageData(Object data)
    {
        super.put(DATA_KEY, data);
        return this;
    }

}
