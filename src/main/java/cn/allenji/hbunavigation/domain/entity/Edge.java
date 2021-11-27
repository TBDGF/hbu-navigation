package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

@Data
class Edge {
    private int targetIndex;
    private int weight;

    public Edge(int targetIndex, int weight) {
        this.targetIndex = targetIndex;
        this.weight = weight;
    }
}