package com.iseng.web.controller;

import com.iseng.web.dto.ParticipantDto;
import com.iseng.web.models.Peserta;
import com.iseng.web.service.ServicePartisipan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired
    private ServicePartisipan participantService;

    // Endpoint untuk mendaftar peserta
    @PostMapping("/register")
    public ResponseEntity<String> registerParticipant(@RequestBody ParticipantDto participantDto) {
        participantService.saveParticipant(participantDto);
        return ResponseEntity.ok("Registration successful!");
    }

    @PostMapping("/check-nim")
    public ResponseEntity<Map<String, String>> checkNim(@RequestBody Map<String, String> request) {
        String nim = request.get("nim");
        boolean isNimExist = participantService.isNimExist(nim); // Cek apakah NIM sudah ada
        Map<String, String> response = new HashMap<>();

        if (isNimExist) {
            response.put("status", "error");
            response.put("message", "NIM sudah terdaftar.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            response.put("status", "success");
            response.put("message", "NIM tersedia.");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/validate-nim")
    public ResponseEntity<Map<String, String>> validateNim(@RequestBody Map<String, String> request) {
        String nim = request.get("nim");  // 'nim' is already a String from the request
        boolean isValid = participantService.isNimValid(nim);  // Use the service to check the validity of NIM
        System.out.println("Received NIM: " + nim);

        Map<String, String> response = new HashMap<>();
        if (isValid) {
            response.put("status", "success");
            response.put("message", "NIM is valid.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "NIM not found.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    // Endpoint untuk mendapatkan nama berdasarkan NIM
    @GetMapping("/get-user-name/{nim}")
    public ResponseEntity<Map<String, String>> getUserName(@PathVariable String nim) {
        String name = participantService.getUserNameByNim(nim);  // Ambil nama dari service

        if (name != null) {
            Map<String, String> response = new HashMap<>();
            response.put("name", name);  // Kirim nama dalam response
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }
    }




}