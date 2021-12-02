package cn.allenji.hbunavigation.repository;

import cn.allenji.hbunavigation.domain.entity.Edge;
import cn.allenji.hbunavigation.domain.entity.Vertex;
import cn.allenji.hbunavigation.domain.port.GraphRepository;
import cn.allenji.hbunavigation.repository.entity.MybatisEdge;
import cn.allenji.hbunavigation.repository.entity.MybatisVertex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MybatisRepository{

    @Select("select * from vertex")
    List<MybatisVertex> getVertices();

    @Select("select * from edge")
    List<MybatisEdge> getEdges();
}
