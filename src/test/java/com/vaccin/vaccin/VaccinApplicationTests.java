package com.vaccin.vaccin;

import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
import com.vaccin.vaccin.service.VaccineCenterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


//@RunWith(JUnit4.class)
@RunWith(MockitoJUnitRunner.class)
public class VaccinApplicationTests {

	@Mock
	private VaccineCenterRepository vaccineCenterRepository;
	@Mock
	private VaccineTypeRepository vaccineTypeRepository;

	@Before
	public void setUp() {
		VaccineCenter vc = new VaccineCenter();
		vc.setAddress("O adresa");
		vc.setLongitude(0D);
		vc.setLatitude(0D);
		VaccineType vt = new VaccineType();
		vt.setBrand("Some brand");
		vc.setVaccineType(vt);
		var lst = new ArrayList<>();
		lst.add(vc);
		Mockito.when(vaccineCenterRepository.findAll())
				.thenReturn((List) lst);
	}

	@Test
	public void contextLoads() {
		var service = new VaccineCenterService(vaccineCenterRepository, vaccineTypeRepository);
//		System.out.println(service.getCentersAroundCoords(0D,0D));
		System.out.println("This is to see that the test works");
		Assert.notEmpty(service.getCentersAroundCoords(0D,0D), "It may work");
	}

}
