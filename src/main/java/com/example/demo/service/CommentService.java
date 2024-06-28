package com.example.demo.service;

import com.example.demo.persistence.model.Comment;

public interface CommentService {
    Comment addComment(String userEmail, Comment comment, long serviceId);

    Comment manageCommentBlockState(long commentId);
}
