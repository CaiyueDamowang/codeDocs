package app.service.encryption;


import app.service.timeservice.CurTime;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author Fizz Pu
 * @Date 2020/10/6 下午7:50
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class Jwt {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private long tokenExpiredMinutes = 30;// // token失效分钟数字30分钟
    private long refreshTokenExpireMinutes = 60 * 24 * 60; // refreshToken失效天数60天
    private final String secret = "secret"; // 密钥，后面生成Bean时从配置类读取
    private final Algorithm algorithm = Algorithm.HMAC256(secret);

    @Autowired
    private CurTime curTime;

    public void setTokenExpiredMinutes(long tokenExpiredMinutes) {
        this.tokenExpiredMinutes = tokenExpiredMinutes;
    }

    public long getTokenExpiredMinutes() {
        return tokenExpiredMinutes;
    }

    public void setRefreshTokenExpiredDays(long refreshTokenExpiredMinutes) { this.refreshTokenExpireMinutes = refreshTokenExpiredMinutes; }

    public long getRefreshTokenExpireMinutes() {
        return refreshTokenExpireMinutes;
    }

    public void setCurTime(CurTime curTime) {
        this.curTime = curTime;
    }

    /**
     *
     * @param id
     * @param mintues 时长 单位：minutes
     * @return
     */
    private String getToken(int id, long mintues) {
        // 后面从配置文件读取
        String token = null;
        try {
            JWTCreator.Builder build = JWT.create();
            // 写入id
            build.withClaim("id", id);
            // 设置生效时间
            build.withExpiresAt(curTime.plusMinutesDate(mintues));
            token = build.sign(algorithm);
        } catch (JWTCreationException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    /**
     *  签发token
     * @param id 用户id
     * @return
     */
    public String  getToken(int id){
        return getToken(id, tokenExpiredMinutes);
    }

    /**
     * 签发refreshtoken
     * @param id
     * @return
     */
    public String  getRefreshToken(int id){
        return getToken(id, refreshTokenExpireMinutes);
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException ex){
            return false;
        }
        return true;
    }

    @PostConstruct
    void init(){
        logger.info("Jwt组件已经初始化成功");
    }

}

