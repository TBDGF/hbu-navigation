package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class Vertex implements Comparable<Vertex>{
    private String label;
    private String information;
    private List<Edge> edges;
    private int distance;//遍历时距起点距离

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

    public void addEdge(Vertex target, int weight) {
        for (Edge edge:edges){
            if (edge.getTarget().equals(target)) {
                System.out.println("repeated edge");
                return;
            }
        }
        this.edges.add(new Edge(this,target, weight));
    }

    public Edge getEdge(int index) {
        return this.edges.get(index);
    }

    @Override
    public int compareTo(Vertex vertex) {
        if (this.distance == vertex.distance) {
            return 0;
        } else if (this.distance < vertex.distance) {
            return -1;
        } else {
            return 1;
        }
    }
}
