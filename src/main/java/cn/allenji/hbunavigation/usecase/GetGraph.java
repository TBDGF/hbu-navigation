package cn.allenji.hbunavigation.usecase;

import cn.allenji.hbunavigation.domain.entity.Edge;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.domain.entity.Vertex;
import cn.allenji.hbunavigation.domain.port.GraphRepository;
import cn.allenji.hbunavigation.usecase.entity.WebEdge;
import cn.allenji.hbunavigation.usecase.entity.WebGraph;
import cn.allenji.hbunavigation.usecase.entity.WebVertex;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetGraph {

    public Vertex getVertex(String label){
        return Graph.getVertex(label);
    }

    public WebGraph getWebGraph(){
        WebGraph webGraph=new WebGraph();
        if (!Graph.getVertices().isEmpty()){
            for(Vertex vertex:Graph.getVertices()){
                webGraph.getNodes().add(new WebVertex(vertex));
                for(Edge edge:vertex.getEdges()){
                    webGraph.getEdges().add(new WebEdge(vertex.getLabel(), edge.getTarget().getLabel(), edge.getId(), edge.getWeight()));
                }
            }
        }
        return webGraph;
    }
}
