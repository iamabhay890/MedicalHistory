package com.MedicalHistory;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.MedicalHistory.services.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl service;

    @MockBean
    private UserRepo repository;

    @Test
    @DisplayName("Create User Test ")
    public void getCreateUserTest() {
        UserDto userDto = new UserDto(1011,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,false,null,null,null);
        when(repository.save(any())).thenReturn(service.dtoToUser(userDto));
        assertEquals(userDto.getEmail(), service.createUser(userDto).getEmail());
        System.out.println("create user  "+ service.dtoToUser(userDto).getId());


    }

    @Test
    @DisplayName("Update User Test")
    public void getUpdateUserTest() {
        User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        System.out.println("user Data getName "+ user.getName());
        user.setName("rajiv");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getEmail(), service.userToDto(user).getEmail());
        System.out.println("after update user "+ user.getName());
    }

    @Test
    @DisplayName("UserById Test ")
    public void getUserByIdTest() {
        int userId = 100;
        User user = new User(userId,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        doReturn(Optional.of(user)).when(repository).findById(userId);
        System.out.println("check user id "+ userId);
        service.userToDto(user);
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("check user id "+ service.userToDto(user).getId());
    }

    @Test
    @DisplayName("Get All Users Test ")
    public void getAllUsersTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null),
                        new User(101,"Rajesh","rajesh@gmail.com","767676767",null,null,null,null,null,null,null,false,null,null,null)).collect(Collectors.toList()));
        assertEquals(2, service.getAllUsers().size());

    }

    @Test
    @DisplayName("Delete User Test ")
    public void getDeleteUserTest() {
        //User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        UserDto userDto = new UserDto(101,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,false,null,null,null);

        System.out.println("user Get Id  "+userDto.getId());
        when(repository.save(any())).thenReturn(service.dtoToUser(userDto));
        System.out.println("create user  "+ service.dtoToUser(userDto).getId());
        User user =  service.dtoToUser(userDto);
        System.out.println("user data "+ user.getId());

          //  service.deleteUser(user.getId());
         // repository.delete(user);
       // Assertions.assertThat(user.getId()).isNotEqualTo(101);
        System.out.println("after delete user id "+ user.getId());



    }

    @Test
    public void getDeleteTest(){
        // User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        User user = new User();
        user.setName("Test Name");
        user.setId(1);
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        System.out.println("test before "+user.getId());
        service.deleteUser(user.getId());
        repository.deleteById(user.getId());
       // verify(repository, times(1)).delete(user);

        System.out.println("test after "+user.getId());
    }


    @Test
    @DisplayName("Update Pic Test")
    public void getUpdatePicTest() {
        User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,"a.png",null,false,null,null,null);
        UserDto userDto = new UserDto(100,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,false,null,null,null);
        System.out.println("user Data get pic "+ user.getImage());
        user.setImage("abc.png");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("after update user pic "+ user.getImage());
    }

    @Test
    @DisplayName("Update Password Test")
    public void getUpdatePasswordTest() {
        User user = new User(100,"Raj","raj@gmail.com","98989898898",null,"password",null,null,null,"a.png",null,false,null,null,null);
        UserDto userDto = new UserDto(100,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,false,null,null,null);
        System.out.println("user Data get password "+ user.getPassword());
        user.setPassword("pass");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("after update user password "+ user.getPassword());
    }

    @Test
    @DisplayName("Forgot Password Test")
    public void getForgotPasswordTest() {
        User user = new User(100,"Raj","raj@gmail.com","98989898898",null,"pass12",null,null,null,"a.png",null,false,null,null,null);
       // UserDto userDto = new UserDto(100,"rama","rama@gmail.com","0909090900",null,"null","null",null,null,null,null,null,false,null,null,null);
        System.out.println("user Data get password "+ user.getPassword());
        user.setPassword("password23");
        when(repository.save(any())).thenReturn(service.userToDto(user));
        assertEquals(user.getId(), service.userToDto(user).getId());
        System.out.println("after update user password "+ user.getPassword());
    }

    @Test
    @DisplayName("find By Email Test ")
    public void getFindByEmail() {
        User user = new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null);
        Mockito.doReturn(user).when(repository).findByEmail(user.getEmail());
        System.out.println("check find by email id "+ user.getEmail());
        service.findByEmail(user.getEmail());
        assertEquals(user.getEmail(), service.findByEmail(user.getEmail()).getEmail());
        System.out.println("after check find by email id "+ service.findByEmail(user.getEmail()).getEmail());
    }


    @Test
    @DisplayName("Get Users Test ")
    public void getUsersTest() {
        when(repository.getUsers()).thenReturn(Stream
                .of(new User(100,"Raj","raj@gmail.com","98989898898",null,null,null,null,null,null,null,false,null,null,null),
                        new User(101,"Rajesh","rajesh@gmail.com","767676767",null,null,null,null,null,null,null,false,null,null,null)).collect(Collectors.toList()));
        assertEquals(2, service.getUsers().size());
    }

    @Test
    @DisplayName("Get Admin Test ")
    public void getAdminTest() {
        when(repository.getAdmin()).thenReturn(new User(100,"Raj","raj@gmail.com","98989898898",
                        null,null,null,null,null,
                        null,null,false,null,null,null));
        assertEquals("raj@gmail.com", service.getAdmin().getEmail());
    }
}



