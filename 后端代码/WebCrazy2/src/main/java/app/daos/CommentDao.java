package app.daos;

import app.pojo.post.Comment;
import app.service.dataService.DataService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/15 下午8:59
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class CommentDao {

    @Autowired
    DataService dataService;

    /**
     * 获得某个一级评论下所有的评论节点,包括一级评论
     * @param rootId 一级评论id
     * @return
     */
    public  List<Comment>  getComments(int rootId, int postId){
        String hql = "from Comment  where rootId=:rootId  and postId = :postId order by create_time";
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        Query<Comment> query = session.createQuery(hql);
        query.setParameter("postId", postId);
        query.setParameter("rootId", rootId);
        List<Comment> res = query.list();
        tx.commit();
        session.close();
        return res;
    }

    /**
     * 获得某一帖子下所有的一级评论的id
     * @param postId
     * @return
     */
    public  List<Integer> getFirstCommentsId(int postId){
        String hql = "select rootId from Comment where postId = :postId and parentId = null order by create_time asc";
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        Query<Integer> query = session.createQuery(hql);
        query.setParameter("postId", postId);
        List<Integer> res = query.list();
        tx.commit();
        session.close();
        return res;
    }

    /**
     * 保存评论
     * @param comment
     */
    public void saveComment(Comment comment){
        // 保存评论
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        session.save(comment);
        tx.commit();
        session.close();
    }

    /**
     * 保存评论并更新评论的rootId字段为主键commentId
     */
    public void saveUpdateComment(Comment comment){
        // 保存评论
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        session.save(comment); // 对象是持久态，保存在缓存中
        comment.setRootId(comment.getCommentId());
        tx.commit(); // 比较对象是否一样，不一样则发送更新缓存语句
        session.close();
    }
}
