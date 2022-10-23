package edu.miu.cs590.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String email;
    private String firstName;
    private String lastName;
    private String token;
}
