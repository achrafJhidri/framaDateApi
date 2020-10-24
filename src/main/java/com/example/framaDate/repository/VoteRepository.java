package com.example.framadate.repository;

import com.example.framadate.entity.IResult;
import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    @Query(value = "select count(v.*)  as totalVotes,v.date_id as VotingDate " +
            "from vote as v " +
            "where v.survey_id=?1 and v.availability =?2 " +
            "group by V.date_id " +
            "order by count(v.*) desc "
            , nativeQuery = true)
    Set<IResult> countVotesForSomeAvailability(Long surveyId, char availability);

    @Query(value = "select count(v.*)  as totalVotes,v.date_id as VotingDate " +
            "from vote as v " +
            "where v.survey_id=?1 and (v.availability = 'A' or v.availability ='M') " +
            "group by V.date_id " +
            "order by count(v.*) desc "
            , nativeQuery = true)
    Set<IResult> countMaybeOrAvailableVotes(Long surveyId);

}
