package com.solvd.dijkstra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Graph {

    public final static Logger LOGGER = LogManager.getLogger(Graph.class);

    private final int MAX_VERTS = 6;
    private final int INFINITY = 1_000_000;
    private Vertex vertexList[]; // Список вершин
    private int adjMat[][]; // Матрица смежности
    private int nVerts; // Текущее количество вершин
    private int nTree; // Количество вершин в дереве
    private DistPar sPath[]; // Массив данных кратчайших путей
    private int currentVert; // Текущая вершина
    private int startToCurrent; // Расстояние до currentVert

    public Graph() // Конструктор
    {
        vertexList = new Vertex[MAX_VERTS]; // 20
        // Матрица смежности
        adjMat = new int[MAX_VERTS][MAX_VERTS]; // 20 x 20
        nVerts = 0; // Текущее количество вершин
        nTree = 0; // Количество вершин в дереве
        for (int j = 0; j < MAX_VERTS; j++) // Матрица смежности
            for (int k = 0; k < MAX_VERTS; k++) // заполняется
                adjMat[j][k] = INFINITY; // бесконечными расстояниями
        sPath = new DistPar[MAX_VERTS]; // Массив данных кратчайших путей 20
    }

    public void addVertex(String name) {
        vertexList[nVerts++] = new Vertex(name);
    }

    public void addEdge(int start, int end, int weight) {
        // добавление графов
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    public void path(int startPoint) // Выбор кратчайших путей
    {
        int startTree = startPoint; // Начиная с вершины 0
        vertexList[startTree].isInTree = true;
        nTree = 1; // Включение в дерево
        // Перемещение строки расстояний из adjMat в sPath
        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }
        // Пока все вершины не окажутся в дереве
        while (nTree < nVerts) {
            int indexMin = getMin(); // Получение индекса элемента с мин значением из sPath
            int minDist = sPath[indexMin].distance;
            if (minDist == INFINITY) // Если все расстояния бесконечны
            { // или уже находятся в дереве,
                LOGGER.info("There are unreachable vertices");
                break; // построение sPath завершено
            } else { // Возврат currentVert
                currentVert = indexMin; // к ближайшей вершине
                startToCurrent = sPath[indexMin].distance;
                // Минимальное расстояние от startTree
                // до currentVert равно startToCurrent
            }
            // Включение текущей вершины в дерево
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath(); // Обновление массива sPath[]
        }
        nTree = 0; // Очистка дерева
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }

    public int getMin() // Поиск в sPath элемента
    { // с наименьшим расстоянием
        int minDist = INFINITY; // Исходный высокий "минимум"
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) // Для каждой вершины
        { // Если она не включена в дерево
            if (!vertexList[j].isInTree && // и ее расстояние меньше
                    sPath[j].distance < minDist) // старого минимума
            {
                minDist = sPath[j].distance;
                indexMin = j; // Обновление минимума
            }
        }
        return indexMin; // Метод возвращает индекс
    } // элемента с наименьшим расстоянием

    public void adjust_sPath() {
        // Обновление данных в массиве кратчайших путей sPath
        int column = 1; // Начальная вершина пропускается
        while (column < nVerts) // Перебор столбцов
        {
            // Если вершина column уже включена в дерево, она пропускается
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            // Вычисление расстояния для одного элемента sPath
            // Получение ребра от currentVert к column
            int currentToFringe = adjMat[currentVert][column];
            // Суммирование расстояний
            int startToFringe = startToCurrent + currentToFringe;
            // Определение расстояния текущего элемента sPath
            int sPathDist = sPath[column].distance;
            // Сравнение расстояния от начальной вершины с элементом sPath
            if (startToFringe < sPathDist) // Если меньше,
            { // данные sPath обновляются
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths(int start, int finish) {

        LOGGER.info("From point " + vertexList[start].name +
                " to point " + vertexList[finish].name +
                " the distance is " + sPath[finish].distance);
    }

    public Vertex[] getVertexList() {
        return vertexList;
    }
}