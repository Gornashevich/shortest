package com.solved.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Graph {

	public final static Logger LOGGER = LogManager.getLogger(Graph.class);

	private final int MAX_VERTS = 7;
	private final int INFINITY = 1_000_000;
	private Vertex vertexList[]; // Ð¡Ð¿Ð¸Ñ�Ð¾Ðº Ð²ÐµÑ€ÑˆÐ¸Ð½
	private int adjMat[][]; // ÐœÐ°Ñ‚Ñ€Ð¸Ñ†Ð° Ñ�Ð¼ÐµÐ¶Ð½Ð¾Ñ�Ñ‚Ð¸
	private int nVerts; // Ð¢ÐµÐºÑƒÑ‰ÐµÐµ ÐºÐ¾Ð»Ð¸Ñ‡ÐµÑ�Ñ‚Ð²Ð¾ Ð²ÐµÑ€ÑˆÐ¸Ð½
	private int nTree; // ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑ�Ñ‚Ð²Ð¾ Ð²ÐµÑ€ÑˆÐ¸Ð½ Ð² Ð´ÐµÑ€ÐµÐ²Ðµ
	private DistPar sPath[]; // ÐœÐ°Ñ�Ñ�Ð¸Ð² Ð´Ð°Ð½Ð½Ñ‹Ñ… ÐºÑ€Ð°Ñ‚Ñ‡Ð°Ð¹ÑˆÐ¸Ñ… Ð¿ÑƒÑ‚ÐµÐ¹
	private int currentVert; // Ð¢ÐµÐºÑƒÑ‰Ð°Ñ� Ð²ÐµÑ€ÑˆÐ¸Ð½Ð°
	private int startToCurrent; // Ð Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ðµ Ð´Ð¾ currentVert
	public ArrayList<Integer> arr;

	public Graph() // ÐšÐ¾Ð½Ñ�Ñ‚Ñ€ÑƒÐºÑ‚Ð¾Ñ€
	{
		vertexList = new Vertex[MAX_VERTS]; // 20
		// ÐœÐ°Ñ‚Ñ€Ð¸Ñ†Ð° Ñ�Ð¼ÐµÐ¶Ð½Ð¾Ñ�Ñ‚Ð¸
		adjMat = new int[MAX_VERTS][MAX_VERTS]; // 20 x 20
		nVerts = 0; // Ð¢ÐµÐºÑƒÑ‰ÐµÐµ ÐºÐ¾Ð»Ð¸Ñ‡ÐµÑ�Ñ‚Ð²Ð¾ Ð²ÐµÑ€ÑˆÐ¸Ð½
		nTree = 0; // ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑ�Ñ‚Ð²Ð¾ Ð²ÐµÑ€ÑˆÐ¸Ð½ Ð² Ð´ÐµÑ€ÐµÐ²Ðµ
		for (int j = 0; j < MAX_VERTS; j++) // ÐœÐ°Ñ‚Ñ€Ð¸Ñ†Ð° Ñ�Ð¼ÐµÐ¶Ð½Ð¾Ñ�Ñ‚Ð¸
			for (int k = 0; k < MAX_VERTS; k++) // Ð·Ð°Ð¿Ð¾Ð»Ð½Ñ�ÐµÑ‚Ñ�Ñ�
				adjMat[j][k] = INFINITY; // Ð±ÐµÑ�ÐºÐ¾Ð½ÐµÑ‡Ð½Ñ‹Ð¼Ð¸ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ñ�Ð¼Ð¸
		sPath = new DistPar[MAX_VERTS]; // ÐœÐ°Ñ�Ñ�Ð¸Ð² Ð´Ð°Ð½Ð½Ñ‹Ñ… ÐºÑ€Ð°Ñ‚Ñ‡Ð°Ð¹ÑˆÐ¸Ñ… Ð¿ÑƒÑ‚ÐµÐ¹ 20
	}

	public void addVertex(String name) {
		vertexList[nVerts++] = new Vertex(name);
	}

	public void addEdge(int start, int end, int weight) {
		// Ð´Ð¾Ð±Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ Ð³Ñ€Ð°Ñ„Ð¾Ð²
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}

	public void path(int startPoint) // Ð’Ñ‹Ð±Ð¾Ñ€ ÐºÑ€Ð°Ñ‚Ñ‡Ð°Ð¹ÑˆÐ¸Ñ… Ð¿ÑƒÑ‚ÐµÐ¹
	{
		int startTree = startPoint; // Ð�Ð°Ñ‡Ð¸Ð½Ð°Ñ� Ñ� Ð²ÐµÑ€ÑˆÐ¸Ð½Ñ‹ 0
		vertexList[startTree].isInTree = true;
		nTree = 1; // Ð’ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ Ð² Ð´ÐµÑ€ÐµÐ²Ð¾
		// ÐŸÐµÑ€ÐµÐ¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ñ�Ñ‚Ñ€Ð¾ÐºÐ¸ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ð¹ Ð¸Ð· adjMat Ð² sPath
		for (int j = 0; j < nVerts; j++) {
			int tempDist = adjMat[startTree][j];
			sPath[j] = new DistPar(startTree, tempDist);
		}
		// ÐŸÐ¾ÐºÐ° Ð²Ñ�Ðµ Ð²ÐµÑ€ÑˆÐ¸Ð½Ñ‹ Ð½Ðµ Ð¾ÐºÐ°Ð¶ÑƒÑ‚Ñ�Ñ� Ð² Ð´ÐµÑ€ÐµÐ²Ðµ
		while (nTree < nVerts) {
			int indexMin = getMin(); // ÐŸÐ¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð¸Ð½Ð´ÐµÐºÑ�Ð° Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð° Ñ� Ð¼Ð¸Ð½
										// Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸ÐµÐ¼ Ð¸Ð· sPath
			int minDist = sPath[indexMin].distance;
			if (minDist == INFINITY) // Ð•Ñ�Ð»Ð¸ Ð²Ñ�Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ñ� Ð±ÐµÑ�ÐºÐ¾Ð½ÐµÑ‡Ð½Ñ‹
			{ // Ð¸Ð»Ð¸ ÑƒÐ¶Ðµ Ð½Ð°Ñ…Ð¾Ð´Ñ�Ñ‚Ñ�Ñ� Ð² Ð´ÐµÑ€ÐµÐ²Ðµ,
				LOGGER.info("There are unreachable vertices");
				break; // Ð¿Ð¾Ñ�Ñ‚Ñ€Ð¾ÐµÐ½Ð¸Ðµ sPath Ð·Ð°Ð²ÐµÑ€ÑˆÐµÐ½Ð¾
			} else { // Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‚ currentVert
				currentVert = indexMin; // Ðº Ð±Ð»Ð¸Ð¶Ð°Ð¹ÑˆÐµÐ¹ Ð²ÐµÑ€ÑˆÐ¸Ð½Ðµ
				startToCurrent = sPath[indexMin].distance;
				// ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ðµ Ð¾Ñ‚ startTree
				// Ð´Ð¾ currentVert Ñ€Ð°Ð²Ð½Ð¾ startToCurrent
			}
			// Ð’ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ Ñ‚ÐµÐºÑƒÑ‰ÐµÐ¹ Ð²ÐµÑ€ÑˆÐ¸Ð½Ñ‹ Ð² Ð´ÐµÑ€ÐµÐ²Ð¾
			vertexList[currentVert].isInTree = true;
			nTree++;
			adjust_sPath(); // ÐžÐ±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ðµ Ð¼Ð°Ñ�Ñ�Ð¸Ð²Ð° sPath[]
		}
		nTree = 0; // ÐžÑ‡Ð¸Ñ�Ñ‚ÐºÐ° Ð´ÐµÑ€ÐµÐ²Ð°
		for (int j = 0; j < nVerts; j++)
			vertexList[j].isInTree = false;
	}

	public int getMin() // ÐŸÐ¾Ð¸Ñ�Ðº Ð² sPath Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð°
	{ // Ñ� Ð½Ð°Ð¸Ð¼ÐµÐ½ÑŒÑˆÐ¸Ð¼ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸ÐµÐ¼
		int minDist = INFINITY; // Ð˜Ñ�Ñ…Ð¾Ð´Ð½Ñ‹Ð¹ Ð²Ñ‹Ñ�Ð¾ÐºÐ¸Ð¹ "Ð¼Ð¸Ð½Ð¸Ð¼ÑƒÐ¼"
		int indexMin = 0;
		arr = new ArrayList<Integer>();
		for (int j = 0; j < nVerts; j++) // Ð”Ð»Ñ� ÐºÐ°Ð¶Ð´Ð¾Ð¹ Ð²ÐµÑ€ÑˆÐ¸Ð½Ñ‹
		{ // Ð•Ñ�Ð»Ð¸ Ð¾Ð½Ð° Ð½Ðµ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð° Ð² Ð´ÐµÑ€ÐµÐ²Ð¾
			if (!vertexList[j].isInTree && // Ð¸ ÐµÐµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ðµ Ð¼ÐµÐ½ÑŒÑˆÐµ
					sPath[j].distance < minDist) // Ñ�Ñ‚Ð°Ñ€Ð¾Ð³Ð¾ Ð¼Ð¸Ð½Ð¸Ð¼ÑƒÐ¼Ð°
			{
				minDist = sPath[j].distance;

				indexMin = j; // ÐžÐ±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ðµ Ð¼Ð¸Ð½Ð¸Ð¼ÑƒÐ¼Ð°
				arr.add(j);
			}
			
		}
		LOGGER.info(arr);
		return indexMin; // ÐœÐµÑ‚Ð¾Ð´ Ð²Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ Ð¸Ð½Ð´ÐµÐºÑ�
	} // Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð° Ñ� Ð½Ð°Ð¸Ð¼ÐµÐ½ÑŒÑˆÐ¸Ð¼ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸ÐµÐ¼

	public void adjust_sPath() {
		// ÐžÐ±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ðµ Ð´Ð°Ð½Ð½Ñ‹Ñ… Ð² Ð¼Ð°Ñ�Ñ�Ð¸Ð²Ðµ ÐºÑ€Ð°Ñ‚Ñ‡Ð°Ð¹ÑˆÐ¸Ñ…
		// Ð¿ÑƒÑ‚ÐµÐ¹ sPath
		
		// arr = new ArrayList<Integer>();

		int column = 0; // Ð�Ð°Ñ‡Ð°Ð»ÑŒÐ½Ð°Ñ� Ð²ÐµÑ€ÑˆÐ¸Ð½Ð° Ð¿Ñ€Ð¾Ð¿ÑƒÑ�ÐºÐ°ÐµÑ‚Ñ�Ñ�
		while (column < nVerts) // ÐŸÐµÑ€ÐµÐ±Ð¾Ñ€ Ñ�Ñ‚Ð¾Ð»Ð±Ñ†Ð¾Ð²
		{
			// Ð•Ñ�Ð»Ð¸ Ð²ÐµÑ€ÑˆÐ¸Ð½Ð° column ÑƒÐ¶Ðµ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð° Ð² Ð´ÐµÑ€ÐµÐ²Ð¾,
			// Ð¾Ð½Ð° Ð¿Ñ€Ð¾Ð¿ÑƒÑ�ÐºÐ°ÐµÑ‚Ñ�Ñ�
			if (vertexList[column].isInTree) {
				column++;
				continue;
			}
			// Ð’Ñ‹Ñ‡Ð¸Ñ�Ð»ÐµÐ½Ð¸Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ñ� Ð´Ð»Ñ� Ð¾Ð´Ð½Ð¾Ð³Ð¾
			// Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð° sPath
			// ÐŸÐ¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ñ€ÐµÐ±Ñ€Ð° Ð¾Ñ‚ currentVert Ðº column
			int currentToFringe = adjMat[currentVert][column];
			// Ð¡ÑƒÐ¼Ð¼Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ð¹
			int startToFringe = startToCurrent + currentToFringe;
			// ÐžÐ¿Ñ€ÐµÐ´ÐµÐ»ÐµÐ½Ð¸Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ñ� Ñ‚ÐµÐºÑƒÑ‰ÐµÐ³Ð¾ Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð°
			// sPath
			int sPathDist = sPath[column].distance;
			// Ð¡Ñ€Ð°Ð²Ð½ÐµÐ½Ð¸Ðµ Ñ€Ð°Ñ�Ñ�Ñ‚Ð¾Ñ�Ð½Ð¸Ñ� Ð¾Ñ‚ Ð½Ð°Ñ‡Ð°Ð»ÑŒÐ½Ð¾Ð¹
			// Ð²ÐµÑ€ÑˆÐ¸Ð½Ñ‹ Ñ� Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð¾Ð¼ sPath
			if (startToFringe < sPathDist) // Ð•Ñ�Ð»Ð¸ Ð¼ÐµÐ½ÑŒÑˆÐµ,
			{ // Ð´Ð°Ð½Ð½Ñ‹Ðµ sPath Ð¾Ð±Ð½Ð¾Ð²Ð»Ñ�ÑŽÑ‚Ñ�Ñ�

				sPath[column].parentVert = currentVert;
				// LOGGER.info(currentVert);
				// arr.add(currentVert);
				sPath[column].distance = startToFringe;
			}
			column++;

		}

	}

	/*public List<Vertex> getShortestPathTo(Vertex targetVertex){
        List<Vertex> path = new ArrayList<>();
 
        for(Vertex vertex=targetVertex;vertex!=null;vertex=vertex.isVisited()){
            path.add(vertex);
        }
 
        Collections.reverse(path);
        return path;
    }*/
	public void displayPaths(int start, int finish) {

		LOGGER.info("From point " + vertexList[start].name + " to point " + vertexList[finish].name
				+ " the distance is " + sPath[finish].distance);
	}

	public Vertex[] getVertexList() {
		return vertexList;
	}
}