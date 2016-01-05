package graou;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Graou extends JFrame {
	
	public Graou() {
		super("Graou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Création du Modele
		Modele modele = new Modele();
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Graou();
	}
	
}
