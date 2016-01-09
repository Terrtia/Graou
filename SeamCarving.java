package graou;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Driver;
import java.util.Scanner;

/**
 * Classe permettant de lire et créer des fichiers pgm
 *
 */

public class SeamCarving {

	
	private String filename;
	private String magic;
	private int width;
	private int height;
	private int[][] image;
	
	public SeamCarving() {

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
            int maxVal = s.nextInt();
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
		   
            return image;
        }
		
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            
            //DEBUG
            System.err.println("lecture du fichier " + filename + " impossible");
            
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
		   
		   s = "255\n";
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
		   System.out.println("ecriture du fichier " + filename + " reussite");
		   
	   } catch (IOException e) {
		   e.printStackTrace(System.err);
		   
		   //DEBUG
		   System.err.println("ecriture du fichier "+ filename + " impossible");
	   }
	   
   }
   
   
   public static void main(String[] args) {
	   SeamCarving sc = new SeamCarving();
	   
	   int[][] image = sc.readpgm("graou/t.pgm");

	   sc.writepgm(image, "copy.pgm");
	}

   
}
