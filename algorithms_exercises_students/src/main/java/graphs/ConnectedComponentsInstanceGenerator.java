package graphs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ConnectedComponentsInstanceGenerator {

    public static void main(String[] args) {
        int [] nNodes = new int[]{600, 220, 105, 10, 42, 450, 90};
        int [] nEdges = new int[]{120, 7, 3, 20, 23, 150, 40};

        for (int i = 0; i < nNodes.length; i++) {
            ConnectedComponents.Graph randomGraph = generateRandomGraph(nNodes[i], nEdges[i]);
            writeInstance("data/graphs.ConnectedComponents/in_corr_"+i, randomGraph);
        }
        int[] largeNodes = new int[] {5002, 7002};
        for (int i = 0; i < largeNodes.length; i++) {
            ConnectedComponents.Graph randomGraph = complex_graph(largeNodes[i]);
            writeInstance("data/graphs.ConnectedComponents/in_compl_"+i, randomGraph);
        }

        int[] cycleNodes = new int[] {1002, 2002};
        for (int i = 0; i < cycleNodes.length; i++) {
            ConnectedComponents.Graph randomGraph = cycle_graph(cycleNodes[i]);
            writeInstance("data/graphs.ConnectedComponents/in_cycl_"+i, randomGraph);
        }

    }

    public static void writeInstance(String file, ConnectedComponents.Graph graph) {

        int V = graph.V();
        int E = graph.E();
        try {
            PrintWriter p = new PrintWriter(new FileOutputStream(file));
            p.println(V + " " + E);
            for (int i= 0; i < V; i++){
                Iterable<Integer> adj = graph.adj(i);
                for(int e: adj){
                    p.println(i+" "+e);
                }
            }
            int solution = ConnectedComponents.numberOfConnectedComponents(graph);
            p.println(solution);
            p.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ConnectedComponents.Graph generateRandomGraph(int n, int nEdges) {

        ConnectedComponents.Graph randomGraph = new ConnectedComponents.Graph(n);

        for (int i = 0; i < nEdges; i++) {
            int v = (int) (randomGraph.V() * Math.random());
            int w = (int) (randomGraph.V() * Math.random());
            randomGraph.addEdge(v, w);
        }

        return randomGraph;
    }

    public static ConnectedComponents.Graph cycle_graph(int n) {
        ConnectedComponents.Graph g = new ConnectedComponents.Graph(n);
        g.addEdge(0, n - 1);

        for (int i = 1; i < n; i++) {
            g.addEdge(i, (i - 1) % n);
        }
        return g;
    }

    public static ConnectedComponents.Graph complex_graph(int n){
        ConnectedComponents.Graph g = new ConnectedComponents.Graph(n);
        for (int i = 0; i < n; i++) {
            g.addEdge(i, (i + 2) % n);
        }
        return g;
    }

}
