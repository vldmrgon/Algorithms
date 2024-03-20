package com.my.pro;

import java.util.*;

public class Graph {

    private final int vertices;
    private final List<List<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adjacencyList.add(new ArrayList<>());
    }

    // source - original vertex, final vertex
    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }

    // TODO: 2/19/24 Depth-First Search
    // TODO: 2/19/24 Breadth-First Search
    // TODO: 2/19/24 Dijkstra's Algorithm 


    // TODO: 2/19/24 Bellman-Ford Algorithm
    // TODO: 2/19/24 Prim's Algorithm 
    // TODO: 2/19/24 Kruskal's Algorithm
    // TODO: 2/19/24 Floyd-Warshall Algorithm
    // TODO: 2/19/24 Tarjan's Algorithm

    static class Vertex {
        int id;
        int distance;

        Vertex(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    public boolean isCyclic(Graph graph) {
        int length = graph.vertices;
        boolean[] visited = new boolean[length];
        boolean[] recStack = new boolean[length];

        for (int i = 0; i < vertices; i++) {
            if (isCyclicUtil(i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int currentVertex, boolean[] visited, boolean[] recStack) {
        if (recStack[currentVertex]) {
            return true;
        }

        if (visited[currentVertex]) {
            return false;
        }

        visited[currentVertex] = true;
        recStack[currentVertex] = true;
        List<Integer> children = adjacencyList.get(currentVertex);

        for (Integer child : children) {
            if (isCyclicUtil(child, visited, recStack))
                return true;
        }
        recStack[currentVertex] = false;
        return false;
    }

    //  int[i][j] graph  i - row , j - column

    public static void dijkstra(int[][] graph, int start) {
        final int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
        queue.add(new Vertex(start, 0));

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            int u = currentVertex.id;

            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v]) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        queue.add(new Vertex(v, dist[v]));
                    }
                }
            }
        }

        System.out.println("Shortest distances from vertex " + start + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Vertex " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

}
