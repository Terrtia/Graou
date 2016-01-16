package graou;

import graou.graph.Edge;
import graou.algo.*;
import graou.graph.Graph;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
					  g.addEdge(new Edge(width*i+j+1, width*(i+1)+j-1+1, itr[i][j]));
				  }
				  // arête vers la droite
				  if(j < width-1) {
					  g.addEdge(new Edge(width*i+j+1, width*(i+1)+j+1+1, itr[i][j]));
				  }
				  // arête centrale
				  g.addEdge(new Edge(width*i+j+1, width*(i+1)+j+1, itr[i][j]));
			  }
		}
		
		// sommet tout en bas
		for (j = 0; j < width ; j++)		  
			  g.addEdge(new Edge(width*(height-1)+j+1, height*width+1, itr[i][j]));
		
		
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
		   
		   s = "#\n";
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
   public int[][] verticalInterest (int[][] image) {
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
    * Test
    */
   public static void main(String[] args) {
	   SeamCarving sc = new SeamCarving();
	   
	   /*test lecture/ecriture*/
	   //int[][] image = sc.readpgm("graou/t.pgm");
	   //sc.writepgm(image, "copy.pgm");
	    
	   
	   /*test facteur d'intérêt (ouvrir le fichier pour voir les facteurs) */ 
	   int[][] image = sc.readpgm("graou/test.pgm");
	   image = sc.verticalInterest(image);
	   
	   Graph g = sc.verticalToGraph(image);
	   g.writeFile("test3.dot");
	   Dijkstra d = new Dijkstra();
	   Graph res = d.rechercheChemin(g, 0, 13);
	   res.writeFile("res.dot");
	   
	   sc.writepgm(image, "Interetcopy.pgm");
	}

   
}
