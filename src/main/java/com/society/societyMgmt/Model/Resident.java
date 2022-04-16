package com.society.societyMgmt.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resident {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@NotNull
	private String owner;
	
	@NotNull
	private String flatNumber;
	
	@NotNull
	private String flatSize;
	
	@NotNull
	private String towerNumber;
	
	@NotNull
    private String floorNumber;
}
