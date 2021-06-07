package com.solvd.dijkstra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PathApp {

    public final static Logger LOGGER = LogManager.getLogger(PathApp.class);

    public static void main(String[] args) {
        Graph theGraph = new Graph();

        theGraph.addVertex("A"); // 0
        theGraph.addVertex("B"); // 1
        theGraph.addVertex("C"); // 2
        theGraph.addVertex("D"); // 3
        theGraph.addVertex("E"); // 4
        theGraph.addVertex("F"); // 5
        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 3, 80); // AD 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 4, 40); // CE 40
        theGraph.addEdge(3, 2, 20); // DC 20
        theGraph.addEdge(3, 4, 70); // DE 70
        theGraph.addEdge(1, 4, 50); // BE 50
        theGraph.addEdge(5, 4, 10); // FE 10

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            LOGGER.info("Choose the start point name:");
            String startName = reader.readLine();
            LOGGER.info("Choose the end point name:");
            String finishName = reader.readLine();
            int[] points = WorkWithPoints.selector(startName, finishName, theGraph.getVertexList());
            theGraph.path(points[0]); // Кратчайшие пути
            theGraph.displayPaths(points[0], points[1]);

        } catch (IOException ex) {
            LOGGER.error("Input/Output exception", ex);
        }
    }
}
