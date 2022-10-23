package edu.miu.cs590.userservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public static PasswordEncoder passwordEncoder(){
        return passwordEncoder;
    }
}
