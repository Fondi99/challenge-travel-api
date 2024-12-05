package com.example.challenge.service;

import com.example.challenge.model.Path;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PathService {
    private final Map<Long, Path> paths = new HashMap<>();
    private final Map<Long, List<Path>> adjacencyList = new HashMap<>();

    public void addPath(long pathId, long cost, long sourceId, long destinationId) {
        Path path = new Path(pathId, sourceId, destinationId, cost);
        paths.put(pathId, path);

        adjacencyList.putIfAbsent(sourceId, new ArrayList<>());
        adjacencyList.putIfAbsent(destinationId, new ArrayList<>());
        adjacencyList.get(sourceId).add(path);
        adjacencyList.get(destinationId).add(new Path(pathId, destinationId, sourceId, cost));
    }

    public Map<String, Object> getCheapestPath(long sourceId, long destinationId) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        pq.add(new double[]{sourceId, 0.0});

        Map<Long, Double> minCost = new HashMap<>();
        minCost.put(sourceId, 0.0);

        Map<Long, Long> previous = new HashMap<>();

        while (!pq.isEmpty()) {
            double[] current = pq.poll();
            long currentStation = (long) current[0];
            double currentCost = current[1];

            if (currentStation == destinationId) {
                break;
            }

            for (Path path : adjacencyList.getOrDefault(currentStation, new ArrayList<>())) {
                long neighbor = path.getDestinationId();
                double newCost = currentCost + path.getCost();

                if (newCost < minCost.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    minCost.put(neighbor, newCost);
                    previous.put(neighbor, currentStation);
                    pq.add(new double[]{neighbor, newCost});
                }
            }
        }

        List<Long> path = new ArrayList<>();
        long current = destinationId;
        while (previous.containsKey(current)) {
            path.add(0, current);
            current = previous.get(current);
        }
        path.add(0, sourceId);

        if (!minCost.containsKey(destinationId)) {
            throw new IllegalArgumentException("No path exists between the given stations.");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("cost", minCost.get(destinationId));
        return result;
    }
}
