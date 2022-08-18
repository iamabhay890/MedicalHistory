package com.MedicalHistory;
import com.MedicalHistory.services.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.repositories.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceByDBTest {


    /*@Autowired
    private UserRepo userRepo;*/
    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;
    @BeforeEach
    void setUp(){
        this.userService = new UserServiceImpl(userRepo);
    }

    @Test
    @DisplayName("Test")
    public void testAdd() {
        String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }


    @Test
    @Order(1)
    @Rollback
    @Transactional
    @DisplayName("Create User Test ")
    public void createUserTest(){
        User user = User.builder()
                    .id(20)
                    .name("Om singh")
                    .age(24)
                    .gender("Male")
                    .phone("9999999999")
                    .address("Delhi")
                    .email("om122@gmail.com")
                    .password("om1233")
                    .build();
        userRepo.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Get User Test ")
    public void getUserTest(){

        User user = userRepo.findById(1).get();
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("UserById Test ")
    public void getUserById(){
        User user = userRepo.getById(1);
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Order(4)
    @Rollback
    @Transactional
    @DisplayName("Update User Test")
    public void updateUserTest(){

        User user = userRepo.findById(1).get();
        user.setEmail("abc@gmail.com");
        User userUpdated =  userRepo.save(user);
        Assertions.assertThat(userUpdated.getEmail()).isEqualTo("abc@gmail.com");
    }

    @Test
    @Order(5)
    @Rollback
    @Transactional
    @DisplayName("Delete User Test ")
    public void deleteUserTest(){

       User user = userRepo.findById(1).get();
        userRepo.delete(user);
        //userRepo.deleteById(1);
        User user1 = null;
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByEmail("om122@gmail.com"));
        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        }
        Assertions.assertThat(user1).isNull();
    }

    @Test
    @Order(6)
    @DisplayName("Get All Users Test ")
    public void getAllUsers(){

        List<User> user = userRepo.findAll();
        Assertions.assertThat(user.size()).isGreaterThan(0);
    }


    @Test
    @Order(7)
    @DisplayName("Update Pic Test")
    public void updatePic(){

    }

    @Test
    @Order(7)
    @Rollback
    @DisplayName("Update Password Test")
    public void updatePasswordTests(){

        User user = userRepo.findById(7).get();
        user.setPassword("Abcd@12345");
        User userUpdated =  userRepo.save(user);
        Assertions.assertThat(userUpdated.getPassword()).isEqualTo("Abcd@12345");
    }

    @Test
    @Order(8)
    @Rollback
    @DisplayName("Update Password Test")
    public void forgotPasswordTests(){

        User user = userRepo.findById(7).get();
        user.setPassword("Abcd@12345");
        User userUpdated =  userRepo.save(user);
        Assertions.assertThat(userUpdated.getPassword()).isEqualTo("Abcd@12345");
    }

    @Test
    @Order(9)
    @DisplayName("find By Email Test ")
    public void findByEmail(){
        User user = userRepo.findByEmail("om@gmail.com");
        Assertions.assertThat(user.getEmail()).isEqualTo("om@gmail.com");
    }

    @Test
    @Order(10)
    @DisplayName("Get Admin Test ")
    public void getAdmin(){
        User user = userRepo.findById(-1).get();
        Assertions.assertThat(user.getEmail()).isEqualTo("admin");
    }
}
