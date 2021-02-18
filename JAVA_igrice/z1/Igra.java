package igrica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	public static Igra jedinaIgra;
	private Basta mojaBasta;
	private Checkbox[] poljeZaPotvrdu = new Checkbox[3];
	private Button dugme ;
	private Label rezultat;
	
	
	public static Igra getIgra() {
		if (jedinaIgra == null)
			jedinaIgra = new Igra();
		
		return jedinaIgra;
	}

	private Igra() {
		super("Igra");
		mojaBasta = new Basta(4 , 4);
		mojaBasta.setSize(400 ,  600);
		this.add(mojaBasta);
		dodajDesno();
		dodajOsluskivace();
		this.setSize(600, 600);
		setVisible(true);
	}
	
	
	private void dodajDesno() {
	Panel ve = new Panel(new GridLayout(2 , 1));
		
		Panel pn = new Panel(new GridLayout(5 , 1 , 35 , 0));
		pn.add(new Label("Tezina:" , Label.CENTER)).setFont(new Font(null , Font.BOLD , 12));
		CheckboxGroup gr = new CheckboxGroup();
		poljeZaPotvrdu[0] = new Checkbox("Lako" , gr , false);
		poljeZaPotvrdu[1] = new Checkbox("Srednje" , gr , true);
		poljeZaPotvrdu[2] = new Checkbox("Tesko" , gr , false);
		
		for (int i = 0 ; i < 3;i++)
			pn.add(poljeZaPotvrdu[i]);
		
		dugme = new Button("Kreni");
		pn.add(dugme);
		
		
		ve.add(pn);
		
		

		rezultat = new Label("Povrce: 100" );
		rezultat.setFont(new Font(null , Font.BOLD , 12));
		

		
		ve.add(rezultat);
		ve.setSize(200, 600);
		this.add(ve , BorderLayout.EAST);
		
	}
	
	public void postaviRezultat(int br) {
		rezultat.setText("Povrce " + br);
	}

	private void dodajOsluskivace() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(mojaBasta.ziva())mojaBasta.trajnoZaustavi();
				dispose();
			}
		});
		poljeZaPotvrdu[0].addItemListener(l-> {
			if (!mojaBasta.ziva()) {
				mojaBasta.setBrojKoraka(10);
				mojaBasta.setIntervalCekanja(1000);
			}
		});
		
		poljeZaPotvrdu[1].addItemListener(l-> {
			if (!mojaBasta.ziva()) {
				mojaBasta.setBrojKoraka(8);
				mojaBasta.setIntervalCekanja(750);
			}
		});
		
		poljeZaPotvrdu[2].addItemListener(l-> {
			if (!mojaBasta.ziva()) {
				mojaBasta.setBrojKoraka(6);
				mojaBasta.setIntervalCekanja(500);
			}
		});
		
		dugme.addActionListener(e-> {
			String ret =e.getActionCommand();
			if (ret.equals("Kreni")) {
				dugme.setLabel("Stani");
				dugme.setActionCommand("Stani");
				mojaBasta.pokreni();
			}
			else {
				dugme.setLabel("Kreni");
				mojaBasta.trajnoZaustavi();
			}
		});
	}

	public static void main(String[] args) {
		Igra.getIgra();
	}
}
