package com.solvd.dijkstra.batis;

import lombok.Data;

@Data
public class Pathway {

	private Integer id;
	private String start;
	private String end;
	private Integer distance;

}
