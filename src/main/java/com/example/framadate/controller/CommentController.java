package com.example.framadate.controller;

import com.example.framadate.model.comment_dtos.ClientCommentDto;
import com.example.framadate.model.comment_dtos.CommentDto;
import com.example.framadate.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
@Api(tags = {"Comment Resource REST Endpoint"})
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "returns the comments of the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @GetMapping(value = "/{surveyId}/comments")
    public List<CommentDto> comments(@PathVariable Long surveyId) {
        return commentService.findAllComments(surveyId);

    }

    @ApiOperation(value = "post a comment for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey or the given user wasn't found"),
    })
    @PostMapping(value = "/{surveyId}/comments")
    public CommentDto comment(@PathVariable Long surveyId, @Valid @RequestBody ClientCommentDto commentDto) {
        return commentService.comment(surveyId, commentDto);
    }

    @ApiOperation(value = "edit a comment for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested comment wasn't found"),
            @ApiResponse(code = 409, message = "trying to update a comment who's not yours"),
    })
    @PutMapping(value = "/comments/{commentId}")
    public CommentDto updateComment(@PathVariable Long commentId, @Valid @RequestBody ClientCommentDto commentDto) {
        return commentService.updateComment(commentDto, commentId);

    }

    @ApiOperation(value = "edit a comment for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested comment wasn't found")
    })
    @DeleteMapping(value = "/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);

    }
}
