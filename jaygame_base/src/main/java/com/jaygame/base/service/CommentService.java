package com.jaygame.base.service;

import com.jaygame.base.dao.CommentDao;
import com.jaygame.base.pojo.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comments> findAll(){
        return  commentDao.findAll();
    }

    public Comments findById(String id){
        return commentDao.findCommentBy_id(id);
    }

    public void save(Comments comment){
        commentDao.save(comment);
    }

    public void update(Comments comment){
        commentDao.save(comment);
    }

    public void deleteById(String id){
        commentDao.deleteById(id);
    }
    
    public Page<Comments> findByGameId(String gameId, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return commentDao.findCommentsByGameId(gameId, pageable);
    }
}
