package com.api.common.entity;

import com.api.common.enums.HttpType;

import java.util.HashMap;
import java.util.Map;

public class Request {
    /*
    请求类型： post , get
    */
    private HttpType httpType;
    /*
    url
    */
    private String path;
    /*
    请求头
    */
    private Map<String, Object> headers = new HashMap<>();
    /*
    cookies
    */
    private Map<String, String> cookies = new HashMap<>();
    /*
    * 请求参数
    */


    private Map<String, Object> params = new HashMap<>();
    private String body;
    public HttpType getType(){ return httpType;}
    public void setType(HttpType type) { httpType = type;}
    public String getPath() {
        return path;
    }
    public Map<String, Object> getParams() {
        return params;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Map<String, Object> getHeaders(){return headers;}
    public Map<String, String> getCookies(){return cookies;}
    public void setHeaders(Map<String, Object> headers) {this.headers = headers;}
    public void setCookies(Map<String, String> cookies){this.cookies = cookies;}

}
