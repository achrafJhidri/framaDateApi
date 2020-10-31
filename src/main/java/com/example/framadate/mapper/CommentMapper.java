package com.example.framadate.mapper;

import com.example.framadate.entity.Comment;
import com.example.framadate.model.commentDtos.ClientCommentDto;
import com.example.framadate.model.commentDtos.CommentDto;
import org.springframework.stereotype.Component;


@Component
public class CommentMapper {
    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .creationDate(comment.getCreationDate())
                .lastUpdate(comment.getLastUpdate())
                .userId(comment.getUser().getId())
                .build();
    }

    public Comment toEntity(ClientCommentDto commentDto) {
        return Comment.builder()
                .comment(commentDto.getComment().trim())
                .build();
    }

    public void toEntity(Comment comment, ClientCommentDto commentDto) {
        comment.setComment(commentDto.getComment());
    }
}
