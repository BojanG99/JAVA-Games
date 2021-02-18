package igrica;

import java.awt.Graphics;

public abstract class Figura {
	protected Polje mojePolje;
	
	public Figura(Polje p) {
		mojePolje = p;
	}

	public Polje getMojePolje() {
		return mojePolje;
	}
	
	public void pomeri(Polje p) {
		if(p != null ) {
			if (p.DaLiSeFiguraMozeNalaziti()) {
				mojePolje = p;
			}
		}
	}
	
	@Override 
	public boolean equals(Object o) {
		if (!(o instanceof Figura)) return false;
		
		return ((Figura)o).mojePolje == this.mojePolje;
	}
	
	public abstract void iscrtaj();
	
}
