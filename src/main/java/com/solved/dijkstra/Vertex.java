package com.solved.dijkstra;

class Vertex {
    public String name; // ÐœÐµÑ‚ÐºÐ° (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€, 'A')
    public boolean isInTree;
    private boolean visited;
    public Vertex(String label) // ÐšÐ¾Ð½Ñ�Ñ‚Ñ€ÑƒÐºÑ‚Ð¾Ñ€
    {
        this.name = label;
        isInTree = false;
    }
    public boolean isVisited() {
        return visited;
    }
 
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
}
