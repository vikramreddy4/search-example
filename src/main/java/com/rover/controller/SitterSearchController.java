package com.rover.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rover.domain.SitterRank;
import com.rover.domain.SitterResponse;
import com.rover.repository.SitterRankRepository;
import com.rover.repository.UserRepository;

@Controller
@RequestMapping(path = "/sitter")
public class SitterSearchController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SitterRankRepository sitterRankRepository;

	@GetMapping(path = "/search")
	public @ResponseBody Iterable<SitterRank> search(@RequestParam String rating) {
		return sitterRankRepository.findAll();
	}

	@GetMapping(path = "/all")
	public @ResponseBody SitterResponse getAll() {
		return new SitterResponse(sitterRankRepository.findByOrderByRankDesc());
	}
}