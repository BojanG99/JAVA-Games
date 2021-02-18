package igrica;

import java.awt.Color;
import java.awt.Graphics;

public class Novcic extends Figura {

	
	public Novcic(Polje p) {
		super(p);
	}

	@Override
	public void iscrtaj() {
		Graphics g = mojePolje.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillOval(mojePolje.getWidth()/4, mojePolje.getHeight()/4, mojePolje.getWidth()/2, mojePolje.getHeight()/2);
	}

}
