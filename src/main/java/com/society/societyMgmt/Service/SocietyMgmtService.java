package com.society.societyMgmt.Service;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;

import com.society.societyMgmt.Model.Resident;

public interface SocietyMgmtService {
	
	Resident insertResident(Resident resident) throws ServiceException;

	List<Resident> getAllData() throws ServiceException;

	Resident getDatabyName(String owner) throws ServiceException;

	Resident updateData(@Valid Resident resident, String owner) throws ServiceException;

	String deleteByOwner(String owner) throws ServiceException;
	
	String deleteAll() throws ServiceException;

}
