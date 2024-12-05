package com.example.challenge.controller;

import com.example.challenge.service.PathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/paths")
public class PathController {
    private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @PutMapping("/{pathId}")
    public ResponseEntity<?> addPath(@PathVariable long pathId, @RequestBody Map<String, Long> body) {
        long cost = body.get("cost");
        long sourceId = body.get("source_id");
        long destinationId = body.get("destination_id");
        pathService.addPath(pathId,cost,sourceId,destinationId);
        return ResponseEntity.ok(Map.of("status","ok"));
    }

    @GetMapping("/{sourceId}/{destinationId}")
    public ResponseEntity<?> getShortestPath(@PathVariable long sourceId, @PathVariable long destinationId) {
        try {
            Map<String, Object> result = pathService.getCheapestPath(sourceId, destinationId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
}
