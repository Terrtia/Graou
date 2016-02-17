package graou.algo;

import graou.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class Suurballe {

	public Suurballe(){
		
	}
	
	public ArrayList<Integer> rechercheChemin(Graph g, int s, int t, int[][] intereset) {
		
		   
		   Dijkstra d = new Dijkstra();
		   //recherche du premier chemin
		   ArrayList<Integer> chemin1 = d.rechercheChemin(g, 0, g.vertices() - 1);
		   
		   System.out.println("chemin 1 = ");
		   for(Integer i : chemin1){
			   System.out.println(i);
			   }
		   System.out.println("");
		      
		   g.calcMinCost(s);
		   g.diffCost(s);
		   
		   g.writeFile("newCost.dot");
		   
		   Graph g1 = g;
		   
		   g1.swapEdge(chemin1, s);
		   g1.writeFile("graphInv.dot");
		   
		   //recherche du second chemin
		   ArrayList<Integer> chemin2 = d.rechercheChemin(g1, 0, g1.vertices() - 1);
		   Collections.reverse(chemin2);
		   
		   
		   //recuperation du chemin le plus court
		   //chemin2 = this.PlusCourtChemin(chemin2);
		   for(Integer i : chemin2){
			   System.out.println(i);
		   }
		
		return null;
	}
}
