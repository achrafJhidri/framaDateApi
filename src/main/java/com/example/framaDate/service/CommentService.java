package com.example.framadate.service;

import com.example.framadate.entity.Comment;
import com.example.framadate.mapper.CommentMapper;
import com.example.framadate.model.CommentDto;
import com.example.framadate.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    public CommentDto toDto(Comment comment) {
        return commentMapper.toDto(comment);
    }

    public Comment save(Comment commentEntity) {
        return commentRepository.saveAndFlush(commentEntity);
    }

    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) { //Not Found in db
            return null ;
        }
        comment.get().setComment(commentDto.getComment()); // the only change that's permited to come from the client
        comment.get().setLastUpdate(new Date()); // the current date is the date of the lastUpdate

        commentRepository.saveAndFlush(comment.get());

        return commentMapper.toDto(comment.get());
    }

    public String deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) { //Not Found in db
            return null ;
        }
        commentRepository.delete(comment.get());
        return comment.get().getComment();
    }
}
