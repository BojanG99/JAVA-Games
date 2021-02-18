package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Trava extends Polje {

	public Trava(Mreza mojaMreza) {
		super(mojaMreza);
		Polje p = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (mojaMreza.vrstaPolja == 1)
					Mreza.zameniPolje(p);
			}
		});
	}

	@Override
	public boolean DaLiSeFiguraMozeNalaziti() {
		return true;
	}

	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
