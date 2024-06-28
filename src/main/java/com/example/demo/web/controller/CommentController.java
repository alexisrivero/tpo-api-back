package com.example.demo.web.controller;

import com.example.demo.persistence.model.Comment;
import com.example.demo.persistence.model.Service;
import com.example.demo.service.implementation.CommentServiceImplementation;
import com.example.demo.service.implementation.PetServiceImplementation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImplementation commentServiceImplementation;

    @PostMapping("/add/{userEmail}/{serviceId}")
    public ResponseEntity<Comment> addComment(@PathVariable String userEmail, @RequestBody @NotNull Comment comment, @PathVariable long serviceId) {
        Comment createdComment = commentServiceImplementation.addComment(userEmail, comment, serviceId);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/manage-block/{commentId}")
    public ResponseEntity<Comment> manageCommentBlockState(@PathVariable long commentId) {
        Comment comment = commentServiceImplementation.manageCommentBlockState(commentId);
        return ResponseEntity.ok(comment);
    }
}
