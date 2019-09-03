package com.xtsoft.kernel.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AjaxJsonResult<T> implements Serializable {
	public final static int RET_SUCCESS = 1;
	public final static int RET_FAIL = 0;
	private int result = 1;// 0: 正确返回。其它: 失败。
	private String message = "操作成功";// 提示信息
	private Object data = null;// 返回的数据
	private LinkedHashMap<String, Object> extend = new LinkedHashMap<String, Object>();// 扩展数据
	private List<Object> rows;
	private List<T> results; // 结果
	private int resultType = 1;// 1: 正确返回。其它: 失败

	public final int getResultType() {
		return resultType;
	}

	public final void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public final List<T> getResults() {
		return results;
	}

	public final void setResults(List<T> results) {
		this.results = results;
	}

	private long exeTime;// 执行时间

	public final long getExeTime() {
		return exeTime;
	}

	public final void setExeTime(long exeTime) {
		this.exeTime = exeTime;
	}

	public final long getTotal() {
		return total;
	}

	public final void setTotal(long total) {
		this.total = total;
	}

	private long total; // 总数

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public void put(String key, Object value) {
		extend.put(key, value);
	}

	public void remove(String key) {
		extend.remove(key);
	}

	public LinkedHashMap<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(LinkedHashMap<String, Object> extend) {
		this.extend = extend;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static AjaxJsonResult bulidAjaxPageJsonSucess(List results, long total, long exeTime) {
		AjaxJsonResult ajaxPageJson = new AjaxJsonResult();
		ajaxPageJson.setExeTime(exeTime);
		ajaxPageJson.setResults(results);
		ajaxPageJson.setRows(results);
		ajaxPageJson.setResult(ajaxPageJson.RET_SUCCESS);
		ajaxPageJson.setTotal(total);
		ajaxPageJson.setMessage(ajaxPageJson.message);
		return ajaxPageJson;
	}

	public static AjaxJsonResult bulidAjaxPageJsonFail(String message, long exeTime) {
		AjaxJsonResult ajaxPageJson = new AjaxJsonResult();
		ajaxPageJson.setExeTime(0);
		ajaxPageJson.setResults(new ArrayList());
		ajaxPageJson.setRows(new ArrayList());
		ajaxPageJson.setResult(ajaxPageJson.RET_FAIL);
		ajaxPageJson.setTotal(0);
		ajaxPageJson.setMessage(message);
		return ajaxPageJson;
	}

	public static AjaxJsonResult buildResultSuccess(String msg) {
		AjaxJsonResult ajaxJsonResult = new AjaxJsonResult();
		ajaxJsonResult.setMessage(msg);
		ajaxJsonResult.setResult(AjaxJsonResult.RET_SUCCESS);
		return ajaxJsonResult;
	}

	public static AjaxJsonResult buildResultFail(String msg) {
		AjaxJsonResult ajaxJsonResult = new AjaxJsonResult();
		ajaxJsonResult.setMessage(msg);
		ajaxJsonResult.setResult(AjaxJsonResult.RET_FAIL);
		return ajaxJsonResult;
	}

}
