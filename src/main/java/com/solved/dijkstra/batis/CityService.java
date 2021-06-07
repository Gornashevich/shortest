package com.solved.dijkstra.batis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CityService {
	private final static Logger LOGGER = LogManager.getLogger(CityService.class);

	ICity bat = DaoFactory.getCity();

	public String[] getAll() {

		String[] list = bat.getAll();
		LOGGER.info(list);
		return list;

	}
}
