package app.pojo;

/**
 * @Author Fizz Pu
 * @Date 2021/1/8 下午11:57
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// 验证码

public class Kaptcha {
    private String data;
    private int code;

    public String getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
