package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Krtica extends Zivotinja {

	
	public Krtica(Rupa mojaRupa) {
		super(mojaRupa);
	}

	@Override
	public void udarenaZivotinja() {
		mojaRupa.trajnoZaustavi();
	}

	@Override
	public void iscrtaj(Graphics g ,Rectangle r) {
		g.setColor(Color.GRAY);
		g.fillOval((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	}

	@Override
	public void pobeglaZivotinja() {
		if (mojaRupa.mojaBasta.ziva())mojaRupa.mojaBasta.kradiPovrce();
	}

	
}
