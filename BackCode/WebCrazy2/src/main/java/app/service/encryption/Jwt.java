package app.service.encryption;


import app.service.timeservice.CurTime;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author Fizz Pu
 * @Date 2020/10/6 下午7:50
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@PropertySource("classpath:application.properties")
@Component
public class Jwt {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // @Value("${jwt.secretKey}")
    private String secret = "hujie&_*(";

    // @Value("${jwt.tokenExpried}") // 进行类型转换
    private long tokenExpiredMinutes = 30 * 24 * 60;

    private final Algorithm algorithm = Algorithm.HMAC256(secret);

    @Autowired
    private CurTime curTime;

    public void setTokenExpiredMinutes(long tokenExpiredMinutes) {
        this.tokenExpiredMinutes = tokenExpiredMinutes;
    }

    public long getTokenExpiredMinutes() {
        return tokenExpiredMinutes;
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
    private String getToken(long id, long mintues) {
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
    public String  getToken(Long id){
        return getToken(id, tokenExpiredMinutes);
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean verify(String token){
        if(token == null) return false;

        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException ex){
            return false;
        }
        return true;
    }

    public Long getId(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            Claim claim = decode.getClaim("id");
            return claim.asLong();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @PostConstruct
    void init(){
        logger.info("Jwt组件已经初始化成功");
    }
}

