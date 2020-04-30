package entity;

import java.util.HashMap;
import java.util.List;

public class Request {
    public String uri;
    public String method;
    public HashMap<String, String> headers;
    public HashMap<String, Object> body;
    //public HashMap<String, Object> expect;
    public List<HashMap<String, Object>> validators;
}
