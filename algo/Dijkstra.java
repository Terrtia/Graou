package graou.algo;

import java.util.ArrayList;
import java.util.Collections;

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
	public ArrayList<Integer> rechercheChemin(Graph g, int s, int t) {
		// initialisation du tas
		int nbSommets = g.vertices();
		Heap h = new Heap(nbSommets);
		h.decreaseKey(s, 0);
		
		int i = 0;
		int cout = 0;
		int sommet;
		
		// initialisation du tableau des parents
		int[] parent = new int[g.vertices()];
		for(i = 0;i < parent.length;i++) {
			parent[i] = -1;
		}
		
		for(i = 0;i < nbSommets;i++) {
			// calcul du coût
			sommet = h.pop();
			cout = h.priority(sommet);
			for(Edge e : g.next(sommet)) {
				// mise à jour de la priorité et du parent si un chemin plus petit est trouvé
				if(e.getCost()+cout < h.priority(e.getTo())) {
					h.decreaseKey(e.getTo(), cout + e.getCost());
					parent[e.getTo()] = e.getFrom();
				}
			}
		}
		
		// remplissage de l'arraylist avec les sommets du chemin de coût minimal
		ArrayList<Integer> liste = new ArrayList<Integer>();
		sommet = parent[t];
		while(sommet != s) {
			liste.add(sommet);
			sommet = parent[sommet];
		}
	
		return liste;

	}
	
}
