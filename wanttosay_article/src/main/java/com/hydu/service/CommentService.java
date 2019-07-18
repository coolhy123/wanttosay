package com.hydu.service;

import com.hydu.dao.CommentDao;
import com.hydu.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;
import java.util.List;

/**
 * Created by heyong
 * 2019/7/4
 */

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 新增一条评论
     * @param comment
     */
    public void save(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 删除一条评论
     * @param id
     */
    public void deleteById(String id){
        commentDao.deleteById(id);
    }

    /**
     * 通过文章id查询所有评论
     * @param articleId
     * @return
     */
    public List<Comment> findByArticleid(String articleId){
        return commentDao.findByArticleid(articleId);
    }
}
