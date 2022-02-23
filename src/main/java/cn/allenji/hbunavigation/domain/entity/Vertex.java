package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class Vertex implements Comparable<Vertex> {  //实现Comparable接口，用于优先队列
    private String label;       //储存节点的名称
    private String information; //储存节点详细信息
    private List<Edge> edges;   //储存边List
    private int distance;       //遍历时距起点距离，用于dijkstra算法
    private int x, y;           //在图上点的绝对位置，用于可视化

    public Vertex() {
        this.label = null;
        this.information = null;
        this.edges = new LinkedList<>();
    }

    public Vertex(String label, String information, int x, int y) {
        this.label = label;
        this.information = information;
        this.edges = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void addEdge(Vertex target, int weight) {
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                System.out.println("repeated edge");
                return;
            }
        }
        this.edges.add(new Edge(this, target, weight));
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
