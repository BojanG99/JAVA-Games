package igrica;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Polje extends Canvas {
	protected Mreza mojaMreza;

	public Polje(Mreza mojaMreza) {
		this.mojaMreza = mojaMreza;
	}
	
	public Mreza getMojaMreza() {
		return mojaMreza;
	}
	
	
	public int[] getPozicijuPolja() {
		for (int i = 0 ; i < mojaMreza.getTabla().length;i++)
			for (int j = 0 ; j < mojaMreza.getTabla()[i].length;j++)
				if (mojaMreza.getTabla()[i][j] == this) {
					return new int[] {i , j};
				}
					
		return new int[] {-1 , -1};
	}
	
	public Polje getPoljeUdaljeno(int dy , int dx) {
		int[] kord = this.getPozicijuPolja();
		int x = mojaMreza.getTabla()[0].length ,  y = mojaMreza.getTabla().length;
		
		kord[0] += dy;
		kord[1] += dx;
		
		if (kord[0]<0 || kord[1] < 0 || kord[0]>=y || kord[1]>=x) return null;
		
		return mojaMreza.getTabla()[kord[0]][kord[1]];
	}
	
	public abstract boolean DaLiSeFiguraMozeNalaziti();
	
	
}
