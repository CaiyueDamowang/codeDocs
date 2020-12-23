package app.pojo.post;

import javax.persistence.*;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午10:53
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Entity
@Table(name = "post_img")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    Integer img_id;

    @Column(name = "img_url")
    String imgUrl;

    @Column(name = "post_id")
    Long postId;

    // baseStr base64位字符串
    @Column(name = "base_str")
    String baseStr;

    // 图片名
    @Column(name = "image_name")
    String fileName;

    // 图片是否被删除 1: 已经被删除
    @Column(name = "state")
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getImg_id() {
        return img_id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Long getPostId() {
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

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setBaseStr(String baseStr) {
        this.baseStr = baseStr;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
