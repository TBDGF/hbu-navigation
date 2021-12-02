package cn.allenji.hbunavigation.usecase.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class WebGraph {
    List<WebVertex> nodes;
    List<WebEdge> edges;

    public WebGraph(){
        this.nodes=new LinkedList<>();
        this.edges=new LinkedList<>();
    }
}
