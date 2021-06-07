package com.solvd.dijkstra.models;

import lombok.Data;

@Data
public class Cities {

    private int id;
    private String name;

    public Cities(int id,String name) {
        this.id = id;
        this.name = name;
    }
}
