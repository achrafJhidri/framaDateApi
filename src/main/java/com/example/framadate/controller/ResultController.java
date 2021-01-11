package com.example.framadate.controller;

import com.example.framadate.entity.IGenericResult;
import com.example.framadate.service.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
@Api(tags = {"Survey's results Resource REST Endpoint"})
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @ApiOperation(value = "returns the results of the specified survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The requested survey wasn't found"),
    })
    @GetMapping(value = "/{surveyId}/result")
    public Map<String, IGenericResult> getResults(@PathVariable Long surveyId) {
        return resultService.getResults(surveyId);
    }
}
