package com.gestmemapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.gestmemapi.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findById(Long id);

    Optional<Comment> findByCommentDescription(String commentDescription);

}