package org.kee.vue.server.vhr.model;

/**
 * @Author： 羽子少年
 * @Date： 2020/6/14 20:40
 */
public class Response {
    private Integer status;
    private String msg;
    private Object obj;

    public Response(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static Response ok(String msg) {
        return new Response(200, msg, null);
    }

    public static Response ok(String msg, Object obj) {
        return new Response(200, msg, obj);
    }

    public static Response error(String msg) {
        return new Response(500, msg, null);
    }

    public static Response error(String msg, Object obj) {
        return new Response(500, msg, obj);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
