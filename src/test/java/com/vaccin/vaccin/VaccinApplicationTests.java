package com.vaccin.vaccin;

import HelperForTesting.GenerateCenters;
import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
import com.vaccin.vaccin.service.VaccineCenterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VaccinApplicationTests {

	@Mock
	private VaccineCenterRepository vaccineCenterRepository;
	@Mock
	private VaccineTypeRepository vaccineTypeRepository;

	@Before
	public void setUp() {
		Mockito.when(vaccineCenterRepository.findAll())
				.thenReturn((List) GenerateCenters.getCenters(0L,6));

		Mockito.when(vaccineTypeRepository.findById(0L))
				.thenReturn(Optional.empty());

		VaccineType vt = new VaccineType();
		vt.setId(1L);
		vt.setBrand("Some brand 1");
		vt.setDaysBetweenShots(60);
		Mockito.when(vaccineTypeRepository.findById(1L))
				.thenReturn(Optional.of(vt));
	}

	@Test
	public void getCentersAroundCoords_GetsByRightCoords() {
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		assert !service.getCentersAroundCoords(0.1D,0.1D).isEmpty() : "List is empty";
	}

	@Test
	public void getCentersAroundCoords_DoesntGetByWrong() {
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		assert service.getCentersAroundCoords(1D,1D).isEmpty() : "List is not empty";
	}

	@Test
	public void addCenter_GivesAffirmativeAnswer(){
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto val = new VaccineCenterCreateDto();
		val.setAddress("Some address");
		val.setLatitude(0D);
		val.setLongitude(0D);
		val.setVaccineTypeId(1);
		val.setName("Some type");
		val.setDosesAvailable(400);

		var res = service.addCenter(val);

		assert res == "Vaccine Center added";
	}

	@Test
	public void addCenter_GivesNegativeAnswer(){
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto val = new VaccineCenterCreateDto();
		val.setAddress("Some address");
		val.setLatitude(0D);
		val.setLongitude(0D);
		val.setVaccineTypeId(0);
		val.setName("Some type");
		val.setDosesAvailable(400);

		var res = service.addCenter(val);

		assert res == "Vaccine Type not found";
	}

	@Test
	public void addCenter_SavesToRepo(){
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto val = new VaccineCenterCreateDto();
		val.setAddress("Some address");
		val.setLatitude(0D);
		val.setLongitude(0D);
		val.setVaccineTypeId(1);
		val.setName("Some type");
		val.setDosesAvailable(400);

		service.addCenter(val);

		verify(vaccineCenterRepository).save(ArgumentMatchers.any());
	}

}
