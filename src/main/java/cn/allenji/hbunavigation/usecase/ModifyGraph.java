package cn.allenji.hbunavigation.usecase;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.domain.entity.Vertex;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ModifyGraph {

    public void addVertex(String label, String information){
        Graph.addVertex(label,information);
    }

    public void addEdge(Vertex source, Vertex target, int weight){
        Graph.addEdge(source, target, weight);
    }
}
