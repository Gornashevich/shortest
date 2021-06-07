package com.solvd.dijkstra.interfaces;

import com.solvd.dijkstra.models.Cities;

public interface ICity extends Iabstract<Cities>{

    Cities getById(int id);

}
