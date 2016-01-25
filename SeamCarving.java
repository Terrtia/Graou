package graou;

import graou.algo.Dijkstra;
import graou.graph.Edge;
import graou.graph.Graph;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de lire et créer des fichiers pgm
 *
 */

public class SeamCarving {

	
	private String filename;
	private String magic;
	private int maxVal;
	private int width;
	private int height;
	private int[][] image;
	private int[][] interest;
	
	public SeamCarving() {

	}
	
	/**
	 * @param itr - tableau contenant les facteurs d'intérêt de l'image
	 * @return un graphe représentant ce tableau
	 */
	public Graph verticalToGraph(int[][] itr) {
		int nbSommets = width * height + 2;
		int i, j;
		Graph g = new Graph(nbSommets);
		
		// sommet tout en haut
		for (j = 0; j < width ; j++)					
			  g.addEdge(new Edge(0, j+1, 0));
		
		for (i = 0; i < height-1; i++)
		{
			  for (j = 0; j < width ; j++)
			  {
				  // arête vers la gauche
				  if(j > 0) {
					  g.addEdge(new Edge(width*i +j+1, width*(i+1)+(j-1)+1, itr[i][j]));
				  }
				  // arête vers la droite
				  if(j < width-1) {
					  g.addEdge(new Edge(width*i +j+1, width*(i+1)+(j+1)+1, itr[i][j]));
				  }
				  // arête centrale
				  g.addEdge(new Edge(width*i +j+1, width*(i+1)+j+1, itr[i][j]));
			  }
		}
		
		// sommet tout en bas
		for (j = 0; j < width ; j++)		  
			  g.addEdge(new Edge(width*(height-1)+j+1, height*width+1, itr[i][j]));
		
		
		return g;
	}
	
	/**
	 * @param itr - tableau contenant les facteurs d'intérêt de l'image
	 * @return un graphe représentant ce tableau
	 */
	public Graph horizontalToGraph(int[][] itr) {
		int nbSommets = width * height + 2;
		int i, j;
		Graph g = new Graph(nbSommets);
		
		// sommet tout en haut
		for (j = 0; j < height ; j++)					
			  g.addEdge(new Edge(0, j+1, 0));
		
		for (i = 0; i < width-1; i++)
		{
			  for (j = 0; j < height ; j++)
			  {
				  // arête vers le haut
				  if(j > 0) {
					  g.addEdge(new Edge(height*i+j+1, height*(i+1)+j-1+1, itr[j][i]));
				  }
				  // arête vers le bas
				  if(j < height-1) {
					  g.addEdge(new Edge(height*i+j+1, height*(i+1)+j+1+1, itr[j][i]));
				  }
				  // arête centrale
				  g.addEdge(new Edge(height*i+j+1, height*(i+1)+j+1, itr[j][i]));
			  }
		}
		
		// sommet tout en bas
		for (j = 0; j < height ; j++)		  
			  g.addEdge(new Edge(height*(width-1)+j+1, height*width+1, itr[j][i]));
		
		
		return g;
	}
	
	/**
	 * Lit un fichier pgm
	 * @param fn - nom du fichier à lire
	 * @return un tableau à 2 dimensions contenant la valeur en niveaux de gris de chaque pixel de l'image (0 - 255)
	 */
   public int[][] readpgm(String filename)
	 {		
        try {
        	/*Image dans le meme package:
        	InputStream f = Driver.class.getResourceAsStream("t.pgm");*/
        	
        	InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            BufferedReader d = new BufferedReader(new InputStreamReader(f));
            
            this.magic = d.readLine() + "\n";
            String line = d.readLine();
            while (line.startsWith("#")) {
            	line = d.readLine();
            }
            Scanner s = new Scanner(line);
            this.width = s.nextInt();
            this.height = s.nextInt();
            s.close();
            line = d.readLine();
            s = new Scanner(line);
            this.maxVal = s.nextInt();
            s.close();
            this.image = new int[height][width];
            s = new Scanner(d);
            int count = 0;
            while (count < height*width) {
            	image[count / width][count % width] = s.nextInt();
            	count++;
            }
            s.close();
            f.close();
            
            //DEBUG
 		   	System.out.println("lecture du fichier " + filename + " reussite\n");
 		   
            return image;
        }
		
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            
            //DEBUG
            System.err.println("lecture du fichier " + filename + " impossible\n");
            
            return null;
        }
    }
   
   /**
    * Crée une image pgm
    * @param image - tableau à 2 dimensions représentant les pixels de l'image
    * @param filename - nom du fichier à créer
    */
   public void writepgm(int[][] image, String filename) {
	   
	   try {
		   String s;
		   FileOutputStream fos = new FileOutputStream(filename);
		   
		   fos.write(magic.getBytes());
		   
		   s = "#write by Graou\n";
		   fos.write(s.getBytes());
		   
		   s = width + " " + height + "\n";
		   fos.write(s.getBytes());
		   
		   s = String.valueOf(this.maxVal) + "\n";
		   fos.write(s.getBytes());
		   
		   for(int i =0; i<height; i++){
			   for(int j=0; j<width; j++){
				   s = String.valueOf(image[i][j]) + " ";
				   fos.write(s.getBytes());
			   }
			   s = "\n";
			   fos.write(s.getBytes());
		   }
		   
		   fos.close();
		   
		   //DEBUG
		   System.out.println("ecriture du fichier " + filename + " reussite\n");
		   
	   } catch (IOException e) {
		   e.printStackTrace(System.err);
		   
		   //DEBUG
		   System.err.println("ecriture du fichier "+ filename + " impossible\n");
	   }
	   
   }
   
   /**
    * Calcul le facteur d'intérêt de chaque pixel
    * @param image - tableau à 2 dimensions représentant les pixels de l'image
    * @return un tableau à 2 dimensions contenant les facteurs d'intérêt de chaque pixel
    */
   public int[][] verticalInterest (int[][] interest) {
	   int[][] interet = new int[height][width];
	   
	   int voisinGauche;
	   int voisinDroite;
	   int moyenne;
	   int res;
	   
	   for(int i =0; i<height; i++){
		   for(int j=0; j<width; j++){
			   
			   if(j == 0){
				   voisinDroite = this.image[i][j + 1];
				   res = this.image[i][j] - voisinDroite;
			   } else if(j == width - 1){
				   voisinGauche = this.image[i][j - 1];
				   res = this.image[i][j] - voisinGauche;
			   } else {
				   voisinGauche = this.image[i][j - 1];
				   voisinDroite = this.image[i][j + 1];
				   moyenne = (voisinDroite + voisinGauche) /2;
				   res = this.image[i][j] - moyenne;
			   }
			   
			   //valeur absolue
			   if(res >= 0){
				   interet[i][j] = res;
			   }else{
				   interet[i][j] = -res;
			   }
			   
		   }
	   }
	   
	   return interet;
   }
   
   
   /**
    * Calcul le facteur d'intérêt de chaque pixel
    * @param image - tableau à 2 dimensions représentant les pixels de l'image
    * @return un tableau à 2 dimensions contenant les facteurs d'intérêt de chaque pixel
    */
   public int[][] HorizontalInterest (int[][] interest) {
	   int[][] interet = new int[height][width];
	   
	   int voisinHaut;
	   int voisinBas;
	   int moyenne;
	   int res;
	   
	   for(int i =0; i<height; i++){
		   for(int j=0; j<width; j++){
			   
			   if(i == 0 && i != height - 1){
				   voisinBas = this.image[i + 1][j];
				   res = this.image[i][j] - voisinBas;
			   } else if(i == height - 1){
				   voisinHaut = this.image[i - 1][j];
				   res = this.image[i][j] - voisinHaut;
			   } else {
				   voisinHaut = this.image[i - 1][j];
				   voisinBas = this.image[i + 1][j];
				   moyenne = (voisinBas + voisinHaut) /2;
				   res = this.image[i][j] - moyenne;
			   }
			   
			   //valeur absolue
			   if(res >= 0){
				   interet[i][j] = res;
			   }else{
				   interet[i][j] = -res;
			   }
			   
		   }
	   }
	   
	   return interet;
   }
   
   public void deleteColumns() {
	   
	   
	   this.interest = this.verticalInterest(image);
	   int nbPixels = height * width;
	   
	   Dijkstra d = new Dijkstra();
	   Graph g = this.verticalToGraph(this.interest);
	  
	   ArrayList<Integer> res = d.rechercheChemin(g, 0, g.vertices() - 1);
	   
	   int[][] newImage = new int[height][width - 1];
	   
	   int line = height - 1;
	   int column;
	   boolean equals;
	   int pos;
	   
	   for(int l : res){
		   column = 0;
		   pos = l%this.width - 1;
		   equals = false;
		   
		   if(l != 0 && l != (nbPixels + 1) ){
			   while(column != this.width -1){
				   if(pos == column){
					   equals = true;
				   }
				   
				   if(equals == false){
					   newImage[line][column] = this.image[line][column];
				   } else if(equals == true){
					   if(column < width){
						   newImage[line][column] = this.image[line][column + 1];
					   }
				   } else {
					   System.err.println("Error 0x087A4E652");
				   } 
				   column++;
			   }
		   }
		   line--;
	   }
	   
	   this.image = newImage;
	   this.width = width - 1;   	   
   }
   
   public void deleteNColumns(int nb){
	   for(int i=0; i<nb; i++){
		   this.deleteColumns();
	   }
   }
   
   /**
    * Test
    */
   public static void main(String[] args) {
	   SeamCarving sc = new SeamCarving();
	   
	   /*test lecture/ecriture*/
	   //int[][] image = sc.readpgm("graou/t.pgm");
	   //sc.writepgm(image, "copy.pgm");
	    
	   
	   /*test facteur d'intérêt (ouvrir le fichier pour voir les facteurs) */ 
	   //int[][] image = sc.readpgm("graou/test.pgm");
	   //image = sc.HorizontalInterest(image);
	   

	   //Graph g = sc.verticalToGraph(image);
	   //g.writeFile("testV.dot");
	   
	   //g = sc.horizontalToGraph(image);
	   //g.writeFile("testH.dot");
	   
	   //Dijkstra d = new Dijkstra();
	   //ArrayList<Integer> res = d.rechercheChemin(g, 0, 13);
	   //res.writeFile("res.dot");
	   //System.out.println(res.toString());
	   
	   /* Suppression 1 colonnes */
	   /*sc.readpgm("graou/test.pgm");
	   sc.deleteNColumns(2);
	   sc.writepgm(sc.image, "suppr.pgm");*/
	   
	   /* Suppression 50 colonnes */
	   sc.readpgm("graou/t.pgm");
	   sc.deleteNColumns(50);
	   sc.writepgm(sc.image, "verDeletet.pgm");
	   
	}

   
}
