package com.vaccin.vaccin;

import HelperForTesting.GenerateCenters;
import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
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
		VaccineCenterService service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		assert !service.getCentersAroundCoords(0.1D,0.1D).isEmpty() : "List is empty";
	}

	@Test
	public void getCentersAroundCoords_DoesntGetByWrong() {
		VaccineCenterService service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		assert service.getCentersAroundCoords(1D,1D).isEmpty() : "List is not empty";
	}

	@Test
	public void addCenter_GivesAffirmativeAnswer(){
		VaccineCenterService service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto centerDto = new VaccineCenterCreateDto();
		centerDto.setAddress("Some address");
		centerDto.setLatitude(0D);
		centerDto.setLongitude(0D);
		centerDto.setVaccineTypeId(1);
		centerDto.setName("Some type");
		centerDto.setDosesAvailable(400);

		VaccineCenterDto res = new VaccineCenterDto();
		try {
			 res = service.addCenter(centerDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assert res.getName().equals("Some type")
				&& res.getLatitude() == 0D
				&& res.getLongitude() == 0D
				&& res.getAddress().equals("Some address")
				&& res.getDosesAvailable() == 400;

	}

	@Test(expected = Exception.class)
	public void addCenter_GivesNegativeAnswer() throws Exception {
		VaccineCenterService service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto centerDto = new VaccineCenterCreateDto();
		centerDto.setAddress("Some address");
		centerDto.setLatitude(0D);
		centerDto.setLongitude(0D);
		centerDto.setVaccineTypeId(0);
		centerDto.setName("Some type");
		centerDto.setDosesAvailable(400);

		service.addCenter(centerDto);
	}

	@Test
	public void addCenter_SavesToRepo(){
		VaccineCenterService service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
		VaccineCenterCreateDto centerDto = new VaccineCenterCreateDto();
		centerDto.setAddress("Some address");
		centerDto.setLatitude(0D);
		centerDto.setLongitude(0D);
		centerDto.setVaccineTypeId(1);
		centerDto.setName("Some type");
		centerDto.setDosesAvailable(400);

		try {
			service.addCenter(centerDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		verify(vaccineCenterRepository).save(ArgumentMatchers.any());
	}

}
