package app.pojo;

/**
 * @Author Fizz Pu
 * @Date 2020/10/27 下午2:48
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class OrdRes {
    private Integer code;
    private String msg;

    public OrdRes(Integer responseCode, String msg) {
        this.code = responseCode;
        this.msg = msg;
    }

    public Integer getResponseCode() {
        return code;
    }

    public void setResponseCode(Integer responseCode) {
        this.code = responseCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

