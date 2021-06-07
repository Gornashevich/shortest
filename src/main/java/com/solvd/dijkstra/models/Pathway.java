package com.solvd.dijkstra.models;

import lombok.Data;

@Data
public class Pathway {

    private int id;
    private String start;
    private String end;
    private int distance;

    public Pathway(String start, String end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

}
