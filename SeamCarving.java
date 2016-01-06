package graou;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 * Classe permettant de lire et créer des fichiers pgm
 *
 */

public class SeamCarving {

	
	/**
	 * Lit un fichier pgm
	 * @param fn - nom du fichier à lire
	 * @return un tableau à 2 dimensions contenant la valeur en niveaux de gris de chaque pixel de l'image (0 - 255)
	 */
   public static int[][] readpgm(String fn)
	 {		
        try {
            InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
            BufferedReader d = new BufferedReader(new InputStreamReader(f));
            String magic = d.readLine();
            String line = d.readLine();
		   while (line.startsWith("#")) {
			  line = d.readLine();
		   }
		   Scanner s = new Scanner(line);
		   int width = s.nextInt();
		   int height = s.nextInt();		   
		   line = d.readLine();
		   s = new Scanner(line);
		   int maxVal = s.nextInt();
		   int[][] im = new int[height][width];
		   s = new Scanner(d);
		   int count = 0;
		   while (count < height*width) {
			  im[count / width][count % width] = s.nextInt();
			  count++;
		   }
		   return im;
        }
		
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            return null;
        }
    }
   
   /**
    * Crée une image pgm
    * @param image - tableau à 2 dimensions représentant l'image
    * @param filename - nom du fichier à créer
    */
   public void writepgm(int[][] image, String filename) {
	   
   }
   
   

   
}
