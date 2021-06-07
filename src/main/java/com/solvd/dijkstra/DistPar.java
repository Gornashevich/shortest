package com.solvd.dijkstra;

class DistPar // Расстояние и родительская вершина
{ // Объекты сохраняются в массиве sPath
    public int parentVert; // Текущий родитель вершины
    public int distance; // Расстояние от начальной вершины до текущей

    public DistPar(int parentVert, int distance) // Конструктор
    {
        this.parentVert = parentVert;
        this.distance = distance;
    }
}
