package app.pojo.userservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author Fizz Pu
 * @Date 2020/11/2 下午7:04
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


@Entity
@Table(catalog = "user_tb")
public class User {

    @Column(columnDefinition = "user_id")
    private Long userId;

    @Column(columnDefinition = "user_name")
    private String userName;

    @Column(columnDefinition = "password")
    private String passWord;

    @Column(columnDefinition = "email")
    private String email;

    @Column(columnDefinition = "is_superuser")
    private Integer isSuperUser;

    @Column(columnDefinition = "sex")
    private String sex;

    @Column(columnDefinition = "photo_url")
    private String photoUrl;

    @Transient
    private String token;

    @Transient
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(Integer isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
