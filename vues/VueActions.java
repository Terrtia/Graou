package graou.vues;

import graou.Modele;
import graou.SeamCarving;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class VueActions extends JPanel implements Observer {

	private Modele m;
	private JButton delV;
	private JButton delH;
	private JButton addV;
	private JButton addH;
	private Thread t;
	private JProgressBar bar;
	
	public VueActions(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		m = mod;
		m.addObserver(this);
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
				
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
							new Thread() 
							{
								@Override
								public void run()
								{
									m.deleteColumns((int)js.getValue());
								}
							}.start();
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
						if((int) js.getValue() <= m.getHeight()) {
							jd.dispose();
							new Thread() 
							{
								@Override
								public void run()
								{
									m.deleteLines((int)js.getValue());
								}
							}.start();
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
				dialPan.add(new JLabel("Nombre de lignes à supprimer :"));
				dialPan.add(js);
				dialPan.add(ok);
				dialPan.add(annuler);
				jd.setContentPane(dialPan);
				jd.pack();
				jd.setVisible(true);				
			}
		});
		
		addV = new JButton("Ajout de colonnes");
		addV.setEnabled(false);
		
		addV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog jd = new JDialog(new JFrame(), "Nombre de colonnes à ajouter", true);
				jd.setPreferredSize(new Dimension(300,100));
				JPanel dialPan = new JPanel();
				SpinnerModel sm = new SpinnerNumberModel(0, 0, m.getWidth(), 1);
				final JSpinner js = new JSpinner(sm);
				final JButton ok = new JButton("Valider");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							jd.dispose();
							new Thread() 
							{
								@Override
								public void run()
								{
									m.addColumns((int)js.getValue());
								}
							}.start();
						
					}
				});
				final JButton annuler = new JButton("Annuler");
				annuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
					}
				});
				dialPan.add(new JLabel("Nombre de colonnes à ajouter :"));
				dialPan.add(js);
				dialPan.add(ok);
				dialPan.add(annuler);
				jd.setContentPane(dialPan);
				jd.pack();
				jd.setVisible(true);				
			}
		});
		
		addH = new JButton("Ajout de lignes");
		addH.setEnabled(false);
		
		addH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog jd = new JDialog(new JFrame(), "Nombre de lignes à ajouter", true);
				jd.setPreferredSize(new Dimension(300,100));
				JPanel dialPan = new JPanel();
				SpinnerModel sm = new SpinnerNumberModel(0, 0, m.getHeight(), 1);
				final JSpinner js = new JSpinner(sm);
				final JButton ok = new JButton("Valider");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							jd.dispose();
							new Thread() 
							{
								@Override
								public void run()
								{
									m.addLines((int)js.getValue());
								}
							}.start();
						
					}
				});
				final JButton annuler = new JButton("Annuler");
				annuler.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
					}
				});
				dialPan.add(new JLabel("Nombre de lignes à ajouter :"));
				dialPan.add(js);
				dialPan.add(ok);
				dialPan.add(annuler);
				jd.setContentPane(dialPan);
				jd.pack();
				jd.setVisible(true);				
			}
		});
		
		
		buttons.add(delV);
		buttons.add(delH);
		buttons.add(addV);
		buttons.add(addH);
		
		
		t = new Thread(new Traitement());
		bar  = new JProgressBar();
		bar.setMaximum(100);
	    bar.setMinimum(0);
	    bar.setStringPainted(true);
    
	    add(bar);
	       
	    t.start();      
	    
	    add(buttons);
	    
	    this.setVisible(true);      
	}

	class Traitement implements Runnable{   
	    public void run(){
	      int v = SeamCarving.progressValue;;
	      while(v <= 100) {
	    	  v = SeamCarving.progressValue;
	    	  bar.setValue(v);
	    	  repaint();
	      }
	    }
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		delV.setEnabled(true);
		delH.setEnabled(true);
		addV.setEnabled(true);
		addH.setEnabled(true);
	}

}
