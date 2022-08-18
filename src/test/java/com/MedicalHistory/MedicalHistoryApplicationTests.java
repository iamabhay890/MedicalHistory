package com.MedicalHistory;

import com.MedicalHistory.controllers.UserController;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalHistoryApplicationTests {


	@InjectMocks
	UserController userController;

	@Mock
	UserDto userDto;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}


/*	@Test
	public void CreateUserTest(){
		User user = Mockito.mock(User.class);
		*//*when(listMock.add(anyString())).thenReturn(false);*//*

		user.setId(1);
		user.setName("Rahul Sharma");
		user.setAge(23);
		user.setGender("Male");
		user.setPhone("9898989898");
		user.setAddress("Delhi");
		user.setEmail("rahul@gmail.com");
		user.setPassword("rahul");
		when(userRepo.save(user)).thenReturn(user);
		assertThat(user, userService.(c));





	}*/

	 /*  @Test
    @DisplayName("Save Method Calling ")
    public void testCreateNewObject() {
        User user = new User();
        user.setId(20);
        user.setName("Rahul kumar");
        user.setAge(23);
        user.setGender("Male");
        user.setPhone("9898989898");
        user.setAddress("Delhi");
        user.setEmail("rahul111@gmail.com");
        user.setPassword("rahul");
        when(userRepo.save(any(User.class))).thenReturn(user);
        User savedCustomer = userRepo.save(user);
        assertThat(savedCustomer.getName()).isNotNull();
    }*/

/*	@Test
	@Order(4)
	@DisplayName("Get All Slips Test ")
	public void getAllSlips(){
		patient = when(patientRepo.findAll().size()).getMock();

		List<Patient> patients = patientRepo.findAll();
		assertEquals(patient,patients.size());
		//Assertions.assertThat(patients.size()).isGreaterThan(0);
	}*/
}
