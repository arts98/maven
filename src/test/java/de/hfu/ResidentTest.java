package de.hfu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import junit.framework.TestCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

public class ResidentTest extends TestCase {
	
	public void testResidentMock() {
		Resident resident1 = new Resident("Hans", "Müller", "Karlstrasse", "Schwenningen", new Date(1993, 05, 02));
		Resident resident2 = new Resident("Thomas", "Schmidt", "Salinenstrasse", "Villingen", new Date(1987, 10, 23));
		Resident resident3 = new Resident("Hans", "Müller", "Kar*", "Schwe*", new Date(1993, 05, 02));
		List<Resident> liste = Arrays.asList(resident1, resident2);
		BaseResidentService service = new BaseResidentService();
		
		ResidentRepository repoMock = createMock(ResidentRepository.class);
		expect(repoMock.getResidents()).andReturn(liste);
		replay(repoMock);
		
		service.setResidentRepository(repoMock);
		List<Resident> liste1 = new ArrayList<Resident>();
		liste1.add(resident1);
		List<Resident> resultList = service.getFilteredResidentsList(resident3);
		
		assertEquals(liste1.size(), resultList.size());
		assertTrue(resultList.containsAll(liste1));
		assertFalse(resultList.contains(resident2));
		verify(repoMock);
	}
	
	@SuppressWarnings("deprecation")
	public void testResidentsList() {
		
		Resident resident1 = new Resident("Hans", "Müller", "Karlstrasse", "Schwenningen", new Date(1993, 05, 02));
		@SuppressWarnings("unused")
		Resident resident2 = new Resident("Thomas", "Schmidt", "Salinenstrasse", "Villingen", new Date(1987, 10, 23));
		Resident resident3 = new Resident("Hans", "Müller", "Kar*", "Schwe*", new Date(1993, 05, 02));
		BaseResidentService service = new BaseResidentService();
		ResidentRepository stub = new ResidentRepositoryStub();
		stub.neueResident(resident1);
		stub.neueResident(resident2);
		service.setResidentRepository(stub);
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(resident1);
		List<Resident> resultList = service.getFilteredResidentsList(resident3);
		assertEquals(liste.size(), resultList.size());
		assertTrue(resultList.containsAll(liste));
		assertFalse(resultList.contains(resident2));
	}
	
	public void testResidentUnique() {
		Resident resident1 = new Resident("Hans", "Müller", "Karlstrasse", "Schwenningen", new Date(1993, 05, 02));
		@SuppressWarnings("unused")
		Resident resident2 = new Resident("Thomas", "Schmidt", "Salinenstrasse", "Villingen", new Date(1987, 10, 23));
		Resident resident3 = new Resident("Hans", "Müller", "Kar*", "Schwe*", new Date(1993, 05, 02));
		Resident resident4 = new Resident("Werner", "Siebert", "Friedrichstrasse", "Dauchingen", new Date(2000, 8, 12));
		BaseResidentService service = new BaseResidentService();
		ResidentRepository stub = new ResidentRepositoryStub();
		stub.neueResident(resident1);
		stub.neueResident(resident2);
		service.setResidentRepository(stub);
		try {
			service.getUniqueResident(resident3);
			fail("Wildcard erlaubt");
		} catch (ResidentServiceException e) {}
		try {
		assertTrue(resident1.equals(service.getUniqueResident(resident1)));
		} catch (ResidentServiceException e) {}
		try {
			service.getUniqueResident(resident4);
			fail("Resident nicht vorhanden");
		} catch (ResidentServiceException e) {}
	}
}
