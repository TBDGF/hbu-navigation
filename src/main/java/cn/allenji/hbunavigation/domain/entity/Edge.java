package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

@Data
public class Edge {
    private String id;      //边的id，由起点和终点自动生成
    private Vertex target;  //目标节点
    private int weight;     //权值

    public Edge(Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
        this.id = source.getLabel() + "-to-" + target.getLabel();
    }
}