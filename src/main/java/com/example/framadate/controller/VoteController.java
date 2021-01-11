package com.example.framadate.controller;

import com.example.framadate.model.vote_dtos.PostVoteDto;
import com.example.framadate.model.vote_dtos.PutVoteDto;
import com.example.framadate.model.vote_dtos.VoteDto;
import com.example.framadate.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/framadate/api/v1/surveys")
@Api(tags = {"Survey's votes Resource REST Endpointt"})
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @ApiOperation(value = "vote for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
            @ApiResponse(code = 409, message = "votes are closed for the specified survey")
    })
    @PostMapping(value = "/{surveyId}/votes")
    public VoteDto vote(@PathVariable Long surveyId, @Valid @RequestBody PostVoteDto voteDto) {
        return voteService.vote(surveyId, voteDto);
    }

    @ApiOperation(value = "vote for the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 400, message = "invalid model"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
            @ApiResponse(code = 409, message = "votes are closed for the specified survey")
    })
    @PutMapping(value = "/{surveyId}/votes")
    public VoteDto updateVote(@PathVariable Long surveyId, @Valid @RequestBody PutVoteDto voteDto) {
        return voteService.updateVote(surveyId, voteDto);
    }

    @ApiOperation(value = "returns the votes of the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @GetMapping(value = "/{surveyId}/votes")
    public List<VoteDto> votes(@PathVariable Long surveyId) {
        return voteService.findAll(surveyId);
    }
}
