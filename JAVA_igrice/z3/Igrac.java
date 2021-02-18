package igrica;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor centar, double r, double v, Scena s) {
		super(centar, Color.GREEN , r, v, s);
	}
	
	
	@Override
	public void pomeri() {}

	@Override
	public void obavestiFiguruSudarilaSe() {
		mojaScena.trajnoZaustavi();
	}
	
	public void pomeri(double dx) {
		if ((this.centar.getX() + dx + this.r) <= (double)this.mojaScena.getWidth() 
				&& (this.centar.getX() + dx - this.r) >= 0) this.centar.saberi(new Vektor(dx , 0));
	}

	@Override
	public void iscrtaj(Scena s) {
		super.iscrtaj(s);
		Graphics g = s.getGraphics();
		g.setColor(Color.BLUE);
		double x = centar.getX() - r/2 , y = centar.getY() - r/2;
		g.fillOval((int)x, (int)y, (int)(r) , (int)(r));
	}
}
