package cn.allenji.hbunavigation.usecase;

import cn.allenji.hbunavigation.domain.entity.Edge;
import cn.allenji.hbunavigation.domain.entity.Graph;
import cn.allenji.hbunavigation.domain.entity.Vertex;
import cn.allenji.hbunavigation.usecase.entity.WebPath;
import cn.allenji.hbunavigation.utils.Perm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraverseGraph {
    private List<Boolean> isVisited = new LinkedList<>();   //记录节点是否被访问
    private static final int INF = (int) 1e6;   //设置足够大的常数，表示距离为无穷，即不可到达
    private int[] pre;          //记录前驱节点，pre[x]=y，在最短路径上，x的前一个节点是y
    private int[] distance;     //记录到起点的距离
    private WebPath webPath;
    private int[][] adjMatrix;  //用于floyd的邻接矩阵

    private void DFS(int root, int depth) {
        this.isVisited.set(root, true);
        System.out.print(Graph.getVertex(root).getLabel() + " ");
        for (int i = 0; i < Graph.getVertex(root).getEdges().size(); i++) {
            Vertex target = Graph.getVertex(root).getEdge(i).getTarget();
            if (!this.isVisited.get(Graph.getVertexIndex(target))) {
                System.out.print(Graph.getVertex(root).getEdge(i).getId() + " ");
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

    public void dijkstra(int start) {
        pre = new int[Graph.getVertices().size()];
        distance = new int[Graph.getVertices().size()];
        boolean[] hasFound = new boolean[Graph.getVertices().size()];
        for (int i = 0; i < Graph.getVertices().size(); i++) {
            distance[i] = INF;
            hasFound[i] = false;
        }
        distance[start] = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        Graph.getVertex(start).setDistance(distance[start]);
        queue.add(Graph.getVertex(start));
        while (!queue.isEmpty()) {
            Vertex top = queue.peek();
            queue.poll();
            if (hasFound[Graph.getVertexIndex(top)]) {
                continue;
            }
            hasFound[Graph.getVertexIndex(top)] = true;
            for (int i = 0; i < top.getEdges().size(); i++) {
                Edge edge = top.getEdge(i);
                int targetIndex=Graph.getVertexIndex(edge.getTarget());
                if (hasFound[targetIndex])
                    continue;
                if (distance[targetIndex] > edge.getWeight() + top.getDistance()) {
                    distance[targetIndex] = edge.getWeight() + top.getDistance();
                    edge.getTarget().setDistance(distance[targetIndex]);
                    queue.add(edge.getTarget());

                    pre[targetIndex] = Graph.getVertexIndex(top);
                }
            }
        }
    }

    public void floyd() {
        int graphSize = Graph.getVertices().size();
        this.adjMatrix = new int[graphSize][graphSize]; //初始化邻接矩阵
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                this.adjMatrix[i][j] = INF;
            }
        }
        for (int i = 0; i < graphSize; i++) {   //将以邻接表储存的图储存成临界矩阵的格式
            if (!Graph.getVertex(i).getEdges().isEmpty()) {
                for (Edge edge : Graph.getVertex(i).getEdges()) {
                    adjMatrix[i][Graph.getVertexIndex(edge.getTarget())] = edge.getWeight();
                }
            }
        }
        for (int k = 0; k < graphSize; k++) {   //执行floyd算法
            for (int i = 0; i < graphSize; i++) {
                if (adjMatrix[i][k] != INF) {
                    for (int j = 0; j < graphSize; j++) {
                        if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j])
                            adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }
    }

    public void traverseWebPath(int start, int destination) {
        if (start == destination) {
            this.webPath.getNodes().add(Graph.getVertex(start).getLabel());
            System.out.print(Graph.getVertex(start).getLabel() + " ");
            return;
        }
        traverseWebPath(start, pre[destination]);
        this.webPath.getNodes().add(Graph.getVertex(destination).getLabel());
        this.webPath.getEdges().add(Graph.getVertex(pre[destination]).getLabel() + "-to-" + Graph.getVertex(destination).getLabel());
        System.out.print(Graph.getVertex(destination).getLabel() + " ");
    }

    public WebPath getWebPath(int start, int destination) { //调用dijkstra，并保存路径
        this.dijkstra(start);
        webPath = new WebPath();
        traverseWebPath(start, destination);
        System.out.println(this.distance[destination]);
        return webPath;
    }

    public List<WebPath> getPaths(int start, int destination, List<Integer> pass) {
        List<WebPath> paths = new LinkedList<>();
        this.floyd();
        int minDistance = INF;
        List<Integer> minList = new ArrayList<>();
        List<List<Integer>> passMatrix = Perm.permList(pass);
        for (int i = 0; i < passMatrix.size(); i++) {
            List<Integer> passList = passMatrix.get(i);
            int cnt = 0;
            for (int j = 0; j < passList.size() - 1; j++) {
                if (adjMatrix[passList.get(j)][passList.get(j + 1)] != INF) {
                    cnt += adjMatrix[passList.get(j)][passList.get(j + 1)];
                }
            }
            cnt += adjMatrix[start][passList.get(0)];
            cnt += adjMatrix[passList.get(passList.size() - 1)][destination];
            if (minDistance > cnt) {
                minDistance = cnt;
                minList = passList;
            }
        }
        paths.add(getWebPath(start, minList.get(0)));
        for (int i = 0; i < minList.size() - 1; i++)
            paths.add(getWebPath(minList.get(i), minList.get(i + 1)));
        paths.add(getWebPath(minList.get(minList.size() - 1), destination));
        System.out.println(paths);
        return paths;
    }

}
