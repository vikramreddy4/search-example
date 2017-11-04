package com.rover.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rover.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByEmail(String email);

	@Query("SELECT sr FROM SitterRank sr where sr.rank <= :rankTerm")
	List<User> searchSitters(@Param("rankTerm") String rankTerm);
}