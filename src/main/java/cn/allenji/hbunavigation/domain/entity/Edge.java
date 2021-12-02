package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

@Data
public class Edge {
    private String id;
    private Vertex target;
    private int weight;

    public Edge(Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public Edge(Vertex source,Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
        this.id =source.getLabel()+"-to-"+target.getLabel();
    }
}