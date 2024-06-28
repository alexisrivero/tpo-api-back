package com.example.demo.service.implementation;

import com.example.demo.persistence.model.Comment;
import com.example.demo.persistence.model.Service;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.CommentRepository;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImplementation userServiceImplementation;
    private final ServiceServiceImplementation serviceServiceImplementation;

    public Comment addComment(String userEmail, Comment comment, long serviceId) {
        User user = userServiceImplementation.getUser(userEmail);
        Optional<Service> service = serviceServiceImplementation.findById(serviceId);
        
        comment.setUser(user);
        comment.setService(service.get());
        
        this.commentRepository.save(comment);
        
        return comment;
    }

    public Comment manageCommentBlockState(long commentId) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);

        if (comment.isPresent()) {
            comment.get().setBlocked(!comment.get().isBlocked());

            this.commentRepository.save(comment.get());
        }

        return comment.get();
    }
}
