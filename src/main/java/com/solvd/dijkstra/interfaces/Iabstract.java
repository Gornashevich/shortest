package com.solvd.dijkstra.interfaces;

import java.util.List;

public interface Iabstract<T>{

    void update(T entity);
    void delete(int id);
    void add(T entity);
    List <T> getAll();
}
