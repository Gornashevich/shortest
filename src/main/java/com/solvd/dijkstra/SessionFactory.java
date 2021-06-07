package com.solvd.dijkstra;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class SessionFactory {

    private final static SessionFactory factory = new SessionFactory();
    private final static String config = "config.xml";
    private static SqlSessionFactory sqlSessionFactory;


    private SessionFactory() {
        try (InputStream inputStream = Resources.getResourceAsStream(config)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            log.error("Incorrect input", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
