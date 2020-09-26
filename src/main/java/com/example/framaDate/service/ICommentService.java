package com.example.framaDate.service;

import java.util.List;

public interface ICommentService {


    /* get comments of a specific survey
   params :
   surveyID : the id of the survey of want to select it's comments. a default limit will be applied to the result !
   */
    List<Comment> getComments(int surveyID);

    /* get comments of a specific survey
    params :
    surveyID : the id of the survey of want to select it's comments
    count : how many comments would you like to select
    skip  : how many comments to skip (for pagination)
     */
    List<Comment> getComments(int surveyID, int count, int skip);

}
