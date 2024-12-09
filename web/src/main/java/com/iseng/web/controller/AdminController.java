package com.iseng.web.controller;

import com.iseng.web.service.ServicePertanyaan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ServicePertanyaan questionService;
    private static final String ADMIN_ACCESS_CODE = "5555";

    @PostMapping("/validate")
    public ResponseEntity<String> validateAccessCode(@RequestBody String accessCode) {
        if (ADMIN_ACCESS_CODE.equals(accessCode)) {
            return ResponseEntity.ok("Access granted!");
        }
        return ResponseEntity.status(403).body("Invalid access code");
    }
}
