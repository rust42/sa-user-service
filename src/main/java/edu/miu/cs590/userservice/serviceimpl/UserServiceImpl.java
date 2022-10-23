package edu.miu.cs590.userservice.serviceimpl;

import edu.miu.cs590.userservice.dto.LoginCredentialDto;
import edu.miu.cs590.userservice.dto.TokenDto;
import edu.miu.cs590.userservice.dto.UserDto;
import edu.miu.cs590.userservice.dto.UserSaveDto;
import edu.miu.cs590.userservice.entity.User;
import edu.miu.cs590.userservice.exception.UserAlreadyExistException;
import edu.miu.cs590.userservice.exception.UserNotFoundException;
import edu.miu.cs590.userservice.mapper.UserSaveMapper;
import edu.miu.cs590.userservice.repository.UserRepository;
import edu.miu.cs590.userservice.service.UserService;
import edu.miu.cs590.userservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserSaveMapper userSaveMapper;

    @Autowired
    private UserRepository userRepository;


    public TokenDto authenticate(LoginCredentialDto credentials) {
        Optional<User> optionalUser = userRepository.findByEmail(credentials.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No such user exists!");
        }

        User user = optionalUser.get();

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                        credentials.getPassword()));

        return TokenDto.builder()
                .token(jwtTokenUtil.generateAccessToken(authenticate))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }


    public UserServiceImpl(UserSaveMapper userSaveMapper,UserRepository userRepository) {
        this.userSaveMapper=userSaveMapper;
        this.userRepository=userRepository;
    }

    @Override
    public UserDto saveUser(UserSaveDto userSaveDto) {
        userRepository.findByEmail(userSaveDto.getEmail()).ifPresent(user -> {throw new UserAlreadyExistException("Email "+user.getEmail()+ " already exist!");});
        return userSaveMapper.toDto(userRepository.save(userSaveMapper.toEntity(userSaveDto)));
    }

    @Override
    public Boolean checkUser(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
