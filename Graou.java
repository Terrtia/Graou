package graou;

import graou.vues.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Graou extends JFrame {
	
	
	public Graou() {
		super("Graou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1200,500));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Création du Modele
		Modele m = new Modele();
		
		// Création des vues
		VueHaut vHaut = new VueHaut(m);
		VueImages vImage = new VueImages(m);
		VueActions vActions = new VueActions(m);
		
		add(vHaut, BorderLayout.NORTH);
		add(vImage, BorderLayout.CENTER);
		add(vActions, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Graou();
	}
	
}
