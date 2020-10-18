package com.example.framadate.mapper;

import com.example.framadate.entity.Comment;
import com.example.framadate.model.commentDtos.CommentDto;
import com.example.framadate.model.commentDtos.ClientCommentDto;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class CommentMapper {
    public CommentDto toDto(Comment comment){
        return CommentDto.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .creationDate(comment.getCreationDate())
                        .lastUpdate(comment.getLastUpdate())
                        .userId(comment.getUser().getId())
                        .build();
    }
    public Comment toEntity(ClientCommentDto commentDto){
        return Comment.builder()
                .comment(commentDto.getComment().trim())
                .lastUpdate(new Date()) //TODO move to business logic
                .creationDate(new Date()) //TODO move to business logic
                .build();
    }
    public void toEntity(Comment comment, ClientCommentDto commentDto){//TODO custom exceptions
        if (UserMapper.isNullOrEmpty(commentDto.getComment()))
            throw new IllegalArgumentException("empty or null parametters");
        comment.setComment(commentDto.getComment());
        comment.setLastUpdate(new Date()); //TODO move to business logic
    }
}
