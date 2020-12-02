package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository{

	@SuppressWarnings("deprecation")
	Resident resident1;
	Resident resident2;
	
	public void neueResident(Resident resident) {
		if (resident1 == null) {
			resident1 = resident;
		} else
			resident2 = resident;
	}
	public List<Resident> getResidents() {
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(resident1);
		liste.add(resident2);
		return liste;
	}

}
