import java.util.*;

// Classe que representa uma aresta
class Edge {
    int to;     // No destino
    double weight; // Peso da aresta
    
    Edge(int t, double w) {
	to = t;
	weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public double distance;         // Distancia ao no origem da pesquisa
    
    Node() {
	adj = new LinkedList<>();
    } 
};

class Ponto {
    double x;
    double y;

    Ponto(double cx, double cy) {
        x = cx;
        y = cy;
    }

}
// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public double cost;
    public int node;

    NodeQ(double c, int n) {
	cost = c;
	node = n;
    }

    @Override
    public int compareTo(NodeQ nq) { 
        if (cost < nq.cost) return -1; 
        if (cost > nq.cost) return +1;
	if (node < nq.node) return -1; 
	if (node > nq.node) return +1;
        return 0; 
    } 
}

// Classe que representa um grafo
class Graph {
    int n;          // Numero de nos do grafo
    Node[] nodes;   // Array para conter os nos
    
    Graph(int n) {
	this.n = n;
	nodes = new Node[n+1];  // +1 se os nos comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }
    
    void addLink(int a, int b, double c) {
    nodes[a].adj.add(new Edge(b, c));
    nodes[b].adj.add(new Edge(a, c));
    }

    // Algoritmo de Prim
    void prim(int s) {
	
	//Inicializar nos como nao visitados e com distancia infinita
	for (int i=1; i<=n; i++) {
	    nodes[i].distance = Integer.MAX_VALUE;
	    nodes[i].visited  = false;
	}
	
	// Inicializar "fila" com no origem
	nodes[s].distance = 0;
	TreeSet<NodeQ> q = new TreeSet<>();
	q.add(new NodeQ(0, s)); // Criar um par (dist=0, no=s)

    double total = 0;
	// Ciclo principal do Dijkstra
	while (!q.isEmpty()) {
      
	    // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
	    NodeQ nq = q.pollFirst();
	    int  u = nq.node;
	    nodes[u].visited = true;
       //System.out.println(u + " [dist=" + nodes[u].distance + "]");
        total += nodes[u].distance;
	    
	    // Relaxar arestas do no retirado
	    for (Edge e : nodes[u].adj) {
		int v = e.to;
		double cost = e.weight;
		if (!nodes[v].visited && cost < nodes[v].distance) {
		    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
		    nodes[v].distance = cost;
		    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
		}
	    }
    }
    System.out.printf("%.5f",total);
    System.out.println();
    }
};

public class Sardas {
    public static double distance(Ponto p1, Ponto p2) {
        return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
    }

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
    double distance = 0;
    int sardas = in.nextInt();
    Ponto p[] = new Ponto[sardas+1];
	Graph g = new Graph(sardas);
	for (int i=1; i<=sardas; i++) {
        double x = in.nextDouble();
        double y = in.nextDouble();
        p[i] = new Ponto(x,y);
    }
  
     for(int i=1; i<=sardas; i++)  
        for(int j=i+1; j<=sardas; j++) { 
            g.addLink(i, j, distance(p[i],p[j]));
    }
   
	g.prim(1);
    }
}