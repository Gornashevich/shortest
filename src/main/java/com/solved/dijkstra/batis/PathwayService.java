package com.solved.dijkstra.batis;

import java.util.List;

public class PathwayService {
	IPathway bat = DaoFactory.getPathway();

	public List<Pathway> getAll() {
		List<Pathway> list = bat.getAll();
		return list;

	}
}
