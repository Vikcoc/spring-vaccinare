package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.dto.UserCreateDto;
import com.vaccin.vaccin.dto.UserDto;
import com.vaccin.vaccin.exception.BadRequestException;
import com.vaccin.vaccin.exception.ErrorMessages;
import com.vaccin.vaccin.exception.NotFoundException;
import com.vaccin.vaccin.model.Role;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.repository.RoleRepository;
import com.vaccin.vaccin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto createUser(UserCreateDto userCreateDto) throws BadRequestException {

        Optional<AuthDto> userOptional = userRepository.getByEmailWithPasswordAndRole(userCreateDto.getEmail());

        if (userOptional.isPresent()) {
            throw new BadRequestException(ErrorMessages.emailInUse);
        }

        Optional<User> userOptional1 = userRepository.getByCnp(userCreateDto.getCnp());
        if (userOptional1.isPresent()) {
            throw new BadRequestException(ErrorMessages.personalDataInUse);
        }
        User user;
        try {
            user = new User(userCreateDto);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorMessages.dateFormat);
        }



        user.setPassword(BCrypt.hashpw(userCreateDto.getPassword(), BCrypt.gensalt()));

        Optional<Role> userRoleOptional = roleRepository.findByRole("ROLE_USER");
        userRoleOptional.ifPresent(user::setRole);

        user.setAppointed(false);

        return new UserDto(userRepository.save(user));

    }

    public UserDto getUser(Long userId) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(ErrorMessages.userNotFound);
        }

        return new UserDto(userOptional.get());
    }

    public UserDto updateUser(Long userId, UserCreateDto userCreateDto) throws BadRequestException {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.userNotFound);
        }

        User user = userOptional.get();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        try {
            user.setBirthDate(Date.valueOf(userCreateDto.getBirthDate()));
        } catch (IllegalArgumentException exception) {
            throw new BadRequestException(ErrorMessages.incorrectBirthDate);
        }
        user.setAddress(userCreateDto.getAddress());
        user.setCnp(userCreateDto.getCnp());

        return new UserDto(userRepository.save(user));
    }

    public List<UserDto> getDoctors(){
        var users = userRepository.getDoctors();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto promoteToDoctor(long userId) throws BadRequestException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.userNotFound);
        }
        User user = userOptional.get();

        if (!user.getRole().getRole().equals("ROLE_USER")) {
            throw new BadRequestException(ErrorMessages.isDoctor);
        }

        Role doctorRole = roleRepository.findByRole("ROLE_DOCTOR").get();
        user.setRole(doctorRole);
        user.setAppointed(null);
        userRepository.save(user);

        return new UserDto(user);
    }
}
