package ex1;

public class MyTest1 {
    public static void main(String[] args) {

        weighted_graph g0 = new WGraph_DS();
        int i = 0;
        while(i < 10) {
            g0.addNode(i);
            i++;
        }
        g0.connect(0,1,5);
        g0.connect(0,1,5);
        g0.connect(0,1,20);
        g0.connect(0,2,5);
        g0.connect(0,3,10);
        g0.connect(0,4,5);
        g0.connect(8,9,5);
        g0.connect(0,8,2);
        g0.connect(1,7,5);
        g0.connect(5,6,1);
        g0.connect(5,9,6);
        g0.connect(4,7,5);
        g0.connect(7,6,5);
        g0.connect(7,6,1);
        g0.removeEdge(0,3);


        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g0);
        System.out.println(ga.isConnected());
        System.out.println("shortestPath: " + ga.shortestPath(0, 5));
        System.out.println("shortestPathDist: " + ga.shortestPathDist(0, 5));



    }
}