package com.example.challenge.service;

import com.example.challenge.model.Station;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StationService {
    private final Map<Long, Station> stations = new HashMap<>();

    public void addStation(long stationId, String name){
        stations.put(stationId, new Station(stationId, name));
    }
}
