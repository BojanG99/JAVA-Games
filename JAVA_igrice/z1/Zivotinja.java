package igrica;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Zivotinja {
	
	protected Rupa mojaRupa;
	
	public Zivotinja(Rupa mojaRupa) {
		super();
		this.mojaRupa = mojaRupa;
	}

	public abstract void udarenaZivotinja();

	public void paint(Rupa rupa , int trenutniBroj) {
		double proc = ( 1 -((double)trenutniBroj)/ ((double)rupa.getBrojKoraka()) )/2;
		int x = (int)((proc * ((double)rupa.getWidth())));  
		int y = (int)((proc * ((double)rupa.getHeight()))); 
		
		Rectangle r = new Rectangle( x, y , rupa.getWidth() - 2 *x , rupa.getHeight() - 2 *y );
		iscrtaj(rupa.getGraphics() , r);
		
		
	}
	
	public abstract void iscrtaj(Graphics g ,Rectangle r);

	public abstract void pobeglaZivotinja();

}
