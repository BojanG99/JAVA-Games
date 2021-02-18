package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Basta extends Panel implements Runnable {
	
	public static Random rd = new Random();
	
	Rupa[][] tabla;
	private int kolicinaPovrca = 100  , brojKoraka;
	private double intervalCekanja;
	private Thread nit = new Thread(this);
	
	public Basta(int vrs , int kol) {
		this.setBackground(Color.GREEN);
		this.setLayout(new GridLayout(vrs , kol , 15 , 15));
		tabla = new Rupa[vrs][];
		for (int i = 0 ; i < vrs;i++) {
			tabla[i] = new Rupa[kol];
			for (int j = 0 ; j < kol;j++) {
				tabla[i][j] = new Rupa(this);
				this.add(tabla[i][j]);
			}
		}
	}
	
	
	
	public void setIntervalCekanja(double intervalCekanja) {
		this.intervalCekanja = intervalCekanja;
	}



	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		for (int i = 0 ; i < tabla.length;i++)
			for (int j = 0 ; j < tabla[i].length;j++)
				tabla[i][j].setBrojKoraka(brojKoraka);
			
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0 ; i < tabla.length;i++)
			for (int j = 0 ; j < tabla[i].length;j++)
				tabla[i][j].paint(g);
	}

	@Override
	public void run() {
		try {
			
			while (!Thread.interrupted()) {
				
				int vr , kol;
				synchronized(nit) {
					while (true) {
						vr = rd.nextInt(tabla.length) ;
						kol = rd.nextInt(tabla[0].length);
						if (tabla[vr][kol].getMojaZivotinja() == null) {
							tabla[vr][kol].setMojaZivotinja(new Krtica(tabla[vr][kol]));
							break;
						}
					}
				}
				
				nit.join((int)intervalCekanja);
				synchronized (nit) {
					tabla[vr][kol].stvori();
					tabla[vr][kol].pokreni();
				}
				
				intervalCekanja *= 0.99;
				
			}
		}
		catch(InterruptedException e) {
			
		}

	}

	public void obavesti() {
		try {
			synchronized (nit) {
				nit.notify();
			}
		}
		catch (IllegalMonitorStateException e) {
			
		}
		
	}

	public synchronized void kradiPovrce() {
		kolicinaPovrca--;
		Igra.getIgra().postaviRezultat(kolicinaPovrca);
		if (kolicinaPovrca == 0) this.trajnoZaustavi();
	}
	
	public synchronized void pokreni() {
		nit.start();
	}
	
	public synchronized void trajnoZaustavi() {
		nit.interrupt();
	}
	
	public boolean ziva() {
		return nit.isAlive();
	}

	
}
