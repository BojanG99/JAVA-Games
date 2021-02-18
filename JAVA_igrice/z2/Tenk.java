package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tenk extends Figura implements Runnable {
	
	public static Random rd = new Random();

//	public enum Smer{ LEVO , GORE , DESNO , DOLE};
	private Thread nit = new Thread(this);
	
	public Tenk(Polje p) {
		super(p);
	}

	public synchronized void pokreni() {
		nit.start();
	}
	
	public synchronized void zaustavi() {
		nit.interrupt();
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(500);
				int stop = 0;
				boolean levo = false , desno = false , gore = false , dole = false;
		loop:	while (stop != 10) {
					int rand = rd.nextInt(4) + 1;
					switch(rand) {
					case 1: // gore
						if (this.mojePolje.getPoljeUdaljeno(-1, 0) != null && this.mojePolje.getPoljeUdaljeno(-1, 0).DaLiSeFiguraMozeNalaziti() ) {
								this.pomeri(this.mojePolje.getPoljeUdaljeno(-1, 0));
								mojePolje.getPoljeUdaljeno(1, 0).repaint();
								break loop;
						}
						if (!gore) { 
							stop += 1;
							gore = true;
						}
						break;
						
					case 2: // levo
						if (this.mojePolje.getPoljeUdaljeno(0, -1) != null && this.mojePolje.getPoljeUdaljeno(0, -1).DaLiSeFiguraMozeNalaziti() ) {
							this.pomeri(this.mojePolje.getPoljeUdaljeno(0, -1));
							mojePolje.getPoljeUdaljeno(0, 1).repaint();
							break loop;
					}
					
					if (!levo) { 
						stop += 2;
						levo = true;
					}
					break;
						
					case 3: // dole
						if (this.mojePolje.getPoljeUdaljeno(1, 0) != null && this.mojePolje.getPoljeUdaljeno(1, 0).DaLiSeFiguraMozeNalaziti() ) {
							this.pomeri(this.mojePolje.getPoljeUdaljeno(1, 0));
							mojePolje.getPoljeUdaljeno(-1, 0).repaint();
							break loop;
					}
						if (!dole) { 
							stop += 3;
							dole = true;
						}
					break; 
						
					case 4: // desno
						if (this.mojePolje.getPoljeUdaljeno(0, 1) != null && this.mojePolje.getPoljeUdaljeno(0, 1).DaLiSeFiguraMozeNalaziti() ) {
							this.pomeri(this.mojePolje.getPoljeUdaljeno(0, 1));
							mojePolje.getPoljeUdaljeno(0, -1).repaint();
							break loop;
					}
					
					if (!desno) { 
						stop += 4;
						desno = true;
					}
					break;
						
						
					}
				}
			}
		}
		catch (InterruptedException e)
		{
			
		}
	}

	@Override
	public void iscrtaj() {
		Graphics g = mojePolje.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, mojePolje.getWidth(), mojePolje.getHeight());
		g.drawLine(0, mojePolje.getHeight(), mojePolje.getWidth(), 0);
	}

}
