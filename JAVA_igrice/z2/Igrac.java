package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Igrac extends Figura {

	public Igrac(Polje p) {
		super(p);
	}

	@Override
	public void iscrtaj() {
		Graphics g = mojePolje.getGraphics();
		g.setColor(Color.RED);	
		g.drawLine(0,mojePolje.getHeight()/2 , mojePolje.getWidth(), mojePolje.getHeight()/2);
		g.drawLine(mojePolje.getWidth()/2, mojePolje.getHeight(), mojePolje.getWidth()/2, 0);
	}

}
