package cn.allenji.hbunavigation.web;

import cn.allenji.hbunavigation.ManualConfig;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.usecase.GetGraph;
import cn.allenji.hbunavigation.usecase.ModifyGraph;
import cn.allenji.hbunavigation.usecase.TraverseGraph;
import cn.allenji.hbunavigation.usecase.entity.WebGraph;
import cn.allenji.hbunavigation.usecase.entity.WebPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class WebController {
    @Autowired
    ModifyGraph modifyGraph;
    private final ManualConfig manualConfig=new ManualConfig();
    private final GetGraph getGraph= manualConfig.getGraph();

    @GetMapping("/hello")
    public String sayHello(){
        modifyGraph.initGraph();
        return "Hello";
    }

    @GetMapping("/get")
    public WebGraph getGraph(){
        return getGraph.getWebGraph();
    }

    @PostMapping("/vertex/add")
    public void add(String label,String information,int x,int y){
        modifyGraph.addVertex(label, information,x,y);
    }

    @PostMapping("/edge/add")
    public void add(String source,String target,int weight){
        modifyGraph.addEdge(source,target,weight);
    }

    @GetMapping("/traverse/dijkstra")
    public List<WebPath> dijkstra(String start,String destination){
        TraverseGraph traverseGraph= manualConfig.traverseGraph();
        List<WebPath> paths=new LinkedList<>();
        paths.add(traverseGraph.getWebPath(Graph.getVertexIndex(start),Graph.getVertexIndex(destination)));
        return paths;
    }

    @PostMapping("/update")
    public String update(){
        modifyGraph.updateGraph();
        return "OK";
    }

    @PostMapping("/traverse/paths")
    public List<WebPath> getPaths(@RequestBody Map<String,Object> body){
        System.out.println(body);
        TraverseGraph traverseGraph= manualConfig.traverseGraph();
        List<Integer> pass=new ArrayList<>();
        for (Object object: (List<Object>)body.get("pass")){
            pass.add(Graph.getVertexIndex((String) object));
        }
        return traverseGraph.getPaths(Graph.getVertexIndex((String) body.get("start")),Graph.getVertexIndex((String) body.get("destination")),pass);
    }
}
