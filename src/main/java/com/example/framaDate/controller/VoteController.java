package com.example.framadate.controller;

import com.example.framadate.model.VoteDto;
import com.example.framadate.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class VoteController {
    private final SurveyService surveyService;

    public VoteController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping(value="/{surveyId}/votes")
    public ResponseEntity<VoteDto> vote(@PathVariable Long surveyId, @RequestBody VoteDto voteDto) {
        return Optional
                .ofNullable(surveyService.vote(surveyId,voteDto))
                .map(createdVote ->ResponseEntity.ok().body(createdVote) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping(value="/{surveyId}/votes")
    public ResponseEntity<List<VoteDto>> votes(@PathVariable Long surveyId){
        return Optional
                .ofNullable(surveyService.findAll(surveyId))
                .map(votes ->ResponseEntity.ok().body(votes) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
