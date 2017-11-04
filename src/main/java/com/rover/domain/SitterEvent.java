package com.rover.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="sitter_event",
	indexes = { 
		@Index(name = "idx_sitter_event_sitter_id", columnList = "sitter_id")
	}
)
public class SitterEvent implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name = "start_date", nullable = false)
    private Calendar startDate;
    
    @Column(name = "end_date", nullable = false)
    private Calendar endDate;

    @ManyToOne(cascade=CascadeType.ALL)
    private User sitter;

    @ManyToOne(cascade=CascadeType.ALL)
    private User owner;
    
    @Column(nullable = false)
    private String dogs;

    @Column(updatable = false, nullable = false)
    private double rating;
    
    @Column(nullable = false)
    @Type(type="text")
    private String description;
    
    @Column(updatable = false, name = "created_date", nullable = false)
    private Calendar createdDate;
    private Calendar updatedDate;
    
    public SitterEvent() {
    	
    }
    
    public SitterEvent(Calendar startDate, Calendar endDate, User sitter, User owner, String dogsPipeSep, double rating, String description) {
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.sitter = sitter;
    	this.owner = owner;
    	if(dogsPipeSep != null) {
    		String[] dogs = dogsPipeSep.split("|");
    		for(String dog : dogs) {
    			if(this.dogs != null && this.dogs.trim().length() > 0) {
        	    	this.dogs += ", ";
    			}
    	    	this.dogs += dog;
    		}
    	}
    	this.rating = rating;
    	this.description = description;
    	this.createdDate = Calendar.getInstance();
    	this.updatedDate = Calendar.getInstance();
    }
	/**
	 * @return the eventId
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setId(Long eventId) {
		this.id = eventId;
	}
	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the sitter
	 */
	public User getSitter() {
		return sitter;
	}
	/**
	 * @param sitter the sitter to set
	 */
	public void setSitter(User sitter) {
		this.sitter = sitter;
	}
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @return the dogs
	 */
	public String getDogs() {
		return dogs;
	}
	/**
	 * @param dogs the dogs to set
	 */
	public void setDogs(String dogs) {
		this.dogs = dogs;
	}
	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createdDate
	 */
	public Calendar getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
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
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

}