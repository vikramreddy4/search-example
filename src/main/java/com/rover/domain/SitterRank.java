package com.rover.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "sitter_rank", 
		uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" })},
		indexes = { 
					@Index(name = "idx_sitter_rank_rank", columnList = "rank"),
					@Index(name = "idx_sitter_rank_user_id", columnList = "user_id")
				}
		)
public class SitterRank implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@Column(name = "rating_score", nullable = false)
	private double ratingScore;

	@Column(nullable = false)
	private double rank;

	@Column(updatable = false, name = "created_date", nullable = false)
	private Calendar createdDate;
	private Calendar updatedDate;

	public void calculateAndSetRank(List<SitterEvent> events, NumberFormat nf) {
		if (events == null || events.isEmpty()) {
			this.rank = ratingScore;
			this.rank = Double.parseDouble(nf.format(rank));
			return;
		}
		long eventCount = 0;
		double eventScore = 0;
		for (SitterEvent event : events) {
			eventCount++;
			eventScore += event.getRating();
		}
		if (eventCount < 10) {
			double weightFactor = ((eventScore / eventCount) - ratingScore) / 10;
			this.rank = ratingScore + (weightFactor * eventCount);
		} else {
			this.rank = (eventScore / eventCount);
		}
		this.rank = Double.parseDouble(nf.format(rank));
	}

	public SitterRank() {

	}

	public SitterRank(User user) {
		if (user.getName() != null) {
			this.ratingScore = getRatingScore(user.getName().toLowerCase());
		}
		this.user = user;
		this.createdDate = Calendar.getInstance();
		this.updatedDate = Calendar.getInstance();
	}

	protected double getRatingScore(String name) {
		if(name == null) {
			return 0;
		}
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < name.length(); ++i){
		    char ch = name.charAt(i);
		    if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))
		        set.add(name.charAt(i));
		}
		return ((5 * set.size())/ 26);
	}
	/**
	 * @return the userId
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the ratingScore
	 */
	public double getRatingScore() {
		return ratingScore;
	}

	/**
	 * @param ratingScore
	 *            the ratingScore to set
	 */
	public void setRatingScore(double ratingScore) {
		this.ratingScore = ratingScore;
	}

	/**
	 * @return the rank
	 */
	public double getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(double rank) {
		this.rank = rank;
	}

	/**
	 * @return the createdDate
	 */
	public Calendar getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Calendar getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}