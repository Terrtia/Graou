package graou.vues;

import graou.Modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class VueHaut extends JPanel implements Observer {

	private Modele m;
	
	public VueHaut(Modele mod) {
		super();
		m = mod;
		m.addObserver(this);
		
		// Bouton choix de l'image pgm
		JButton ouvrirFichier = new JButton("Choisir l'image pgm");
		ouvrirFichier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				File repertoireCourant = null;
		        try {
		            // obtention d'un objet File qui désigne le répertoire courant
		            repertoireCourant = new File(".").getCanonicalFile();
		        } catch(IOException e) {}
				
				JFileChooser dialogue = new JFileChooser(repertoireCourant);
				dialogue.showOpenDialog(null);
				if(dialogue.getSelectedFile() != null)
					m.setFile(dialogue.getSelectedFile().getPath());
			}
		});
		
		add(ouvrirFichier);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
