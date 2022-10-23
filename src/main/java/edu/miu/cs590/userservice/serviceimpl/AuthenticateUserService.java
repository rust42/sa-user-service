package edu.miu.cs590.userservice.serviceimpl;

import edu.miu.cs590.userservice.dto.AuthenticateUser;
import edu.miu.cs590.userservice.entity.User;
import edu.miu.cs590.userservice.exception.UserNotFoundException;
import edu.miu.cs590.userservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticateUserService implements UserDetailsService {

    private UserRepository userRepository;

    public AuthenticateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("User with email "+username+ " not exist!!"));
        return AuthenticateUser.builder().username(user.getEmail()).password(user.getPassword()).grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()))).build();
    }
}
