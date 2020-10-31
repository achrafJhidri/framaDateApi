package com.example.framadate.controller;

import com.example.framadate.model.voteDtos.PostVoteDto;
import com.example.framadate.model.voteDtos.PutVoteDto;
import com.example.framadate.model.voteDtos.VoteDto;
import com.example.framadate.service.VoteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/{surveyId}/votes")
    public VoteDto vote(@PathVariable Long surveyId, @Valid @RequestBody PostVoteDto voteDto) {
        return voteService.vote(surveyId, voteDto);
    }

    @PutMapping(value = "/{surveyId}/votes")
    public VoteDto updateVote(@PathVariable Long surveyId, @Valid @RequestBody PutVoteDto voteDto) {
        return voteService.updateVote(surveyId, voteDto);
    }

    @GetMapping(value = "/{surveyId}/votes")
    public List<VoteDto> votes(@PathVariable Long surveyId) {
        return voteService.findAll(surveyId);
    }
}
