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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VueHaut extends JPanel implements Observer {

	private Modele m;
	
	public VueHaut(Modele mod) {
		super();
		m = mod;
		m.addObserver(this);
		
		// Bouton choix de l'image pgm
		JButton ouvrirFichier = new JButton("Choisir l'image pgm ou ppm");
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
				
				FileFilter filter = new FileNameExtensionFilter("PGM and PPM files", new String[] {"pgm", "ppm"});
				dialogue.setFileFilter(filter);
				dialogue.addChoosableFileFilter(filter);
				
				int returnVal = dialogue.showOpenDialog(null);
				if(dialogue.getSelectedFile() != null && returnVal == JFileChooser.APPROVE_OPTION) {
					String file = dialogue.getSelectedFile().getPath();
					if(file.endsWith(".ppm"))
						m.setColor(true);
					m.setFile(file);
				}
			}
		});
		
		add(ouvrirFichier);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}

