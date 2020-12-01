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
     * @param start 开始位置
     * @param count 查找count条帖子
     * @return
     */
    public List<PostInfo>  getPostInfo(Long start, Long count){
        return  null;
    }

    /**
     * 保存帖子
     * @param post
     * @return 帖子id
     */
    public Long savePost(PostInfo post){
        return 0L;
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
        return 0L;
    }

    /**
     * 更新图片
     * @param image
     * @return 更新的id
     */
    public Long update(PostImage image){
        return 0L;
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
