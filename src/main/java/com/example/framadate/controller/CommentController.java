package com.example.framadate.controller;

import com.example.framadate.model.comment_dtos.ClientCommentDto;
import com.example.framadate.model.comment_dtos.CommentDto;
import com.example.framadate.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{surveyId}/comments")
    public List<CommentDto> comments(@PathVariable Long surveyId) {
        return commentService.findAllComments(surveyId);

    }

    @PostMapping(value = "/{surveyId}/comments")
    public CommentDto comment(@PathVariable Long surveyId, @Valid @RequestBody ClientCommentDto commentDto) {
        return commentService.comment(surveyId, commentDto);
    }

    @PutMapping(value = "/comments/{commentId}")
    public CommentDto updateComment(@PathVariable Long commentId, @Valid @RequestBody ClientCommentDto commentDto) {
        return commentService.updateComment(commentDto, commentId);

    }

    @DeleteMapping(value = "/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);

    }
}
