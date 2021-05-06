package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.AuthDto;
import com.vaccin.vaccin.repository.UserRepository;
import com.vaccin.vaccin.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AuthDto> optionalAuthDto = userRepository.getByEmailWithPasswordAndRole(email);
//        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

        if (optionalAuthDto.isPresent()) {
            return UserPrinciple.build(optionalAuthDto.get());
        } throw new UsernameNotFoundException("No User found with email: " + email);

    }
}
