package com.solvd.dijkstra.interfaces;

public interface IConstant {

    String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/banks";
    String USERNAME = "root";
    String PASSWORD = "NpwS1L91";
    int MAX_AMOUNT_CONNECTION = 5;

    String UNSUPPORTED_DELETE = "METHOD DELETE ISN'T SUPPORTED FOR THIS DAO";
    String UNSUPPORTED_UPDATE = "METHOD UPDATE ISN'T SUPPORTED FOR THIS DAO";
    String UNSUPPORTED_ADD = "METHOD ADD ISN'T SUPPORTED FOR THIS DAO";
    String UNSUPPORTED_GET_ALL = "METHOD GET_ALL ISN'T SUPPORTED FOR THIS DAO";

}