package com.ticket.tracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_dtls")
public class Tickets {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private String title;	
	private String description;
	private String content;
	private LocalDate date;
	
	public Tickets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tickets(String title, String description, String content, LocalDate date) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
		this.date = date;
	}

	public long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Tickets [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
	
	

}
