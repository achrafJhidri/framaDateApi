package com.example.framadate.service;

import com.example.framadate.entity.Comment;
import com.example.framadate.entity.Survey;
import com.example.framadate.entity.User;
import com.example.framadate.exceptions.NotAllowedException;
import com.example.framadate.exceptions.NotFoundException;
import com.example.framadate.mapper.CommentMapper;
import com.example.framadate.model.comment_dtos.ClientCommentDto;
import com.example.framadate.model.comment_dtos.CommentDto;
import com.example.framadate.repository.CommentRepository;
import com.example.framadate.repository.SurveyRepository;
import com.example.framadate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.framadate.model.Constants.*;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;


    public CommentService(CommentRepository commentRepository, SurveyRepository surveyRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> findAllComments(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        return survey.map(value -> value.getComments()
                .stream().map(commentMapper::toDto).collect(Collectors.toList())).orElseThrow(() -> new NotFoundException(SURVEY + surveyId));
    }

    public CommentDto comment(Long surveyId, ClientCommentDto commentDto) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isEmpty()) {
            throw new NotFoundException(SURVEY + surveyId);
        }

        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty())
            throw new NotFoundException(USER + commentDto.getUserId());

        Comment commentEntity = commentMapper.toEntity(commentDto);
        commentEntity.setSurvey(survey.get());
        commentEntity.setUser(user.get());
        commentEntity.setLastUpdate(new Date());
        commentEntity.setCreationDate(new Date());
        commentEntity = commentRepository.saveAndFlush(commentEntity);
        return commentMapper.toDto(commentEntity);
    }


    public CommentDto updateComment(ClientCommentDto commentDto, Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new NotFoundException(COMMENT + commentId);
        }

        commentMapper.toEntity(comment.get(), commentDto);
        comment.get().setLastUpdate(new Date());
        if (!commentDto.getUserId().equals(comment.get().getUserId()))
            throw new NotAllowedException("you're not supposed to edit others comment");

        return commentMapper.toDto(commentRepository.saveAndFlush(comment.get()));
    }

    public String deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new NotFoundException(COMMENT + commentId);
        }
        commentRepository.delete(comment.get());
        return comment.get().getContent() + " has been deleted";
    }
}
