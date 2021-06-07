package com.solvd.dijkstra;

public class WorkWithPoints {

    public static int[] selector(String startName, String finishName, Vertex vertexList[]) {
        int[] result = new int[2];
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i] == null) {
                break;
            } else {
                if (startName.equals(vertexList[i].name)) {
                    result[0] = i;
                } else if (finishName.equals(vertexList[i].name)) {
                    result[1] = i;
                }
            }
        }
        return result;
    }
}