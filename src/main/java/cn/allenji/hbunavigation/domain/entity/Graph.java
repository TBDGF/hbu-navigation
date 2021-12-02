package cn.allenji.hbunavigation.domain.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Graph {
    private static List<Vertex> vertices=new LinkedList<>();

    public static Vertex getVertex(int Index) {
        return vertices.get(Index);
    }

    public static Vertex getVertex(String label) {
        for (Vertex vertex:vertices){
            if (vertex.getLabel().equals(label))
                return vertex;
        }
        return null;
    }

    public static List<Vertex> getVertices(){
        return vertices;
    }

    public static void addVertex(String label, String information) {
        for (Vertex vertex:vertices){
            if (vertex.getLabel().equals(label)){
                System.out.println("repeated vertex label");
                return;
            }
        }
        vertices.add(new Vertex(label, information));
    }

    public static void addEdge(Vertex source, Vertex target, int weight) {
        source.addEdge(target, weight);
        target.addEdge(source, weight);
    }

    public static int getVertexIndex(String label) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getLabel().equals(label))
                return i;
        }
        return -1;
    }

    public static int getVertexIndex(Vertex vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(vertex))
                return i;
        }
        return -1;
    }

}



