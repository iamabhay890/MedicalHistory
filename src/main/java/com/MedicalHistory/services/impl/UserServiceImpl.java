package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Role;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto, MultipartFile file) {

        LocalDateTime createdDate = LocalDateTime.now();
        logger.info("Setting created date and modified date");
        User user = this.dtoToUser(userDto);

        try {
            user.setProfilePic(Base64.getEncoder().encodeToString(file.getBytes()));
            user.setProfilePictureName(file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("Error is setting profile pic");
            e.printStackTrace();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedDate(createdDate);
        user.setModifiedDate(createdDate);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }


    @Override
    public void createOAuth2User(String email, String fullName, String OauthEmail) {

        if (userRepo.existsByEmail(email)) {
            logger.info("Already registered from Auth2 and already logged in user");
        } else {
            logger.info("Unique user and running else part of create user from Auth2");
            LocalDateTime createdDate = LocalDateTime.now();
            logger.info("Setting created date and modified date");
            User user = new User();
            user.setName(fullName);
            user.setEmail(email);
            user.setRoles(Arrays.asList(new Role("ROLE_USER")));
            user.setStatus(false);
            user.setModifiedDate(createdDate);
            user.setCreatedDate(createdDate);
            user.setPassword(bCryptPasswordEncoder.encode(OauthEmail));
            this.userRepo.save(user);

        }
    }

    @Override
    public void updateProfilePicture(MultipartFile file, Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        try {
            user.setProfilePic(Base64.getEncoder().encodeToString(file.getBytes()));
            user.setProfilePictureName(file.getOriginalFilename());
            userRepo.save(user);
            logger.info("profile Picture updated");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Something error has occurred during profile picture update");
        }
    }

    @Override
    public UserDto update(UserDto userDto, Integer userId) {

        LocalDateTime modifiedDate = LocalDateTime.now();

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setAge(userDto.getAge());
        user.setModifiedDate(modifiedDate);
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).get(); //.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setAdharNo(userDto.getAdharNo());
        user.setGender(userDto.getGender());
        user.setProfilePic(userDto.getProfilePic());
        user.setAge(userDto.getAge());
        user.setStatus(userDto.isStatus());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setModifiedDate(userDto.getModifiedDate());


        return user;
    }

    public UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setAdharNo(user.getAdharNo());
        userDto.setGender(user.getGender());
        userDto.setProfilePic(user.getProfilePic());
        userDto.setAge(user.getAge());
        userDto.setStatus(user.isStatus());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setModifiedDate(user.getModifiedDate());

        return userDto;
    }

    @Override
    public UserDto updatePassword(UserDto userDto) {

        User user = this.userRepo.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userDto.getId()));
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
        System.out.println("User password get by DB " + userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;

    }

    @Override
    public UserDto forgotPass(UserDto userDto) {
        User user = this.userRepo.findByEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Running loadUserByUsername method");
        User user = userRepo.findByEmail(username);

        if (user.isStatus()) {

            throw new UsernameNotFoundException("Inactivated User");
        } else {
            if (user == null) {

                throw new UsernameNotFoundException("Invalid username or password");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepo.getUsers();
        return users;
        //return users;
    }

    @Override
    public User getAdmin() {
        return this.userRepo.getAdmin();
    }
}
