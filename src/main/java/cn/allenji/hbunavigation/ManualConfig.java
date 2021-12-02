package cn.allenji.hbunavigation;

import cn.allenji.hbunavigation.domain.port.GraphRepository;
import cn.allenji.hbunavigation.repository.MybatisRepository;
import cn.allenji.hbunavigation.usecase.GetGraph;
import cn.allenji.hbunavigation.usecase.ModifyGraph;
import cn.allenji.hbunavigation.usecase.TraverseGraph;

public class ManualConfig {
    private final GraphRepository graphRepository=new MybatisRepository();


    public GetGraph getGraph(){
        return new GetGraph(graphRepository);
    }

    public ModifyGraph modifyGraph(){
        return new ModifyGraph();
    }

    public TraverseGraph traverseGraph(){
        return new TraverseGraph();
    }

}
