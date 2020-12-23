package app.pojo.post;

import javax.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    Long postId;

    @Column(name = "title")
    String  title;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "heat")
    Long heat;

    @Column(name = "thumbs") // 点赞数
    Long thumbs;

    @Column(name = "state")
    Long state;

    @Column(name = "post_content")
    String content;

    @Transient
    List<PostImage> postImages ;


    @Override
    public String toString() {
        return title + " " + content + " " + postImages;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getHeat() {
        return heat;
    }

    public Long getThumbs() {
        return thumbs;
    }

    public Long getState() {
        return state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setHeat(Long heat) {
        this.heat = heat;
    }

    public void setThumbs(Long thumbs) {
        this.thumbs = thumbs;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }
}
