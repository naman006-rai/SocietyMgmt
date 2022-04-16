package com.society.societyMgmt.Service;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.societyMgmt.DAO.DataRepo;
import com.society.societyMgmt.Model.Resident;

@Service
public class SocietyServiceImpl implements SocietyMgmtService{

	@Autowired
	private DataRepo dataRepo;
	
	@Override
	public Resident insertResident(Resident resident) throws ServiceException {
		
		if(dataRepo.findByTowerNumber(resident.getTowerNumber())!=null) {
			List<Resident> data = dataRepo.findByTowerNumber(resident.getTowerNumber());
			for(Resident res: data) {
				if(res.getFloorNumber().equals(resident.getFloorNumber())&& res.getFlatNumber().equals(resident.getFlatNumber())) {
					throw new ServiceException("Already contains a Verified Resident");
				}
			}
		}
		 return dataRepo.save(resident);
		 
		
	}

	@Override
	public List<Resident> getAllData() {
		if (dataRepo.findAll() == null ) {
			throw new ServiceException("Resident Not Found");
		 }
		return dataRepo.findAll();
		 
	}

	@Override
	public Resident getDatabyName(String owner) {
		if (dataRepo.findByOwner(owner) == null ) {
			throw new ServiceException("Resident Not Found");
		 }
		return dataRepo.findByOwner(owner);
	}

	@Override
	public Resident updateData(@Valid Resident resident, String owner) {
		Resident oldData = dataRepo.findByOwner(owner);
		if (oldData != null ) {
			oldData.setFlatNumber(resident.getFlatNumber());
			oldData.setFlatSize(resident.getFlatSize());
			oldData.setOwner(resident.getOwner());
			oldData.setTowerNumber(resident.getTowerNumber());
			oldData.setFloorNumber(resident.getFloorNumber());
			return dataRepo.save(oldData);
		}
			throw new ServiceException("Resident Not Found");
	}

	@Override
	public String deleteByOwner(String owner) {
		Resident resident = dataRepo.findByOwner(owner);
		if (resident != null ) {
			dataRepo.deleteById(resident.getId());
			return "Deleted Succesfully";
		 }
		throw new ServiceException("Resident Not Found");

	}

	@Override
	public String deleteAll() throws ServiceException {
		dataRepo.deleteAll();
		return "Deleted Succesfully";
	}


}
