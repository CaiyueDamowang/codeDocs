package app.daos;


import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.pojo.userservice.User;
import app.service.dataService.DataService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:42
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class PostInfoDao {

    @Autowired
    DataService dataService;

    /**
     * 分页查询帖子
     * @param start 页数
     * @param count 查找count条帖子
     * @return
     */
    public List<PostInfo>  getPostInfo(Integer start, Integer count){
        Session session = dataService.getSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
        return null;
    }

    /**
     * 保存帖子
     * @param post
     * @return 帖子id
     */
    public Long savePost(PostInfo post){
        Long id;
        Session session = dataService.getSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
        session.close();
        id = post.getPostId();
        return id;
    }

    /**
     * 根据postId获得图片
     * @param postId
     * @return
     */
    public List<PostImage> getPostImages(Long postId){
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from PostImage where postId = :postId";
        Query<PostImage> query = session.createQuery(hql);
        query.setParameter("postId", postId);
        List<PostImage> res = query.list();
        tx.commit();
        return res;
    }

    /**
     * 保存帖子图片
     */
    public void saveImages(List<PostImage> images){

    }

    /**
     * 保存帖子图片
     * @return 图片id
     */
    public Long saveImage(PostImage image){
        Long id;
        Session session = dataService.getSession();
        session.beginTransaction();
        session.save(image);
        session.getTransaction().commit();
        session.close();
        id = image.getPostId();
        return id;
    }

    /**
     * 更新图片
     * @param image
     * @return 更新的id
     */
    public void update(PostImage image){
        Session session = dataService.getSession();
        session.beginTransaction();
        session.update(image);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * 根据ImageId获得图片
     * @param imageId
     * @return
     */
    public PostImage getImage(Long imageId){
        return null;
    }
}
