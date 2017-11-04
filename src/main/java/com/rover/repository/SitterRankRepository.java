package com.rover.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rover.domain.SitterRank;
import com.rover.domain.User;

@Repository
public interface SitterRankRepository extends CrudRepository<SitterRank, Long> {
	List<SitterRank> findByUser(User user);
	List<SitterRank> findByOrderByRankDesc();
	List<SitterRank> findAll();
}