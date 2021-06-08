package com.solvd.dijkstra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.dijkstra.batis.CityService;
import com.solvd.dijkstra.batis.Pathway;
import com.solvd.dijkstra.batis.PathwayService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class PathApp {

	public final static Logger LOGGER = LogManager.getLogger(PathApp.class);

	public static void main(String[] args) {
		Graph theGraph = new Graph();

		CityService cityS = new CityService();
		PathwayService pathS = new PathwayService();

		List<String> list = cityS.getAll();
		List<Pathway> listW = pathS.getAll();

		list.stream().forEach((c) -> theGraph.addVertex(c));
		listW.stream().forEach((p) -> theGraph.addEdge(Integer.parseInt(p.getStart()), Integer.parseInt(p.getEnd()), p.getDistance()));

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			LOGGER.info("Choose the start point name:");
			String startName = reader.readLine();
			LOGGER.info("Choose the end point name:");
			String finishName = reader.readLine();
			int[] points = WorkWithPoints.selector(startName, finishName, theGraph.getVertexList());
			theGraph.path(points[0]);
			theGraph.displayPaths(points[0], points[1]);

		} catch (IOException ex) {
			LOGGER.error("Input/Output exception", ex);
		}
	}
}
