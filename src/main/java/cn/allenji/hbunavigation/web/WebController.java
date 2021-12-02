package cn.allenji.hbunavigation.web;

import cn.allenji.hbunavigation.ManualConfig;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.usecase.GetGraph;
import cn.allenji.hbunavigation.usecase.ModifyGraph;
import cn.allenji.hbunavigation.usecase.TraverseGraph;
import cn.allenji.hbunavigation.usecase.entity.WebGraph;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebController {
    private final ManualConfig manualConfig=new ManualConfig();
    private final GetGraph getGraph= manualConfig.getGraph();
    private final ModifyGraph modifyGraph=manualConfig.modifyGraph();
    private final TraverseGraph traverseGraph= manualConfig.traverseGraph();

    @GetMapping("/hello")
    public String sayHello(){
        Graph.addVertex("图书馆","info");
        Graph.addVertex("北2门口","info-0");
        Graph.addVertex("北1门口","info-1");
        Graph.addVertex("南2门口","info-2");
        Graph.addVertex("南1门口","info-3");
        modifyGraph.addEdge(getGraph.getVertex("图书馆"),getGraph.getVertex("北2门口"),77);
        modifyGraph.addEdge(getGraph.getVertex("北1门口"),getGraph.getVertex("图书馆"),134);
        modifyGraph.addEdge(getGraph.getVertex("北1门口"),getGraph.getVertex("南2门口"),37);
        modifyGraph.addEdge(getGraph.getVertex("北1门口"),getGraph.getVertex("北2门口"),210);
        modifyGraph.addEdge(getGraph.getVertex("南2门口"),getGraph.getVertex("南1门口"),202);
        modifyGraph.addEdge(getGraph.getVertex("南1门口"),getGraph.getVertex("北2门口"),39);
        traverseGraph.DFSTravel(Graph.getVertexIndex("图书馆"));
        return "Hello";
    }


    @GetMapping("/get")
    public WebGraph getGraph(String label){
        return getGraph.getWebGraph();
    }

    @PostMapping("/vertex/add")
    public void add(String label,String information){
        modifyGraph.addVertex(label, information);
    }

    @PostMapping("/edge/add")
    public void add(String source,String target,int weight){
        modifyGraph.addEdge(getGraph.getVertex(source),getGraph.getVertex(target),weight);
    }

    @GetMapping("/traverse/dijkstra")
    public void dijkstra(String label){
        traverseGraph.dijkstra(Graph.getVertexIndex(label));
    }
}
