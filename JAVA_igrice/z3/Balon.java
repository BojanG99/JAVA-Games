package igrica;

import java.awt.Color;

public class Balon extends KruznaFigura {

	
	public Balon(Vektor centar, Color boja, double r, double v, Scena s) {
		super(centar, boja, r, v, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pomeri() {
		centar.saberi(new Vektor(0 , brzina));

	}

	@Override
	public void obavestiFiguruSudarilaSe() {
	//	System.out.println("Obavestio sam figuru " + this);

	}

}
