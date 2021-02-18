package igrica;

import java.awt.Color;

public abstract class KruznaFigura extends Krug {

	protected double brzina ;
	protected Scena mojaScena;
	
	
	public KruznaFigura(Vektor centar, Color boja, double r , double v , Scena s) {
		super(centar, boja, r);
		brzina = v;
		mojaScena = s;
	}
	
	public void obavestiFiguruProtekloVreme() {
		this.pomeri();
		if (!unutarScene()) {
			mojaScena.izbaci(this); 
		}
	}
	
	private boolean unutarScene() {
		if (this.centar.getX() - this.r < 0) return false;
		if (this.centar.getY() - this.r < 0) return false;
		double vis = (double)mojaScena.getHeight() , sir = (double) mojaScena.getWidth();
		
		if (this.centar.getX() + this.r > sir) return false;
		if (this.centar.getY() + this.r > vis) return false;
			
		return true;
	}

	public abstract void pomeri();
	
	public abstract void obavestiFiguruSudarilaSe();

}
