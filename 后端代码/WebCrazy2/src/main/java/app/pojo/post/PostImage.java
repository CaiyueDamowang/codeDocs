package app.pojo.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午10:53
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Entity
@Table(name = "post_img")
public class PostImage {

    @Column(name = "img_id")
    Integer img_id;

    @Column(name = "img_url")
    String imgUrl;

    @Column(name = "post_id")
    String postId;

    // baseStr base64位字符串
    @Transient
    String baseStr;

    // 图片名
    @Transient
    String fileName;

    public Integer getImg_id() {
        return img_id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPostId() {
        return postId;
    }

    public String getBaseStr() {
        return baseStr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setBaseStr(String baseStr) {
        this.baseStr = baseStr;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
