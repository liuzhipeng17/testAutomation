package entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.List;

public class TestCase {
    private String apiName;
    private String uri;
    private String method;
    private HashMap<String, String> headers;
    private HashMap<String, Object> body;
    private List<HashMap<String, Object>> validators;

    public String getApiName()
    {
        return apiName;
    }
    public void setApiName(String apiName){
        this.apiName = apiName;
    }


    public String getUri()
    {
        return uri;
    }
    public void setUri(String uri){
        this.uri = uri;
    }

    public String getMethod()
    {
        return method;
    }
    public void setMethod(String method){
        this.method = method;
    }

    public HashMap<String, String> getHeaders(){
        return headers;
    }
    public void setHeaders(HashMap<String ,String> headers){
        this.headers = headers;
    }

    public HashMap<String, Object> getBody(){
        return body;
    }
    public void setBody(HashMap<String ,Object> body){
        this.body = body;
    }

    public List<HashMap<String, Object>> getValidators(){
        return validators;
    }
    public void setValidators(List<HashMap<String, Object>> validators){
        this.validators = validators;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        //return ToStringBuilder.reflectionToString(this);
    }

}
