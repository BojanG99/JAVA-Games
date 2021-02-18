package igrica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Scena extends Canvas implements Runnable{
	
	public static double pomerajIgraca = 10;
	public static Random rd = new Random();
	
	private Igra mojaIgra;
	private Thread nit = new Thread(this);
	private Igrac mojIgrac;
	private ArrayList<KruznaFigura> prepreke= new ArrayList<KruznaFigura>();
	
	public Scena(Igra igra) {
		mojaIgra = igra;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					mojIgrac.pomeri(0-mojIgrac.brzina);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					mojIgrac.pomeri(mojIgrac.brzina);
				}
			}
		});
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(60);
			
				generisiBalon();
				synchronized (nit) {
					
					for (int i = 0 ; i < prepreke.size();i++)
						prepreke.get(i).obavestiFiguruProtekloVreme();
					
					mojIgrac.obavestiFiguruProtekloVreme();
				
			
						for (int i = 0 ; i < prepreke.size()-1 ;i++) {
							for (int j = i+1 ; j < prepreke.size();j++) {
								if (prepreke.get(i).preklapa(prepreke.get(j))) {
									prepreke.get(i).obavestiFiguruSudarilaSe();
									prepreke.get(j).obavestiFiguruSudarilaSe();
								}
							}
							if (prepreke.get(i).preklapa(mojIgrac)) {
								prepreke.get(i).obavestiFiguruSudarilaSe();
								mojIgrac.obavestiFiguruSudarilaSe();
							}
						}
					
					}
				repaint();
				}

			}
		catch (InterruptedException e) {
			
		}
	}

	private KruznaFigura generisiBalon() { // ispod 500 visine
		int verovatnoca = rd.nextInt(10);
		if (verovatnoca < 1) {
			int x = rd.nextInt(mojaIgra.getWidth() - 40) + 20;  //, y = rd.nextInt(mojaIgra.getHeight() - 200);
			
			Balon b = new Balon(new Vektor(x , 35), Color.RED , 10 , pomerajIgraca , this); // brzina je 0
			this.ubaci(b);
			return b;
		}
		
		return null;
	}
	public synchronized void izbaci(KruznaFigura kruznaFigura) {
	//	System.out.println("Izbacio sam figuru");
		prepreke.remove(kruznaFigura);
	}
	
	public synchronized void ubaci(KruznaFigura kruznaFigura) {
		prepreke.add(kruznaFigura);
	}

	public synchronized void trajnoZaustavi() {
		nit.interrupt();	
	}
	
	public void pokreni() {
		mojIgrac = new Igrac(new Vektor(mojaIgra.getWidth() /2 , mojaIgra.getHeight()-100), 15 , pomerajIgraca , this);
		nit.start();	
	}
	
	@Override
	public void paint(Graphics g) {
		for (KruznaFigura k : prepreke)
			k.iscrtaj(this);
		
		mojIgrac.iscrtaj(this);
		
	}

}
