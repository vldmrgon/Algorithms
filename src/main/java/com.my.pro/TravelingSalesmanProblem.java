package com.my.pro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TravelingSalesmanProblem {

    public static List<Integer> nearestNeighborTSP(int[][] graph, int startNode) {
        int numNodes = graph.length;
        Set<Integer> unvisitedNodes = new HashSet<>();
        for (int i = 0; i < numNodes; i++) {
            unvisitedNodes.add(i);
        }

        int currentNode = startNode;
        List<Integer> path = new ArrayList<>();
        path.add(currentNode);

        while (!unvisitedNodes.isEmpty()) {
            int nearestNeighbor = findNearestNeighbor(graph, currentNode, unvisitedNodes);
            path.add(nearestNeighbor);
            unvisitedNodes.remove(nearestNeighbor);
            currentNode = nearestNeighbor;
        }
        path.add(startNode);
        return path;
    }

    private static int findNearestNeighbor(int[][] graph, int currentNode, Set<Integer> unvisitedNodes) {
        int nearestNeighbor = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int neighbor : unvisitedNodes) {
            int distance = graph[currentNode][neighbor];
            if (distance < minDistance) {
                minDistance = distance;
                nearestNeighbor = neighbor;
            }
        }
        return nearestNeighbor;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0}
        };

        int startNode = 0;
        List<Integer> resultPath = nearestNeighborTSP(graph, startNode);
        System.out.println("Nearly: " + resultPath);
    }
}