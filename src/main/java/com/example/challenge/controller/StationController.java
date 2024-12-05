package com.example.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.challenge.service.StationService;

import java.util.Map;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PutMapping("/{stationId}")
    public ResponseEntity<?> addStation(@PathVariable long stationId, @RequestBody Map<String, String> body){
        String name = body.get("name");
        stationService.addStation(stationId, name);
        return ResponseEntity.ok(Map.of("status","ok"));
    }
}
