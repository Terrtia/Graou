package graou.vues;

import graou.Modele;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueImages extends JPanel implements Observer {

	private JLabel imgSrc;
	private JLabel imgDest;
	private Modele m;
	
	public VueImages(Modele mod) {
		super();
		setLayout(new FlowLayout());
		//setPreferredSize(new Dimension(1200,400));
		m = mod;
		m.addObserver(this);
		imgSrc = new JLabel();
		imgDest = new JLabel();	
		add(imgSrc);
		add(imgDest);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(m.getFile() != null) {
			// load the image using JAI
			PlanarImage pgmImage = JAI.create("fileload", m.getFile());
			// create an ImageIcon from the image
			ImageIcon icon = new ImageIcon(pgmImage.getAsBufferedImage());
			
			imgSrc.setIcon(icon);
			
			if (m.getFileRes() != null) {
				pgmImage = JAI.create("fileload", m.getFileRes());
				icon = new ImageIcon(pgmImage.getAsBufferedImage());
				imgDest.setIcon(icon);
			}
		}

	}

}
