package com.telusko.springSecurityEx.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	@Id
	private int id;
	private String username;
	private String applicationname;
	private String applicationdate;
	private String customername;
	private String stage;
	private int tat;
}
