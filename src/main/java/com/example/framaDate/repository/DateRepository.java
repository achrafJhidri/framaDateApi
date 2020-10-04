package com.example.framadate.repository;

import com.example.framadate.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date, java.util.Date> {
}
