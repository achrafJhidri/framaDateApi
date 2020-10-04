package com.example.framadate.repository;

import com.example.framadate.entity.Vote;
import com.example.framadate.entity.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
