import java.util.*;

class PackageDeliveryOptimizer {
    // Graph representation using adjacency list
    static class Graph {
        private Map<String, List<Edge>> adjacencyList;

        public Graph() {
            adjacencyList = new HashMap<>();
        }

        // Add an edge to the graph
        public void addEdge(String source, String destination, int weight) {
            adjacencyList.putIfAbsent(source, new ArrayList<>());
            adjacencyList.putIfAbsent(destination, new ArrayList<>());
            adjacencyList.get(source).add(new Edge(destination, weight));
            adjacencyList.get(destination).add(new Edge(source, weight)); // Bidirectional
        }

        // Dijkstra's algorithm to find the shortest path
        public void findShortestPath(String start, String end) {
            Map<String, Integer> distances = new HashMap<>();
            Map<String, String> previousNodes = new HashMap<>();
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            
            for (String node : adjacencyList.keySet()) {
                distances.put(node, Integer.MAX_VALUE);
            }
            distances.put(start, 0);
            priorityQueue.add(new Edge(start, 0));

            while (!priorityQueue.isEmpty()) {
                Edge current = priorityQueue.poll();
                String currentNode = current.node;

                for (Edge neighbor : adjacencyList.getOrDefault(currentNode, new ArrayList<>())) {
                    int newDistance = distances.get(currentNode) + neighbor.weight;
                    if (newDistance < distances.get(neighbor.node)) {
                        distances.put(neighbor.node, newDistance);
                        previousNodes.put(neighbor.node, currentNode);
                        priorityQueue.add(new Edge(neighbor.node, newDistance));
                    }
                }
            }

            // Output the shortest path
            printPath(previousNodes, start, end, distances.get(end));
        }

        private void printPath(Map<String, String> previousNodes, String start, String end, int distance) {
            if (distance == Integer.MAX_VALUE) {
                System.out.println("No route found.");
                return;
            }
            List<String> path = new ArrayList<>();
            for (String at = end; at != null; at = previousNodes.get(at)) {
                path.add(at);
            }
            Collections.reverse(path);

            System.out.println("Shortest path: " + String.join(" -> ", path));
            System.out.println("Total distance: " + distance);
        }
    }

    // Edge class to represent connections
    static class Edge {
        String node;
        int weight;

        Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        // Input graph edges
        System.out.println("Enter number of routes:");
        int routes = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter routes (format: source destination distance):");
        for (int i = 0; i < routes; i++) {
            String[] input = scanner.nextLine().split(" ");
            String source = input[0];
            String destination = input[1];
            int distance = Integer.parseInt(input[2]);
            graph.addEdge(source, destination, distance);
        }

        // Input start and end locations
        System.out.println("Enter start location:");
        String start = scanner.nextLine();

        System.out.println("Enter end location:");
        String end = scanner.nextLine();

        // Find and print shortest path
        System.out.println("\nFinding shortest path...");
        graph.findShortestPath(start, end);

        scanner.close();
    }
}
