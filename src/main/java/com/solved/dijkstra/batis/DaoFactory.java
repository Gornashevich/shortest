package com.solved.dijkstra.batis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoFactory {
	private final static Logger LOGGER = LogManager.getLogger(DaoFactory.class);

	private DaoFactory() {

	}

	public static ICity getCity() {

		ICity countMap = null;
		SqlSessionFactory factory = null;

		try {

			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
			countMap = factory.openSession(true).getMapper(ICity.class);

		} catch (IOException e) {
			LOGGER.error("Error: " + e);
		} /*
			 * finally { ((PooledDataSource)
			 * factory.getConfiguration().getEnvironment().getDataSource()).forceCloseAll();
			 * }
			 */
		return countMap;

	}
}
