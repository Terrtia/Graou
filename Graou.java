package graou;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Graou extends JFrame {
	
	public Graou() {
		super("Graou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400,300));

		// Création du Modele
		Modele modele = new Modele();
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Graou();
	}
	
}
