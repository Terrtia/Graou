package graou.graph;

public class Edge {
	
   private int from;
   private int to;
   private int cost;
   
   public Edge(int x, int y, int cost) {
		this.from = x;
		this.to = y;
		this.cost = cost;
   }
   
   /****************************** GET FUNCTION *******************/
   public int getFrom() {
	   return this.from;
   }
   
   public int getTo() {
	   return this.to;
   }
   
   public int getCost() {
	   return this.cost;
   }
   
}
