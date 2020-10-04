package com.example.framadate.controller;

import com.example.framadate.model.CommentDto;
import com.example.framadate.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class CommentController {

    private final SurveyService surveyService;

    public CommentController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long id) {
        return Optional
                .ofNullable( surveyService.findAllComments(id) )
                .map( commentDtos -> ResponseEntity.ok().body(commentDtos) ) //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
    @PostMapping(value="/{id}/comments")
    public ResponseEntity<CommentDto> comment(@PathVariable Long id,@RequestBody CommentDto commentDto){

        return Optional
                .ofNullable(surveyService.comment(id,commentDto))
                .map(comment ->ResponseEntity.ok().body(comment) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping(value="/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId,@RequestBody CommentDto commentDto){
        return Optional
                .ofNullable(surveyService.updateComment(commentDto,commentId))
                .map(comment ->ResponseEntity.ok().body(comment) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping(value="/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        return Optional
                .ofNullable(surveyService.deleteComment(commentId))
                .map(comment ->ResponseEntity.ok().body("the comment "+comment+" has been deleted") )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("the "+commentId+" didn't match any commentId"));
    }
}
