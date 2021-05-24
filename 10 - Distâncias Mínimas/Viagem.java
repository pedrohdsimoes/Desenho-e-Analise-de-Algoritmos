// Exemplo de aplicacao do algoritmo de Dijkstra
// (assumindo um grafo pesado e dirigido, sem pesos negativos)
// (codigo adaptado do codigo em C++ feito na teorica)

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

    // Algoritmo de Dijkstra
    void dijkstra(int s, int destino) {
	
	//Inicializar nos como nao visitados e com distancia infinita
	for (int i=1; i<=n; i++) {
	    nodes[i].distance = Integer.MAX_VALUE;
	    nodes[i].visited  = false;
	}
	
	// Inicializar "fila" com no origem
	nodes[s].distance = 0;
	TreeSet<NodeQ> q = new TreeSet<>();
	q.add(new NodeQ(0, s)); // Criar um par (dist=0, no=s)

	// Ciclo principal do Dijkstra
	while (!q.isEmpty()) {
      
	    // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
	    NodeQ nq = q.pollFirst();
	    int  u = nq.node;
	    nodes[u].visited = true;
        if(u==destino) {
            System.out.printf("%.1f",nodes[u].distance);
            System.out.println();
        }
	    
	    // Relaxar arestas do no retirado
	    for (Edge e : nodes[u].adj) {
		int v = e.to;
		double cost = e.weight;
		if (!nodes[v].visited && nodes[u].distance + cost < nodes[v].distance) {
		    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
		    nodes[v].distance = nodes[u].distance + cost;
		    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
		}
	    }
	}
    }
};


public class Viagem {

    static TreeMap<String,Integer> map;
    static int id = 1;

    public static int stringId(String local) {
 
        if(map.get(local)==null) {
            map.put(local,id);
            id++;
        }
        //System.out.println(local+ " id= " + map.get(local));
        return map.get(local);
    }

    public static void main(String args[]) {
	FastScanner in = new FastScanner(System.in);

    Graph g = new Graph(in.nextInt());
    map = new TreeMap<>();
    int   e = in.nextInt();
    int casa = stringId(in.next()); int destino = stringId(in.next());
    
	for (int i=0; i<e; i++) 
        g.addLink(stringId(in.next()),stringId(in.next()), in.nextDouble());
        

	// Execucao exemplo a partir do no 1
	g.dijkstra(casa, destino);
    }
}