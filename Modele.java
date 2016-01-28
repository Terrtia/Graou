package graou;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class Modele extends Observable {

	private String file;
	private String fileRes;
	private SeamCarving sc;
	private int width;
	private int height;
	
	
	public Modele() {
		sc = new SeamCarving();
		width = 0;
		height = 0;
		file = null;
		fileRes = null;
	}
	
	public void setFile(String s) {
		file = s;
		sc.readpgm(file);
		width = sc.getWidth();
		height = sc.getHeight();
		setChanged();
		notifyObservers();
	}
	
	public String getFile() {
		return file;
	}
	
	public String getFileRes() {
		return fileRes;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void deleteColumns(int nb) {
		sc.deleteNColumns(nb);
		save("res.pgm");
		String repertoireCourant;
		try {
			repertoireCourant = new File(".").getCanonicalFile().getPath();
			fileRes = repertoireCourant + "/res.pgm";
			sc.readpgm(file);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteLines(int nb) {
		sc.deleteNLines(nb);
		save("res.pgm");
		String repertoireCourant;
		try {
			repertoireCourant = new File(".").getCanonicalFile().getPath();
			fileRes = repertoireCourant + "/res.pgm";
			sc.readpgm(file);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addColumns(int nb) {
		sc.addNColumns(nb);
		save("res.pgm");
		String repertoireCourant;
		try {
			repertoireCourant = new File(".").getCanonicalFile().getPath();
			fileRes = repertoireCourant + "/res.pgm";
			sc.readpgm(file);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addLines(int nb) {
		sc.addNLines(nb);
		save("res.pgm");
		String repertoireCourant;
		try {
			repertoireCourant = new File(".").getCanonicalFile().getPath();
			fileRes = repertoireCourant + "/res.pgm";
			sc.readpgm(file);
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(String name) {
		sc.writepgm(sc.getImage(), name);
	}
	
}
