package edu.miu.cs590.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthZ {

    @GetMapping("/")
    ResponseEntity<Object> healthz1() {
        Map<String, String> object = new HashMap<>();
        object.put("Status", "OK");
        return ResponseEntity.ok(object);
    }

    @GetMapping("/health")
    ResponseEntity<Object> healthz2() {
        return healthz1();
    }

}
