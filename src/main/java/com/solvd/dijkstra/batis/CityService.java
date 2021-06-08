package com.solvd.dijkstra.batis;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CityService {
	private final static Logger LOGGER = LogManager.getLogger(CityService.class);

	ICity bat = DaoFactory.getCity();

	public List<String> getAll() {

		List<City> list = bat.getAll();
		
		List<String> cities = new ArrayList<String>();
		list.stream().forEach((c) -> cities.add(c.getName()));

		LOGGER.info(cities);
		return cities;
	}
}
