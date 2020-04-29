package com.api.common.utils;

import com.api.common.entity.Request;
import com.api.common.enums.HttpType;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;

public class HttpUtils {

    private RestAssuredConfig restAssuredCfg;
    private Response response;
    private String baseUrl;

    /**
     *
     * 构造方法
     */
    HttpUtils(String url){
        baseUrl = url;
        restAssuredCfg = config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(DOUBLE));
    }


    /*
    *获取本次请求的响应信息
     */
    private String getResponseInfo(Response response){

        if (response.contentType().contains("json")){
            return response.jsonPath().get();

        }
        else{
            return "";
        }

    }

    /**
     * 配置请求的headers
     * @param headers
     * @param cookies
     * @param path
     * @return
     */

    private RequestSpecification getRequestSpecification(Map<String, Object> headers,
                                                         Map<String, String> cookies,
                                                         String path)
    {
        return given().headers(headers).cookies(cookies).config(restAssuredCfg).basePath(path);
    }

    /**
     *
     * @param httpType
     * @param path
     * @param headers
     * @param cookies
     * @param params
     * @return
     */
    private Response request(HttpType httpType, String path, Map<String,
            Object> headers, Map<String, String> cookies, Map<String, Object> params,
                             String body)
    {
        switch(httpType){

            case GET:
                response = getRequestSpecification(headers,cookies,path).params(params).get();
                break;
            case POST:
                response = getRequestSpecification(headers,cookies,path).params(params).body(body).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }
            return response;
            //return response.then().extract().response();

    }


    /**
     * 获取请求响应, 这个响应包含了statusCode的
     * @param req
     * @return
     */

    public Response request(Request req){
        HttpType type = req.getType();
        String path = req.getPath();
        Map<String, Object> headers = req.getHeaders();
        Map<String, String> cookies = req.getCookies();
        Map<String, Object> params = req.getParams();
        String body = req.getBody();
        RestAssured.baseURI = baseUrl;
        response = request(type, path, headers, cookies, params, body);
        return response;
        //response.then().extract().response()
        //response.then().statusCode
    }

    public ValidatableResponse checkPoint(int statusCode){
        return response.then().statusCode(statusCode);
    }

}
