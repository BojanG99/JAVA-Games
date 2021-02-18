package igrica;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Mreza extends Panel implements Runnable {
	
	public static Random rd = new Random();
	
	private int rezim; // 0-izmena , 1 - igranja
	public int vrstaPolja ; // 0-trava , 1 - zid
	private int poeni;
	private int brojNovcica , currBrojNovcica;
	

	private ArrayList<Novcic> novcici = new ArrayList<>();
	private ArrayList<Tenk> tenkovi = new ArrayList<>();
	private Igrac mojIgrac;
	private Polje[][] tabla;
	private Thread nit = new Thread(this);
	private Igra mojaIgra;
	
	public Polje[][] getTabla() {
		return tabla;
	}
	
	public ArrayList<Novcic> getNovcici() {
		return novcici;
	}

	public ArrayList<Tenk> getTenkovi() {
		return tenkovi;
	}

	public Igrac getMojIgrac() {
		return mojIgrac;
	}

	public Mreza(Igra igra) {
		this(17 , igra);
	}
	
	public void setPolje(int y , int x , Polje p) {
		tabla[y][x] = p;
	}

	public Mreza(int vel , Igra igra) {
		this.setLayout(new GridLayout(vel , vel));
		mojaIgra= igra;
		tabla = new Polje[vel][];
		for (int i = 0 ; i < vel;i++ ) {
			tabla[i] = new Polje[vel];
			for (int j = 0 ; j < vel;j++) {
				int rand = rd.nextInt(5);
				if (rand < 4) {
					tabla[i][j] = new Trava(this);
				}
				else {
					tabla[i][j] = new Zid(this);
				}
				this.add(tabla[i][j]);
			}
		}
		dodajOsluskivace();
		this.setVisible(true);
	}
	
	private void dodajOsluskivace() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'a') {
					if (mojIgrac != null) {
						mojIgrac.pomeri(mojIgrac.mojePolje.getPoljeUdaljeno(0, -1));
						mojIgrac.mojePolje.getPoljeUdaljeno(0, 1).repaint();
					}
				}
				if (e.getKeyChar() == 'w') {
					if (mojIgrac != null) {
						mojIgrac.pomeri(mojIgrac.mojePolje.getPoljeUdaljeno(-1, 0));
						mojIgrac.mojePolje.getPoljeUdaljeno(1, 0).repaint();
					}
				}
				if (e.getKeyChar() == 'd') {
					if (mojIgrac != null) {
						mojIgrac.pomeri(mojIgrac.mojePolje.getPoljeUdaljeno(0, 1));
						mojIgrac.mojePolje.getPoljeUdaljeno(0, -1).repaint();
					}
				}
				if (e.getKeyChar() == 's') {
					if (mojIgrac != null) {
						mojIgrac.pomeri(mojIgrac.mojePolje.getPoljeUdaljeno(1, 0));
						mojIgrac.mojePolje.getPoljeUdaljeno(-1, 0).repaint();
					}
				}
				repaint();
			}
		});
		
	}
	
	public static void zameniPolje(Polje p) {
		Polje po = null;
		if (p instanceof Zid) 
			po = new Trava(p.mojaMreza);
		else 
			po = new Zid(p.mojaMreza);
				
			int[] kord = p.getPozicijuPolja();
			p.mojaMreza.setPolje(kord[0], kord[1], po);
			p.mojaMreza.remove(kord[0]*p.mojaMreza.getTabla().length + kord[1]);
			p.mojaMreza.add(po ,kord[0]*p.mojaMreza.getTabla().length + kord[1] );
			p.mojaMreza.revalidate();
		}

	
	public int getRezim() {
		return rezim;
	}
	
	public int getPoeni() {
		return poeni;
	}

	public void setRezim(int rezim) {
		this.rezim = rezim;
	}

	public void setBrojNovcica(int br) {
		brojNovcica = br;
	}
	
	public int getBrojNovcica() {
		return brojNovcica;
	}
	
	public void setVrstaPolja(int vrstaPolja) {
		this.vrstaPolja = vrstaPolja;
	}
	
	public void paint(Graphics g) {
		
		for (int i = 0 ; i < tenkovi.size();i++)
			tenkovi.get(i).iscrtaj();
		
		for (int i = 0 ; i < novcici.size();i++)
			novcici.get(i).iscrtaj();
		
		if (mojIgrac != null) mojIgrac.iscrtaj(); 
		
/*		for (int i = 0 ; i < tabla.length;i++)
			for (int j = 0 ; j < tabla[i].length;j++)
				tabla[i][j].paint(g);*/
		
	}
	
	public synchronized void iscrajPoPlatnu() {
		for (int i = 0 ; i < tenkovi.size();i++)
			tenkovi.get(i).iscrtaj();
		
		for (int i = 0 ; i < novcici.size();i++)
			novcici.get(i).iscrtaj();
		
		if (mojIgrac != null) mojIgrac.iscrtaj();
	}

	public synchronized void inicijalizuj(int br) {
		currBrojNovcica = brojNovcica;
		this.izbaciSveFigure();
		int cnt = 0;
		while (cnt != br) { // postavi novcice
			int x = rd.nextInt(tabla.length) , y = rd.nextInt(tabla.length);
			
			if (tabla[y][x].DaLiSeFiguraMozeNalaziti()) {
				boolean fleg = false;
				for (Novcic n : novcici) {
					if (tabla[y][x] == n.mojePolje) {
						fleg = true;
						break;
					}
				}
				if (!fleg) {
					novcici.add(new Novcic(tabla[y][x]));
					cnt++;
				}
			}
		}
		
		cnt = 0; br /=3;
		while (cnt != br) { // postavi novcice
			int x = rd.nextInt(tabla.length) , y = rd.nextInt(tabla.length);
			
			if (tabla[y][x].DaLiSeFiguraMozeNalaziti()) {
				boolean fleg = false;
				for (Tenk t : tenkovi) {
					if (tabla[y][x] == t.mojePolje) {
						fleg = true;
						break;
					}
				}
				if (!fleg) {
					tenkovi.add(new Tenk(tabla[y][x]));
					cnt++;
				}
			}
		}
		
		cnt = 0; br = 1;
		while (cnt != br) { // postavi novcice
			int x = rd.nextInt(tabla.length) , y = rd.nextInt(tabla.length);
			
			if (tabla[y][x].DaLiSeFiguraMozeNalaziti()) {
				boolean fleg = false;
				for (Novcic n : novcici) {
					if (tabla[y][x]== n.mojePolje) {
						fleg = true;
						break;
					}
				}
				
				for (Tenk t : tenkovi) {
					if (tabla[y][x]== t.mojePolje) {
						fleg = true;
						break;
					}
				}
				
				if (!fleg) {
					mojIgrac = new Igrac(tabla[y][x]);
					cnt++;
				}
			}
		}
		
		repaint();
	}
	
	@Override
	public void run() {
		try {
		while (!Thread.interrupted()) {
				Thread.sleep(40);
				this.repaint();
			
			synchronized(nit) {
				for (int i = 0 ; i < novcici.size();i++) {
					if (novcici.get(i).equals(mojIgrac)) {
						novcici.remove(novcici.get(i));
						poeni++;
						currBrojNovcica--;
						mojaIgra.postaviBrojPoena(poeni);
						break;
					}
				}
				if (currBrojNovcica == 0) this.trajnoZaustavi();
				for (int i = 0 ; i < tenkovi.size();i++) {
					if (tenkovi.get(i).equals(mojIgrac)) {
						mojIgrac = null;
						trajnoZaustavi();
					}
				}
			}
		}
		
		}
		catch (InterruptedException e) {}
	}

	public synchronized void trajnoZaustavi() {
			for (Tenk t : tenkovi)
				t.zaustavi();
			nit.interrupt();
			izbaciSveFigure();
	}

	public void izbaciSveFigure() {
		for (int i = 0 ; i < tenkovi.size();i++)
			tenkovi.get(i).mojePolje.repaint();
		tenkovi.clear();
		
		for (int i = 0 ; i < novcici.size();i++)
			novcici.get(i).mojePolje.repaint();
		novcici.clear();
		
		if(mojIgrac != null)mojIgrac.mojePolje.repaint();
		mojIgrac = null;
	}

	public synchronized void zapocni() {
		nit = new Thread(this);
		
		for (Tenk t : tenkovi)
			t.pokreni();
		
		nit.start();
		
	}

	
}
