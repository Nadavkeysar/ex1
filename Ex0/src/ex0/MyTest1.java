package ex0;

public class MyTest1 {
    public static void main(String args[]) {
        graph g0 = new Graph_DS();
        node_data n0 = new NodeData();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        node_data n5 = new NodeData();
        node_data n6 = new NodeData();
        node_data n7 = new NodeData();
        node_data n8 = new NodeData();
        node_data n9 = new NodeData();
        node_data n10 = new NodeData();
        node_data n11 = new NodeData();
        node_data n12 = new NodeData();
        node_data n13 = new NodeData();
        node_data n14 = new NodeData();
        node_data n15 = new NodeData();

        g0.addNode(n5);
        g0.addNode(n6);
        g0.addNode(n8);
        g0.addNode(n9);
        g0.addNode(n10);
        g0.addNode(n11);
        g0.addNode(n12);
        g0.addNode(n13);
        g0.addNode(n14);

        g0.connect(5,10);g0.connect(5,11);g0.connect(5,13);g0.connect(5,14);g0.connect(6,8);g0.connect(6,10);
        g0.connect(6,11);g0.connect(6,12);g0.connect(6,13);g0.connect(8,6);g0.connect(8,9);g0.connect(8,10);
        g0.connect(9,8);g0.connect(9,10);g0.connect(9,11);g0.connect(9,12);g0.connect(10,5);g0.connect(10,6);
        g0.connect(10,8);g0.connect(10,9);g0.connect(10,11);g0.connect(10,12);g0.connect(10,14);g0.connect(11,5);
        g0.connect(11,6);g0.connect(11,9);g0.connect(11,10);g0.connect(11,13);g0.connect(11,14);g0.connect(12,6);
        g0.connect(12,9);g0.connect(12,10);g0.connect(12,13);g0.connect(12,14);g0.connect(13,5);g0.connect(13,6);
        g0.connect(13,11);g0.connect(13,12);g0.connect(14,5);g0.connect(14,10);g0.connect(14,11);g0.connect(14,12);

        g0.removeNode(5);


        graph_algorithms ga = new Graph_Algo();

        ga.init(g0);

        System.out.println(ga.isConnected());


    }
}
