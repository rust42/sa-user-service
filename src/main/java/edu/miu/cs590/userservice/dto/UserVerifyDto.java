package edu.miu.cs590.userservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserVerifyDto {
    @NotBlank
    private String email;
}
