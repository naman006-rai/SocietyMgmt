package com.society.societyMgmt.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.societyMgmt.Model.Resident;


public interface DataRepo extends JpaRepository<Resident,Integer>{

	Resident findByOwner(String owner);

	List<Resident> findByTowerNumber(String towerNumber);

}