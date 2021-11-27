package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class Graph {
    private static List<Vertex> vertices;
    private static List<Boolean> isVisited;

    private Vertex getVertex(int Index) {
        return vertices.get(Index);
    }

    public Graph() {
        vertices = new LinkedList<>();
        isVisited = new LinkedList<>();
    }

    public void addVertex(String label, String information) {
        vertices.add(new Vertex(label, information));
    }

    public void addEdge(int sourceIndex, int targetIndex, int weight) {
        this.getVertex(sourceIndex).addEdge(targetIndex, weight);
        this.getVertex(targetIndex).addEdge(sourceIndex, weight);
    }

    public int getVertexId(String label) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getLabel().equals(label))
                return i;
        }
        return -1;
    }

    private void DFS(int root, int depth) {
        isVisited.set(root, true);
        System.out.println(this.getVertex(root).getLabel() + " ");
        for (int i = 0; i < this.getVertex(root).getEdges().size(); i++) {
            int target = this.getVertex(root).getEdge(i).getTargetIndex();
            if (!isVisited.get(target)) {
                System.out.println(this.getVertex(root).getEdge(i).hashCode() + " " + this.getVertex(root).getEdge(i).getWeight());
                this.DFS(target, depth + 1);
            }
        }
    }

    public void DFSTravel(int rootIndex) {
        isVisited.clear();
        for (int i = 0; i < vertices.size(); i++)
            isVisited.add(false);
        DFS(rootIndex, 1);
    }
}



