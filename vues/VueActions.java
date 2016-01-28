package graou.vues;

import graou.Modele;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class VueActions extends JPanel implements Observer {

	private Modele m;
	private JButton delV;
	private JButton delH;
	
	public VueActions(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new FlowLayout());
		m = mod;
		m.addObserver(this);
		
		delV = new JButton("Suppression de colonnes");
		delV.setEnabled(false);
		
		delV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog jd = new JDialog(new JFrame(), "Nombre de colonnes à supprimer", true);
				jd.setPreferredSize(new Dimension(300,100));
				JPanel dialPan = new JPanel();
				SpinnerModel sm = new SpinnerNumberModel(0, 0, m.getWidth(), 1);
				final JSpinner js = new JSpinner(sm);
				final JButton ok = new JButton("Valider");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if((int)js.getValue() <= m.getWidth()) {
							jd.dispose();
							m.deleteColumns((int)js.getValue());
						}
						
					}
				});
				final JButton annuler = new JButton("Annuler");
				annuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
					}
				});
				dialPan.add(new JLabel("Nombre de colonnes à supprimer :"));
				dialPan.add(js);
				dialPan.add(ok);
				dialPan.add(annuler);
				jd.setContentPane(dialPan);
				jd.pack();
				jd.setVisible(true);				
			}
		});
		
		delH = new JButton("Suppression de lignes");
		delH.setEnabled(false);
		
		delH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog jd = new JDialog(new JFrame(), "Nombre de lignes à supprimer", true);
				jd.setPreferredSize(new Dimension(300,100));
				JPanel dialPan = new JPanel();
				SpinnerModel sm = new SpinnerNumberModel(0, 0, m.getHeight(), 1);
				final JSpinner js = new JSpinner(sm);
				final JButton ok = new JButton("Valider");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
						m.deleteLines((int)js.getValue());
					}
				});
				final JButton annuler = new JButton("Annuler");
				annuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
					}
				});
				dialPan.add(new JLabel("Nombre de lignes à supprimer :"));
				dialPan.add(js);
				dialPan.add(ok);
				dialPan.add(annuler);
				jd.setContentPane(dialPan);
				jd.pack();
				jd.setVisible(true);				
			}
		});
		
		
		add(delV);
		add(delH);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		delV.setEnabled(true);
		delH.setEnabled(true);
	}

}
