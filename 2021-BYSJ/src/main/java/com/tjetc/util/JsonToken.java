package com.tjetc.util;

public class JsonToken {
    public int code;
    public String msg;
    public Object data;
    public int count;
    public int pages;

    //返回数据符合layui需要的数据格式；第一个是状态码，0表示成功，第二个是提示信息，第三个是要返回的数据，第四个是数据的总数量

    public JsonToken(int code, String msg, Object data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
    //这个方法添加了页数，方便做分页的时候直接做好分页在渲染页面

    public JsonToken(int code, String message, Object data, int count, int pages) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.count = count;
        this.pages = pages;
    }
}
