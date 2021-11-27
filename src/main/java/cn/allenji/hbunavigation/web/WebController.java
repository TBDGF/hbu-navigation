package cn.allenji.hbunavigation.web;

import cn.allenji.hbunavigation.domain.entity.Graph;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping
    public String sayHello(){
        Graph graph=new Graph();
        graph.addVertex("node-5","info-5");
        graph.addVertex("node-0","info-0");
        graph.addVertex("node-1","info-1");
        graph.addVertex("node-2","info-2");
        graph.addVertex("node-3","info-3");
        graph.addEdge(graph.getVertexId("node-0"),graph.getVertexId("node-1"),5);
        graph.addEdge(graph.getVertexId("node-1"),graph.getVertexId("node-2"),5);
        graph.addEdge(graph.getVertexId("node-2"),graph.getVertexId("node-3"),5);
        graph.addEdge(graph.getVertexId("node-0"),graph.getVertexId("node-5"),5);
        graph.DFSTravel(graph.getVertexId("node-1"));
        return graph.toString();
    }

}
