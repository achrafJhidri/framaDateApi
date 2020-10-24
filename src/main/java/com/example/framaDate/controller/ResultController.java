package com.example.framadate.controller;

import com.example.framadate.entity.IResult;
import com.example.framadate.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/framadate/api/v1/surveys")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


    @GetMapping(value = "/{surveyId}/result")
    public ResponseEntity<HashMap<String, IResult>> getResults(@PathVariable Long surveyId) {

        //TODO catch suveyId not Found

        return ResponseEntity.ok().body(resultService.getResults(surveyId));
    }
}
