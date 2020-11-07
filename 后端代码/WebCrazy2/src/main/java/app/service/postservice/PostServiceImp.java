package  app.service.postservice;

import app.daos.CommentDao;
import app.daos.PostInfoDao;
import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:47
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

public class PostServiceImp implements PostService {

    @Autowired
    PostInfoDao postInfoDao;

    @Override
    public List<PostInfo> getPostInfo(Long start, Long count) {
        List<PostInfo> posts = postInfoDao.getPostInfo(start, count);
        for(PostInfo post : posts){
            List<PostImage> images = postInfoDao.getPostImages(post.getPostId());
            post.setPostImages(images);
        }
        return posts;
    }

    @Override
    public List<PostImage> getPostImageBaseString(Long postId) {
        return  postInfoDao.getPostImages(postId);
    }

    @Override
    public void upLoadPost(PostInfo postInfo) {
        if(postInfo == null)throw new IllegalArgumentException("postInfo为null");
        // 1. 保存帖子
        Long postId = postInfoDao.savePost(postInfo);
        // 2. 保存图片
        List<PostImage> postImages = postInfo.getPostImages();
        // 生成参数, url
        Long imageId;
        if(postImages != null && postImages.size() > 0){
            for(PostImage image : postImages){
                image.setPostId(postId);
                imageId = postInfoDao.saveImage(image);
                image.setImgUrl(Generator.imageBaseUrl(imageId));
                postInfoDao.update(image);
            }
        }
    }

    @Override
    public PostImage getImage(Long imageId) {
        return postInfoDao.getImage(imageId);
    }
}