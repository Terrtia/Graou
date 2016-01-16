package graou.algo;

import graou.graph.Edge;
import graou.graph.Graph;

public class Dijkstra {

	public Dijkstra() {
		
	}
	
	/**
	 * @param g - graphe à analyser
	 * @param s - sommet de départ
	 * @param t - sommet d'arrivée
	 * @return le plus court chemin entre s et t
	 */
	public Graph rechercheChemin(Graph g, int s, int t) {
		// initialisation du tas
		int nbSommets = g.vertices();
		Heap h = new Heap(nbSommets);
		h.decreaseKey(s, 0);
		
		int i = 0;
		int cout = 0;
		
		// initialisation du tableau des parents
		int[] parent = new int[g.vertices()];
		for(i = 0;i < parent.length;i++) {
			parent[i] = -1;
		}
		
		for(i = 0;i < nbSommets;i++) {
			// calcul du coût
			cout = h.priority(h.pop());
			for(Edge e : g.next(i)) {
				// mise à jour de la priorité et du parent si un chemin plus petit est trouvé
				if(e.getCost()+cout < h.priority(e.getTo())) {
					h.decreaseKey(e.getTo(), cout + e.getCost());
					parent[e.getTo()] = e.getFrom();
				}
			}
		}
		
		// construction graphe chemin de coût minimal en partant de la fin
		Graph res = new Graph(g.vertices());
		int sommet = t;
		for(i = 0;i < 4;i++) {
			res.addEdge(new Edge(parent[sommet], sommet, h.priority(sommet)-h.priority(parent[sommet])));
			sommet = parent[sommet];
		}
			
		return res;
	}
	
}
