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
		int nbSommets = g.vertices();
		Heap h = new Heap(nbSommets);
		h.decreaseKey(s, 0);
		int i = 0;
		int cout = 0;
		h.affiche();
		
		for(i = 0;i < nbSommets;i++) {
			for(Edge e : g.next(i)) {
				//cout = 0;
				//cout = cout + e.getCost();
				if(e.getCost()+cout < h.priority(e.getTo())) {
					h.decreaseKey(e.getTo(), cout + e.getCost());
				}
				//h.decreaseKey(e.getTo(), cout + e.getCost());
				//h.pop();
				//h.affiche();
			}
			h.affiche();	
		}
		h.affiche();	
			
		return g;
	}
	
}
