package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
