package com.solvd.dijkstra;


import com.solvd.dijkstra.interfaces.IConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ConnectionPool implements IConstant {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool;
    private int conAmount = 0;

    private static final List<Connection> connectionList = Collections.synchronizedList(new ArrayList<>());


    private ConnectionPool() {
        try {
            Class.forName(MYSQL_DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool newInstance() {
        if (connectionPool == null) {
            LOGGER.debug("POOL IS CREATED");
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }



    public synchronized Connection takeConnection() {
        if (!connectionList.isEmpty()) {
            return connectionList.remove(0);
        } else if (conAmount < MAX_AMOUNT_CONNECTION) {
            try {
                conAmount++;
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                conAmount--;
                throwables.printStackTrace();
            }
        }
        return null;
    }


    public synchronized void returnConnection(Connection connection) {
        if (connection != null) connectionList.add(connection);
        LOGGER.debug("Connection is returned to POOL");

    }

}