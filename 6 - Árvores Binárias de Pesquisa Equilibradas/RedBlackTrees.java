// Codigo inicial para o problema [DAA 022] Arvores Red-Black
// Pedro Ribeiro (DCC/FCUP)

import java.util.Scanner;

// Estrutura para representar um no da arvore
class Node {
    boolean isBlack;  // No preto?
    boolean isNull;   // No nulo?
    int value;        // Valor
    Node left, right; // Filhos

    Node(int v) {
	isNull  = (v==0);
	isBlack = (v>=0);
	value   = Math.abs(v);
    }
}

public class RedBlackTrees {

    // Ler input em preorder
    static Node readPreOrder(Scanner in) {
	int v = in.nextInt();
	Node aux = new Node(v);
	if (v!=0) {
	    aux.left  = readPreOrder(in);
	    aux.right = readPreOrder(in);
	}
	return aux;
    }

    // Menor valor da arvore
    static int minimum(Node t) {
	if (t.isNull) return Integer.MAX_VALUE;
	int minLeft  = minimum(t.left);
	int minRight = minimum(t.right);
	return Math.min(t.value, Math.min(minLeft, minRight));
    }

    // Maior valor da arvore
    static int maximum(Node t) {
	if (t.isNull) return Integer.MIN_VALUE;
	int minLeft  = maximum(t.left);
	int minRight = maximum(t.right);
	return Math.max(t.value, Math.max(minLeft, minRight));
    }

    // Quantidade de nos (internos)
    static int size(Node t) {
	if (t.isNull) return 0;
	return 1 + size(t.left) + size(t.right);
    }
    static int getLeafCount(Node node) { 
        if (node.isNull) 
            return 0; 
        if (node.left.isNull && node.right.isNull) 
            return 1; 
        else
            return getLeafCount(node.left) + getLeafCount(node.right); 
    } 

    static int countBlack(Node b) {
        if(b.isNull) return 1;
        else {
            int left = countBlack(b.left) + (b.left.isBlack ? 1 : 0);
            int right = countBlack(b.right) + (b.right.isBlack ? 1 : 0);
            if(left != right) return 0;
            return left;
        }
    }
    static boolean rootProperty(Node root) {
            if(root.isBlack) return true;
            return false;
    }

    static boolean redProperty(Node r) {
            if(r.isNull) return true;
            if(!r.isBlack) {
                if(!r.left.isBlack || !r.right.isBlack) return false;
            }
            return redProperty(r.left) && redProperty(r.right);     
    }

    static boolean blackProperty(Node b) {
      if(countBlack(b)==0) return false;
      return true;
    }

    static boolean binaryTree(Node root,int min, int max) {
        if(root.isNull) return true;
        if(root.value >min && root.value < max && binaryTree(root.right, root.value, max) && binaryTree(root.left, min, root.value)) 
        return true;
        else if(root.value > root.right.value) return false;
        else return false;
    }
    // ---------------------------------------------------
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        int n =  in.nextInt();
        for (int i=0; i<n; i++) {
            Node root = readPreOrder(in);
            if(rootProperty(root) && redProperty(root) && blackProperty(root) && binaryTree(root,Integer.MIN_VALUE, Integer.MAX_VALUE)) 
                System.out.println("SIM"); 
            else System.out.println("NAO");
        }	
    }
}