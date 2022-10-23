package edu.miu.cs590.userservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int code;
    private String status;
    private String message;
    private String details;

    public ErrorResponse(HttpStatus httpStatus, String message, String description) {
        timestamp=LocalDateTime.now();
        this.code=httpStatus.value();
        this.status= httpStatus.toString();
        this.message=message;
        this.details=description;
    }
}
