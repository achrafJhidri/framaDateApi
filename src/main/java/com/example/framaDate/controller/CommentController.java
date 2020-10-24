package com.example.framadate.controller;

import com.example.framadate.model.commentDtos.ClientCommentDto;
import com.example.framadate.model.commentDtos.CommentDto;
import com.example.framadate.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{surveyId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long surveyId) {
        return Optional //TODO catch notFoundException
                .ofNullable(commentService.findAllComments(surveyId))
                .map(commentDtos -> ResponseEntity.ok().body(commentDtos)) //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{surveyId}/comments")
    public ResponseEntity<CommentDto> comment(@PathVariable Long surveyId, @Valid @RequestBody ClientCommentDto commentDto) {

        return Optional //TODO catch notFoundException
                .ofNullable(commentService.comment(surveyId, commentDto))
                .map(comment -> ResponseEntity.ok().body(comment))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(value = "/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @Valid @RequestBody ClientCommentDto commentDto) {
        return Optional //TODO catch notFoundException
                .ofNullable(commentService.updateComment(commentDto, commentId))
                .map(comment -> ResponseEntity.ok().body(comment))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return Optional //TODO catch notFoundException
                .ofNullable(commentService.deleteComment(commentId))
                .map(comment -> ResponseEntity.ok().body("the comment " + comment + " has been deleted"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("the " + commentId + " didn't match any commentId"));
    }
}
