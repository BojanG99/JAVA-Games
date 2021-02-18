package igrica;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	private Scena mojaScena;
	
	public Igra() {
		super("Baloni");
		this.setSize(600, 600);
		mojaScena = new Scena(this);
		this.add(mojaScena);
		dodajOsluskivace();
		mojaScena.pokreni();
		this.setVisible(true);
	}

	private void dodajOsluskivace() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mojaScena.trajnoZaustavi();
				dispose();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Igra();
	}
}
