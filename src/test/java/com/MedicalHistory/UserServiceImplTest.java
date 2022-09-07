package com.MedicalHistory;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    MultipartFile file;
    @Autowired
    private UserServiceImpl service;
    @MockBean
    private UserRepo repository;



    UserDto userDto =  new UserDto(1,"Abhi",null,null,"abhi@gmail.com","9797979797",25,"Password","ConfirmPassword",
            null,null,null,null,null,null,null,false,null,null,null,false);


    @Test
    @DisplayName("Create User Test ")
    public void getCreateUserTest() {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());



        /*UserDto userDto = new UserDto(101,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,null,false,null,null,null,null,null,null,null,null,null,null,null,null);*/

        UserDto userDto = new UserDto(101, "rama",null,null, "rama@gmail.com", "0909090900", null, "null", "null", null, null, null, null, null, null, false, null, null, null,null,false);


        when(repository.save(any())).thenReturn(service.dtoToUser(userDto));
        assertEquals(userDto.getEmail(), service.createUser(userDto, multipartFile).getEmail());
        System.out.println("create user  " + service.dtoToUser(userDto).getId());
    }

    @Test
    @DisplayName("Create User Test ")
    public void getCreateOAuth2UserTest() {
        String email = "email", fullName = "fullName", OauthEmail = "OauthEmail";
        User user = new User();
        user.setId(1);
        user.setName(fullName);
        user.setEmail(email);
        repository.save(user);
        System.out.println("test " + user.getEmail());
        service.createOAuth2User(email, fullName, OauthEmail);
    }


    @Test
    @DisplayName("Update User Test")
    public void getUpdateUserTest() {

        User user = new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, null, null, null, null, null, null, null, false, null, null, null);

        System.out.println("user Data getName " + user.getName());
        user.setName("rajiv");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getEmail(), service.userToDto(user).getEmail());
        System.out.println("after update user " + user.getName());
    }

    @Test
    @DisplayName("UserById Test ")
    public void getUserByIdTest() {
        int userId = 100;

        User user = new User(userId, "Raj",null,null, "raj@gmail.com", "98989898898", null, null, null, null, null, null, null, null, false, null, null, null);

        doReturn(Optional.of(user)).when(repository).findById(userId);
        System.out.println("check user id " + userId);
        service.userToDto(user);
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("check user id " + service.userToDto(user).getId());
    }

    @Test
    @DisplayName("Get All Users Test ")
    public void getAllUsersTest() {
        when(repository.findAll()).thenReturn(Stream

                .of(new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, null, null, null, null, null, null, null, false, null, null, null),
                        new User(101, "Rajesh",null,null, "rajesh@gmail.com", "767676767", null, null, null, null, null, null, null, null, false, null, null, null)).collect(Collectors.toList()));

        assertEquals(2, service.getAllUsers().size());

    }

    @Test
    @DisplayName("Delete User Test ")
    public void getDeleteUserTest() {


        /*UserDto userDto = new UserDto(101,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,null,false,null,null,null);*/
        System.out.println("user Get Id  "+userDto.getId());

        UserDto userDto = new UserDto(101, "rama",null,null, "rama@gmail.com", "0909090900", null, "null", "null", null, null, null, null, null, null, false, null, null, null,null,false);
        System.out.println("user Get Id  " + userDto.getId());

        when(repository.save(any())).thenReturn(service.dtoToUser(userDto));
        System.out.println("create user  " + service.dtoToUser(userDto).getId());
        User user = service.dtoToUser(userDto);
        System.out.println("user data " + user.getId());

        //  service.deleteUser(user.getId());
        // repository.delete(user);
        // Assertions.assertThat(user.getId()).isNotEqualTo(101);
        System.out.println("after delete user id " + user.getId());


    }

    @Test
    public void getDeleteTest() {
        // User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        User user = new User();
        user.setName("Test Name");
        user.setId(1);
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        System.out.println("test before " + user.getId());
        service.deleteUser(user.getId());
        repository.deleteById(user.getId());
        // verify(repository, times(1)).delete(user);

        System.out.println("test after " + user.getId());
    }


    @Test
    @DisplayName("Update Pic Test")
    public void getUpdateProfilePictureTest() throws IOException {

        User user = new User();
        user.setId(1);
        user.setProfilePic("bbb.png");
        when(repository.findById(any())).thenReturn(Optional.of(user));
        System.out.println("user " + repository.findById(any()));
        MultipartFile file = new MockMultipartFile(user.getProfilePic(), user.getProfilePic().getBytes());
        service.updateProfilePicture(file, user.getId());
        repository.save(user);
        System.out.println("user1 " + user.getProfilePic());

    }

    @Test
    @DisplayName("Update Password Test")
    public void getUpdatePasswordTest() {
        User user = new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, "password", null, null, null, "a.png", null, null, false, null, null, null);
        System.out.println("user Data get password " + user.getPassword());
        user.setPassword("pass");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("after update user password " + user.getPassword());
    }

    @Test
    @DisplayName("Forgot Password Test")
    public void getForgotPasswordTest() {

        User user = new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, "pass12", null, null, null, "a.png", null, null, false, null, null, null);
        System.out.println("user Data get password " + user.getPassword());
        user.setPassword("password23");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("after update user password " + user.getPassword());
    }

    @Test
    @DisplayName("find By Email Test ")
    public void getFindByEmail() {

        User user = new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, null, null, null, null, null, null, null, false, null, null, null);
        Mockito.doReturn(user).when(repository).findByEmail(user.getEmail());
        System.out.println("check find by email id " + user.getEmail());
        service.findByEmail(user.getEmail());
        assertEquals(user.getEmail(), service.findByEmail(user.getEmail()).getEmail());
        System.out.println("after check find by email id " + service.findByEmail(user.getEmail()).getEmail());
    }


    @Test
    @DisplayName("Get Users Test ")
    public void getUsersTest() {
        when(repository.getUsers()).thenReturn(Stream

                .of(new User(100, "Raj",null,null, "raj@gmail.com", "98989898898", null, null, null, null, null, null, null, null, false, null, null, null),
                        new User(101, "Rajesh",null,null, "rajesh@gmail.com", "767676767", null, null, null, null, null, null, null, null, false, null, null, null)).collect(Collectors.toList()));
        assertEquals(2, service.getUsers().size());
    }

    @Test
    @DisplayName("Get Admin Test ")
    public void getAdminTest() {

        when(repository.getAdmin()).thenReturn(new User(100, "Raj",null,null, "raj@gmail.com", "98989898898",

                null, null, null, null, null, null,
                null, null, false, null, null, null));
        assertEquals("raj@gmail.com", service.getAdmin().getEmail());
    }
}



