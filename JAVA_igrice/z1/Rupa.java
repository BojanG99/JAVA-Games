package igrica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rupa extends Canvas implements Runnable{
	protected Basta mojaBasta;
	private Zivotinja mojaZivotinja;
	private Thread nit;
	private int brojKoraka , trenutniBroj;

	public Rupa(Basta mojaBasta) {
		super();
		Rupa r = this;
		
		this.setBackground(new Color(139 , 69 , 19));
		this.mojaBasta = mojaBasta;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				r.zgaziRupu();
			}
		});
	}

	public Zivotinja getMojaZivotinja() {
		return mojaZivotinja;
	}

	public void setMojaZivotinja(Zivotinja mojaZivotinja) {
		this.mojaZivotinja = mojaZivotinja;
	}
	
	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
	}

	public void zgaziRupu() {
		if (mojaZivotinja != null) mojaZivotinja.udarenaZivotinja();
	}
	
	@Override
	public void paint(Graphics g) {
	//	g.setColor(new Color(139 , 69 , 19));
	//	g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (mojaZivotinja != null) mojaZivotinja.paint(this , trenutniBroj);
	}
	
	public synchronized void stvori() {
		nit = new Thread(this);
	}
	
	public synchronized void pokreni() {
		nit.start();
	}
	
	public synchronized void trajnoZaustavi() {
		mojaZivotinja = null;
		mojaBasta.obavesti();
		nit.interrupt();
	}
	
	public synchronized boolean pokrenuta() {
		return nit.isAlive();
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 1 ; i <= brojKoraka;i++) {
					trenutniBroj = i;
					repaint();
					Thread.sleep(100);
				}
				
				Thread.sleep(2000);
				mojaZivotinja.pobeglaZivotinja();
				trajnoZaustavi();
			}
		}
		catch(InterruptedException e) {
			
		}
		repaint();
	}
	
	
}
