package org.khasanof.authentication.service;

import org.khasanof.authentication.config.encryption.PasswordEncoderConfigurer;
import org.khasanof.authentication.dto.UserCreateDTO;
import org.khasanof.authentication.model.UserEntity;
import org.khasanof.authentication.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }

    public void register(UserCreateDTO dto) {
        UserEntity userEntity = new UserEntity();
        dto.setPassword(new PasswordEncoderConfigurer().encoder().encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, userEntity);
        userRepo.save(userEntity);
    }
}
