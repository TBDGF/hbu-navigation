package cn.allenji.hbunavigation;

import cn.allenji.hbunavigation.domain.port.GraphRepository;
import cn.allenji.hbunavigation.repository.MybatisRepository;
import cn.allenji.hbunavigation.usecase.GetGraph;
import cn.allenji.hbunavigation.usecase.ModifyGraph;
import cn.allenji.hbunavigation.usecase.TraverseGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManualConfig {
    @Autowired
    MybatisRepository mybatisRepository;


    public GetGraph getGraph(){
        return new GetGraph();
    }

    public ModifyGraph modifyGraph(){
        return new ModifyGraph();
    }

    public TraverseGraph traverseGraph(){
        return new TraverseGraph();
    }

}
