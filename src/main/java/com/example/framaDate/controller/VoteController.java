package com.example.framadate.controller;

import com.example.framadate.model.voteDtos.ClientVoteDto;
import com.example.framadate.model.voteDtos.VoteDto;
import com.example.framadate.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value="/{surveyId}/votes")
    public ResponseEntity<VoteDto> vote(@PathVariable Long surveyId,@Valid @RequestBody PostVoteDto voteDto) {
        return Optional //TODO catch the notfoundException
                .ofNullable(voteService.vote(surveyId,voteDto))
                .map(createdVote ->ResponseEntity.ok().body(createdVote) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping(value="/{surveyId}/votes")
    public ResponseEntity<List<VoteDto>> votes(@PathVariable Long surveyId){
        return Optional //TODO catch the notfoundException
                .ofNullable(voteService.findAll(surveyId))
                .map(votes ->ResponseEntity.ok().body(votes) )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
