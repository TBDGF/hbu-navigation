package cn.allenji.hbunavigation.usecase;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.repository.MybatisRepository;
import cn.allenji.hbunavigation.repository.entity.MybatisEdge;
import cn.allenji.hbunavigation.repository.entity.MybatisVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModifyGraph {
    @Autowired
    MybatisRepository mybatisRepository;

    public void addVertex(String label, String information,int x,int y){
        Graph.addVertex(label,information,x,y);
    }

    public void addEdge(String source, String target, int weight){
        Graph.addEdge(Graph.getVertex(source), Graph.getVertex(target), weight);
    }

    public void initGraph(){
        List<MybatisVertex> vertices=mybatisRepository.getVertices();
        List<MybatisEdge> edges=mybatisRepository.getEdges();
        for(MybatisVertex vertex:vertices){
            this.addVertex(vertex.getLabel(),vertex.getInformation(), vertex.getX(), vertex.getY());
        }
        for(MybatisEdge edge:edges){
            this.addEdge(edge.getSource(),edge.getTarget(), edge.getWeight());
        }
    }

    public void updateGraph(){
        Graph.getVertices().clear();
        this.initGraph();
    }
}
