package igrica;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.*;


public class Krug {
	protected Vektor centar;
	protected double r;
	protected Color boja;
	
	public Krug(Vektor centar,Color boja , double r) {
		this.boja = boja;
		this.centar = centar;
		this.r = r;
	}
	
	public boolean preklapa(Krug k) {
		double d = sqrt(pow(centar.getX() - k.centar.getX(), 2)  +pow(centar.getY() - k.centar.getY(), 2));
		return d <= r + k.r;
	}
	
	public void iscrtaj(Scena s) {
		Graphics g = s.getGraphics();
		g.setColor(boja);
		double x = centar.getX() - r , y = centar.getY() - r;
		g.fillOval((int)x, (int)y, (int)(2*r) , (int)(2*r));
	}
	
}
