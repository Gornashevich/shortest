public class DistanceParent {

    public int distance; // Расстояние от начальной вершины до текущей
    public int parentVert; // Текущий родитель вершины
    public DistanceParent(int pv, int d) // Конструктор
    {
        distance = d;
        parentVert = pv;
    }
}
