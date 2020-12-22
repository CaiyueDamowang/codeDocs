package app.pojo.post;



import javax.persistence.*;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author Fizz Pu
 * @Date 2020/10/16 下午9:18
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// 和实体类对应
// 引入jpa技术，避免配置


@Entity
@Table(name = "comment_tb")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "apply_id")
    private Integer parentId;

    @Column(name = "root_id")
    private Integer rootId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Transient // 与数据库的无关属性
    private List<Comment> children;

    public Comment() {
    }

    public Comment(Integer commentId, Integer userId, Integer parentId,
                   Integer rootId, Integer postId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.parentId = parentId;
        this.rootId = rootId;
        this.postId = postId;
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getRootId() {
        return rootId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return userId + "号 @" + parentId + ":" + content;
    }

    // 层序遍历，打印
    public void show() {
        Comment temp = this;
        Deque<Comment> deque = new LinkedList<>();
        deque.add(temp);
        while (!deque.isEmpty()) {
            temp = deque.removeFirst();
            System.out.println(temp);
            List<Comment> children = temp.getChildren();
            // 孩子节点加到队列中
            if (children != null) {
                for (Comment comment : temp.getChildren()) {
                    deque.addLast(comment);
                }
            }
        }
    }
}
