package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
class Vertex {
    private String label;
    private String information;
    private List<Edge> edges;

    public Vertex() {
        this.label = null;
        this.information = null;
        this.edges = new LinkedList<>();
    }

    public Vertex(String label, String information) {
        this.label = label;
        this.information = information;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int targetIndex, int weight) {
        this.edges.add(new Edge(targetIndex, weight));
    }

    public Edge getEdge(int index) {
        return this.edges.get(index);
    }
}
