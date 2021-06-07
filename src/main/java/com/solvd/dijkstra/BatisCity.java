package com.solvd.dijkstra;

import com.solvd.dijkstra.interfaces.ICity;
import com.solvd.dijkstra.interfaces.IPathway;
import com.solvd.dijkstra.models.Cities;
import com.solvd.dijkstra.models.Pathway;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BatisCity {

    public static Cities getCity(int id) {
        try (SqlSession sqlSession = SessionFactory.getSqlSessionFactory().openSession()) {
            ICity city = sqlSession.getMapper(ICity.class);
            return city.getById(1);
        }
    }

    public static void addCity(Cities entity){
        try (SqlSession sqlSession = SessionFactory.getSqlSessionFactory().openSession(true)){
            ICity city = sqlSession.getMapper(ICity.class);
            city.add(entity);
        }
    }

    public static Pathway getPathway(int id) {
        try (SqlSession sqlSession = SessionFactory.getSqlSessionFactory().openSession()) {
            IPathway pathway = sqlSession.getMapper(IPathway.class);
            return pathway.getById(1);
        }
    }

    public static List<Pathway> getAll(){
        try (SqlSession sqlSession = SessionFactory.getSqlSessionFactory().openSession()) {
            IPathway pathway = sqlSession.getMapper(IPathway.class);
            return pathway.getAll();
        }
    }

    public static void main(String[] args) {
        System.out.println( BatisCity.getAll());

    }
}
