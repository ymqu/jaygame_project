package com.jaygame.base.dao;

import com.jaygame.base.pojo.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comments, String> {
    public Comments findCommentBy_id(String id);
    public Page<Comments> findCommentsByGameId(String gameId, Pageable pageable);
}
