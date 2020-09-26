package com.example.framaDate.service;

import java.util.List;

public interface ICommentService {

    List<Comment> getComments(int surveyID);

    List<Comment> getComments(int surveyID, int count =100, int skip);

}
