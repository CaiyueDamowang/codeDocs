package app.pojo.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:38
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Entity
@Table(name = "post_tb")
public class PostInfo {
    @Column(name = "post_id")
    Integer postId;

    @Column(name = "title")
    String  title;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "heat")
    Integer heat;

    @Column(name = "thumbs") // 点赞数
            Integer thumbs;

    @Column(name = "state")
    Integer state;

    @Column(name = "post_content")
    String Content;

    @Transient
    List<PostImage> postImages ;

    public Integer getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getHeat() {
        return heat;
    }

    public Integer getThumbs() {
        return thumbs;
    }

    public Integer getState() {
        return state;
    }

    public String getContent() {
        return Content;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public void setThumbs(Integer thumbs) {
        this.thumbs = thumbs;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }
}
