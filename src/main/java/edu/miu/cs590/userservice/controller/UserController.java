package edu.miu.cs590.userservice.controller;

import edu.miu.cs590.userservice.dto.LoginCredentialDto;
import edu.miu.cs590.userservice.dto.UserDto;
import edu.miu.cs590.userservice.dto.UserSaveDto;
import edu.miu.cs590.userservice.dto.UserVerifyDto;
import edu.miu.cs590.userservice.exception.InvalidCredentialException;
import edu.miu.cs590.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;
    private UserDetailsService userDetailsService;

    public UserController(UserService userService,UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService=userDetailsService;
    }

    @PostMapping("/signup")
    UserDto saveUser(@RequestBody @Valid UserSaveDto userSaveDto){
        return userService.saveUser(userSaveDto);
    }

    @PostMapping("/login")
    ResponseEntity<? extends Object> login(@RequestBody @Valid LoginCredentialDto credentialDto){
        try {
            return ResponseEntity.ok(userService.authenticate(credentialDto));
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialException("Provided Credential in invalid!");
        }
    }

    @PostMapping("/verify")
    Boolean verifyUserDetailsByUsername(@RequestBody @Valid UserVerifyDto userVerifyDto){
        UserDetails userDetails = userDetailsService.loadUserByUsername(userVerifyDto.getEmail());
        return userDetails!=null
                && userDetails.isAccountNonExpired()
                && userDetails.isAccountNonLocked()
                && userDetails.isEnabled()
                && userDetails.isCredentialsNonExpired();
    }
}
