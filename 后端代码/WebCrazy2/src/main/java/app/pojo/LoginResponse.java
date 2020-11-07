package app.pojo;

/**
 * @Author Fizz Pu
 * @Date 2020/11/4 下午10:21
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class LoginResponse extends OrdRes {
    private String token;
    private String refreshToken;
    private Long userId;

    public LoginResponse(Integer responseCode, String msg, String token, String refreshToken, Long userId) {
        super(responseCode, msg);
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
