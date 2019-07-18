package com.hydu.dao;

import com.hydu.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by heyong
 * 2019/7/4
 */
public interface CommentDao extends MongoRepository<Comment,String> {

    public List<Comment> findByArticleid(String artucleId);
}
