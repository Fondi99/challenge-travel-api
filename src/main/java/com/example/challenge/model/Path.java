package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {
    public long id;
    public long sourceId;
    public long destinationId;
    public double cost;
}
