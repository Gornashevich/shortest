package com.solvd.dijkstra;

class Vertex {
    public String name; // Метка (например, 'A')
    public boolean isInTree;

    public Vertex(String label) // Конструктор
    {
        this.name = label;
        isInTree = false;
    }
}
