// Exemplo de pesquisa em largura (BFS) num grafo nao dirigido
// (similar ao codigo feito na teorica - inclui calculo de distancias)

import java.io.*;
import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitado numa pesquisa
    public int distance;            // Distancia ao no origem da pesquisa

    Node() {
	adj = new LinkedList<Integer>();
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos

    Graph(int n) {
	this.n = n;
	nodes  = new Node[n+1]; // +1 se nos comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
	nodes[a].adj.add(b);
	nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public int bfs(int v) {
	int max=0;
	LinkedList<Integer> q = new LinkedList<Integer>();
	for (int i=1; i<=n; i++) nodes[i].visited = false;

	q.add(v);
	nodes[v].visited = true;
	nodes[v].distance = 0;

	while (q.size() > 0) {
	    int u = q.removeFirst();
		
	    for (int w : nodes[u].adj)
		if (!nodes[w].visited) {
		    q.add(w);
			nodes[w].visited  = true;
			
		    max = nodes[w].distance = nodes[u].distance + 1; 
		}   
	}
	return max;
    }
}

public class Biologica {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
		int nos = in.nextInt();
	Graph g = new Graph(nos);
	int   e = in.nextInt();
	for (int i=0; i<e; i++) 
	    g.addLink(in.nextInt(), in.nextInt());
	
		int result[] = new int[1501];
	// Pesquisa em largura a partir do no
	for(int i=1; i<=nos; i++) result[i] = g.bfs(i);



	int print = 0;
	int diametro = 0;
	int raio = Integer.MAX_VALUE;
	int central = 0;
	int periferico = 0;
	int space = 0;
	
	for(int p=0; p<4; p++){
		switch(print) {
			case 0: 
			for(int i=1; i<=nos; i++) {
				if(result[i]>diametro) diametro = result[i];
			}
			System.out.println(diametro);
			print = 1;
			break;

			case 1:
			for(int i=1; i<=nos; i++) {
				if(result[i]<raio) 
					raio = result[i];
					
				
			}
			System.out.println(raio);
			print = 2;
			break;

			case 2: 
			for(int i=1; i<=nos; i++) {
				if(result[i]==raio) {
					space++;
					if(space==1)System.out.print(i);
					else System.out.print(" "+i);
				}
			}
			System.out.println();
			space=0;
			print = 3;
			break;

			case 3:
			for(int i=1; i<=nos; i++) {
				if(result[i]==diametro) {
					space++;
					if(space==1)System.out.print(i);
					else System.out.print(" "+i);
				}
			}
			System.out.println();

		}
	}
	}

	
	
}