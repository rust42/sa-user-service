package edu.miu.cs590.userservice.service;

import edu.miu.cs590.userservice.dto.LoginCredentialDto;
import edu.miu.cs590.userservice.dto.TokenDto;
import edu.miu.cs590.userservice.dto.UserDto;
import edu.miu.cs590.userservice.dto.UserSaveDto;

public interface UserService {

    UserDto saveUser(UserSaveDto userSaveDto);
    Boolean checkUser(String email);

    TokenDto authenticate(LoginCredentialDto credentials);
}
