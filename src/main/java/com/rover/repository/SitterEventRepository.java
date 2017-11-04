package com.rover.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rover.domain.SitterEvent;
import com.rover.domain.User;

@Repository
public interface SitterEventRepository extends CrudRepository<SitterEvent, Long> {
	List<SitterEvent> findBySitter(User sitter);
}