package igrica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	
	private Mreza mojaMreza;
	private Label rezultat = new Label("Poena: 0" , Label.CENTER);
	private Button bt = new Button("Pocni");
	private TextField brojNovcica = new TextField("0");
	private Checkbox[] mojePolje = new Checkbox[2];
	
	public Igra() {
		super("Igra");
		this.setSize(600 , 600);
		this.setVisible(true);
		this.setLayout(new BorderLayout(5 , 5));
		mojaMreza = new Mreza(this);
		this.add(mojaMreza , BorderLayout.CENTER);

		
		dodajDesno();
		dodajDole();
		dodajMeni();
		dodajOsluskivace();
		
		mojaMreza.setVisible(true);
		mojaMreza.requestFocusInWindow();
	}

	public void postaviBrojPoena(int br) {
		rezultat.setText("Poena: " + br);
	}
	
	private void dodajDole() {
		Panel re = new Panel(new FlowLayout(FlowLayout.CENTER , 5 , 5));
		
		re.add(new Label("Novcica: ", Label.RIGHT) );
		re.add(brojNovcica);
		
		re.add(rezultat);
		re.add(bt);
		
		this.add(re , BorderLayout.SOUTH);
	}

	private void dodajDesno() { // dodao osluskivace
		Panel pn = new Panel(new GridLayout(1 , 2));
		pn.add(new Label("Podloga:", Label.CENTER));
		
		Panel desno = new Panel(new GridLayout(2 , 1));
		CheckboxGroup gr = new CheckboxGroup();
		
		Panel gore = new Panel(new GridLayout(1 , 1));
		gore.setBackground(Color.GREEN);
		gore.add(mojePolje[0] = new Checkbox("Trava", true , gr));
		
		mojePolje[0].addItemListener(e->{
			mojaMreza.setVrstaPolja(0);
			mojaMreza.requestFocusInWindow();
		});
		
		Panel dole = new Panel(new GridLayout(1 , 1));
		dole.setBackground(Color.GRAY);
		dole.add(mojePolje[1] =new Checkbox("Zid", false , gr));
		
		mojePolje[1].addItemListener(e->{
			mojaMreza.setVrstaPolja(1);
			mojaMreza.requestFocusInWindow();
		});
		
		desno.add(gore);
		desno.add(dole);
		
		pn.add(desno);
		
		this.add(pn , BorderLayout.EAST);
		
	}

	private void dodajMeni() { // dodao osluskivace
		MenuBar traka = new MenuBar();
		Menu prva = new Menu("Rezim");
		prva.add("Rezim izmena");
		prva.add("Rezim igranja");
		
		traka.add(prva);
		prva.addActionListener(e-> {
			String rez = e.getActionCommand();
			if (rez.equals("Rezim izmena")) {
				if (mojaMreza.getRezim() == 1)
					mojaMreza.trajnoZaustavi();
				mojaMreza.setRezim(0);
			}
			else if (rez.equals("Rezim igranja")) {
				mojaMreza.setRezim(1);
			}
			mojaMreza.requestFocusInWindow();
		});
		
		this.setMenuBar(traka);
	}

	private void dodajOsluskivace() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mojaMreza.trajnoZaustavi();
				dispose();
			}
		});
		
		
		brojNovcica.addActionListener(e-> {
			if (mojaMreza.getRezim() == 1) {
				String re = e.getActionCommand();
				try {
					int broj = Integer.parseInt(re);
					if (broj>0) {
						mojaMreza.setBrojNovcica(broj);
					}
				mojaMreza.requestFocusInWindow();
				}
				catch (NumberFormatException exc) {}
			}
		});
		
		brojNovcica.addTextListener(e->{
			if(mojaMreza.getRezim() ==0) {
				String re = brojNovcica.getText();
				try {
					int broj = Integer.parseInt(re);
					if (broj>0) {
						mojaMreza.setBrojNovcica(broj);
					}
				}
				catch (NumberFormatException exc) {}
			}
		//	mojaMreza.requestFocusInWindow();
		});
		
		bt.addActionListener(e-> {
			if (mojaMreza.getRezim() == 1) { // rezim izmena
				mojaMreza.inicijalizuj(mojaMreza.getBrojNovcica());
				mojaMreza.zapocni();
				if (!mojaMreza.requestFocusInWindow()) System.out.println("Nesto nije uredu sa ovim fokusom");;
			}
		});
		
		
		/*this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (mojaMreza.getRezim() == 0) {
					Point p = e.getPoint();
					Polje po = null;
					if (mojaMreza.vrstaPolja == 0) {
						po = new Trava(mojaMreza);
					}
					else {
						po = new Zid(mojaMreza);
					}
					
					int x =(int) (p.getX() / (mojaMreza.getWidth() / mojaMreza.getTabla().length)) , 
						y = (int) (p.getY() / (mojaMreza.getHeight() / mojaMreza.getTabla().length));


					mojaMreza.getTabla()[y][x] = po; ;
					mojaMreza.remove(y*mojaMreza.getTabla().length + x);
					mojaMreza.add(mojaMreza.getTabla()[y][x] , y*mojaMreza.getTabla().length + x);
					po.setVisible(true);
				}
				System.out.println("Ispisi mi ovo");
				repaint();
			}
		});*/
		
	}
	
	public static void main(String[] args) {
		new Igra();
	}
}
