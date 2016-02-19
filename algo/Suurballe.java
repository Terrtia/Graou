package graou.algo;

import graou.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class Suurballe {

	public Suurballe(){
		
	}
	
	public ArrayList<Integer> rechercheChemin(Graph g, int s, int t, int[][] intereset) {
		
		g.writeFile("dep.dot");
		
		Dijkstra d = new Dijkstra();
		//recherche du premier chemin
		ArrayList<Integer> chemin1 = d.rechercheChemin(g, 0, g.vertices() - 1);
		
		g.calcMinCost(s);
		g.diffCost(s);
		
		g.writeFile("newCost.dot");
		
		Graph g1 = new Graph(g.vertices());
		
		g1 = g;
		   
		g1.swapEdge(chemin1, s);
		g1.writeFile("swap.dot");
		
		//recherche du second chemin
		ArrayList<Integer> chemin2 = d.rechercheChemin(g1, 0, g1.vertices() - 1);
		
		
		Collections.reverse(chemin2);
		
		
		chemin1 = this.SupprimerSommetInutile(chemin1);
		chemin2 = this.SupprimerSommetInutile(chemin2);
		
		chemin2 = this.SupprimerSommetEnDouble(chemin1, chemin2);
				
		chemin1.add(-1);
		for(Integer i : chemin2){
			chemin1.add(i);
		}
		
		return chemin1;
	}
	
	public ArrayList<Integer> SupprimerSommetInutile(ArrayList<Integer> chemin) {
		for(int i=0; i<chemin.size(); i++){
			if(i%2 != 0){
				if(i != chemin.size() - 1){
					chemin.remove(i);
				}
			}
		}
		
		return chemin;
	}
	
	public ArrayList<Integer> SupprimerSommetEnDouble(ArrayList<Integer> chemin1, ArrayList<Integer> chemin2) {
		
		for(int i=0; i<chemin2.size() - 1; i++){
			if(chemin1.contains(chemin2.get(i))){
				chemin2.remove(i);
			}
		}
		
		return chemin2;
	}
	
	private ArrayList<Integer> PlusCourtChemin(ArrayList<Integer> chemin2, int width) { //inutile desormais
		   int courant;
		   int precedent = -1;   
		   
		   for(int i=0; i< chemin2.size(); i++){
			   courant = chemin2.get(i);
			   
			   if(courant < precedent){
				   
				   if( (i+1) < chemin2.size() && (i-2) >= 0){
					   int suivant = chemin2.get(i + 1);
					   int origine = chemin2.get(i - 2);
					   int indexOrigine = i - 2;
					   	
					   if(origine + width == suivant){
						   ArrayList<Integer> temp = new ArrayList<>();
						   boolean egal = false;;
						   for(int j=0; j< chemin2.size(); j++){
							   if(chemin2.get(j) == origine){
								   egal = true;
							   }
							   if(!egal){
								   temp.add(chemin2.get(j));
							   } else {
								   if(j == indexOrigine){
									   temp.add(chemin2.get(j));
								   }
								   if(j > indexOrigine + 2){
									   temp.add(chemin2.get(j));
								   }
							   }
						   }
						   return this.PlusCourtChemin(temp,width);
					   }
				   }
			   }
			   precedent = chemin2.get(i);
		   }
		   return chemin2;
	   }
}
