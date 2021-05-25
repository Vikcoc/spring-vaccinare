package com.vaccin.vaccin;

import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
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


////	@TestConfiguration
////	static class EmployeeServiceImplTestContextConfiguration {
////
////		@Bean
////		public VaccineCenterService vaccineCenterService() {
////			return new VaccineCenterService();
////		}
////	}
//
//	@Autowired
//	private VaccineCenterService vaccineCenterService;
//
	@Mock
	private VaccineCenterRepository vaccineCenterRepository;
	@Mock
	private VaccineTypeRepository vaccineTypeRepository;

	@Before
	public void setUp() {
//		Mock.init(vaccineCenterRepository);
		VaccineCenter vc = new VaccineCenter();
		vc.setAddress("O adresa");
		var lst = new ArrayList<>();
		lst.add(vc);
		Mockito.when(vaccineCenterRepository.findAll())
				.thenReturn((List) lst);
	}

//	@Before
//	public void runBare(){
//		System.out.println("before");
//	}
//
//	@Test
//	public void testingMethod(){
//		System.out.println("testing");
//	}
//
//	@After
//	public void setDirty(){
//		System.out.println("after");
//	}

	@Test
	public void contextLoads() {
		System.out.println("Is in test");
		System.out.println(vaccineCenterRepository.findAll().stream().findFirst().get().getAddress());
		Assert.notEmpty(vaccineCenterRepository.findAll());
	}

}
