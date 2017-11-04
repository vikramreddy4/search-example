package com.rover.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rover.domain.SitterEvent;
import com.rover.domain.SitterRank;
import com.rover.domain.User;
import com.rover.repository.SitterEventRepository;
import com.rover.repository.SitterRankRepository;
import com.rover.repository.UserRepository;

@Controller
@RequestMapping(path = "/file")
public class FileProcessController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SitterRankRepository sitterRankRepository;

	@Autowired
	private SitterEventRepository sitterEventRepository;

	private static final String SUFFIX_SUCCESS = ".success";
	private static final String SUFFIX_FAIL = ".fail";

	private static Log log = LogFactory.getLog(FileProcessController.class);

	@PostMapping(path = "/start")
	public @ResponseBody String add() {
//		if (run != null && run.trim().toLowerCase().equals("true")) {
			return processFile();
//		}
//		return "no file processed";
	}

	private String processFile() {
		BufferedReader br = null;
		BufferedWriter bwFailure = null;
		BufferedWriter bwSuccess = null;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		try {
			String fileName = "reviews.csv";
			String target = new File(".").getCanonicalPath() + "/";
			log.info("File path : "+target);
			String errorFileName = fileName + SUFFIX_FAIL;
			String successFileName = fileName + SUFFIX_SUCCESS;
			br = new BufferedReader(new FileReader(target + fileName));
			bwFailure = new BufferedWriter(new FileWriter(target + errorFileName));
			bwSuccess = new BufferedWriter(new FileWriter(target + successFileName));

			// Open the file for reading
			String thisLine = null;
			int count = 0;
			while ((thisLine = br.readLine()) != null) { // while loop begins
				if(count == 0) {
					count++;
					continue;
				}
															// here
				String[] parts = thisLine.trim().split(",");
				if (parts == null || parts.length < 13) {
					log.error("Invalid data for event : " + thisLine);
					bwFailure.write(thisLine);
					bwFailure.newLine();
					continue;
				}
				String rating = parts[0].trim();
				String sitterImage = parts[1].trim();
				String endDateStr = parts[2].trim();
				String description = parts[3].trim();
				String ownerImage = parts[4].trim();
				String dogsPipeSep = parts[5].trim();
				String sitterName = parts[6].trim();
				String ownerName = parts[7].trim();
				String startDateStr = parts[8].trim();
				String sitterPhone = parts[9].trim();
				String sitterEmail = parts[10].trim();
				String ownerPhone = parts[11].trim();
				String ownerEmail = parts[12].trim();
				

				try {
					List<User> sitters = userRepository.findByEmail(sitterEmail);
					User sitter = null;
					User owner = null;
					if (sitters == null || sitters.isEmpty()) {
						sitter = new User(sitterEmail, sitterName, sitterPhone, sitterImage, "sitter");
						userRepository.save(sitter);
					} else {
						sitter = sitters.get(0);
					}
					List<User> owners = userRepository.findByEmail(ownerEmail);
					if (owners == null || owners.isEmpty()) {
						owner = new User(ownerEmail, ownerName, ownerPhone, ownerImage, "owner");
						userRepository.save(owner);
					} else {
						owner = owners.get(0);
					}

					List<SitterRank> sitterRanks = sitterRankRepository.findByUser(sitter);
					SitterRank sitterRank = null;
					if (sitterRanks == null || sitterRanks.isEmpty()) {
						sitterRank = new SitterRank(sitter);
						sitterRankRepository.save(sitterRank);
					}else {
						sitterRank = sitterRanks.get(0);
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();
					startDate.setTime(sdf.parse(startDateStr));
					endDate.setTime(sdf.parse(endDateStr));
					SitterEvent event = new SitterEvent(startDate, endDate, sitter, owner, dogsPipeSep,
							new Double(rating), description);
					sitterEventRepository.save(event);

					List<SitterEvent> events = sitterEventRepository.findBySitter(sitter);
					sitterRank.calculateAndSetRank(events, nf);
					sitterRankRepository.save(sitterRank);
					count++;
				} catch (Exception e) {
					log.error("#### Failed with reason : " + e.getMessage(), e);
					bwFailure.write(thisLine + "," + e.getMessage());
					bwFailure.newLine();
				}
			} // end while
		} catch (IOException e) {
			log.error("####IOException: " + e.getMessage(), e);
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {

				}
			}
			if (bwFailure != null) {
				try {
					bwFailure.close();
				} catch (Exception e) {

				}
			}
			if (bwSuccess != null) {
				try {
					bwSuccess.close();
				} catch (Exception e) {

				}
			}
			return "successfully processed";
		}
	}
}