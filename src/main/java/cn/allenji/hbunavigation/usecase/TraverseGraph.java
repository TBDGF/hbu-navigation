package cn.allenji.hbunavigation.usecase;

import cn.allenji.hbunavigation.domain.entity.Edge;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.domain.entity.Vertex;
import cn.allenji.hbunavigation.usecase.entity.WebPath;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraverseGraph {
    private List<Boolean> isVisited=new LinkedList<>();
    private static final int INF = (int) 1e6;
    private int[] pre;  //记录前驱节点，pre[x]=y，在最短路径上，x的前一个节点是y
    private WebPath webPath;

    private void DFS(int root, int depth) {
        this.isVisited.set(root, true);
        System.out.print(Graph.getVertex(root).getLabel() + " ");
        for (int i = 0; i < Graph.getVertex(root).getEdges().size(); i++) {
            Vertex target = Graph.getVertex(root).getEdge(i).getTarget();
            if (!this.isVisited.get(Graph.getVertexIndex(target))) {
                System.out.print(Graph.getVertex(root).getEdge(i).getId()+ " ");
                DFS(Graph.getVertexIndex(target), depth + 1);
            }
        }
    }

    public void DFSTravel(int rootIndex) {
        this.isVisited.clear();
        for (int i = 0; i < Graph.getVertices().size(); i++)
            this.isVisited.add(false);
        DFS(rootIndex, 1);
        System.out.println();
    }

    public void dijkstra(int start){
        pre=new int[Graph.getVertices().size()];
        int[] distance=new int[Graph.getVertices().size()];
        boolean[] hasFound=new boolean[Graph.getVertices().size()];
        for (int i=0;i<Graph.getVertices().size();i++){
            distance[i]=INF;
            hasFound[i]=false;
        }
        distance[start]=0;
        PriorityQueue<Vertex> queue=new PriorityQueue<>();
        Graph.getVertex(start).setDistance(distance[start]);
        queue.add(Graph.getVertex(start));
        while (!queue.isEmpty()){
            Vertex top=queue.peek();
            queue.poll();
            if (hasFound[Graph.getVertexIndex(top)]){
                continue;
            }
            hasFound[Graph.getVertexIndex(top)]=true;
            for (int i=0;i<top.getEdges().size();i++){
                Edge edge=top.getEdge(i);
                if (hasFound[Graph.getVertexIndex(edge.getTarget())])
                    continue;
                if (distance[Graph.getVertexIndex(edge.getTarget())]>edge.getWeight()+top.getDistance()){
                    distance[Graph.getVertexIndex(edge.getTarget())]=edge.getWeight()+top.getDistance();
                    edge.getTarget().setDistance(distance[Graph.getVertexIndex(edge.getTarget())]);
                    queue.add(edge.getTarget());

                    pre[Graph.getVertexIndex(edge.getTarget())]=Graph.getVertexIndex(top);
                }
            }
        }
    }

    public void printPath(int start,int destination){
        if (start==destination){
            this.webPath.getNodes().add(Graph.getVertex(start).getLabel());
            System.out.print(Graph.getVertex(start).getLabel()+" ");
            return;
        }
        printPath(start,pre[destination]);
        this.webPath.getNodes().add(Graph.getVertex(destination).getLabel());
        this.webPath.getEdges().add(Graph.getVertex(pre[destination]).getLabel()+"-to-"+Graph.getVertex(destination).getLabel());
        System.out.print(Graph.getVertex(destination).getLabel()+" ");
    }

    public WebPath getPath(int start,int destination){
        this.dijkstra(start);
        webPath=new WebPath();
        printPath(start,destination);
        return webPath;
    }
}
